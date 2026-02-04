package com.serendipity.myold.base;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 多态demo
 *
 * @author serendipity
 * @date 2021-07-26 22:19
 * <p>
 * 开闭原则：新增支付方式只需添加新策略类
 * 依赖注入：策略通过构造函数注入，便于测试
 * 运行时绑定：process()方法在运行时确定调用哪个实现
 */
@SuppressWarnings("all")
public class PolymorphismDemo {

    @AllArgsConstructor
    @Data
    class Order {
        private String orderId;
        private double amount;
    }

    enum PaymentType {
        ALIPAY, WECHAT
    }

    @AllArgsConstructor
    @Data
    class PaymentResult {
        private boolean success;
        private String message;
    }

    class AlipayClient {
        public AlipayResponse execute(AlipayRequest request) {
            // 模拟调用支付宝接口
            return new AlipayResponse(true, "支付成功");
        }
    }

    @AllArgsConstructor
    @Data
    class AlipayRequest {
        private String orderId;
        private double amount;
    }

    @AllArgsConstructor
    @Data
    class AlipayResponse {
        private boolean success;
        private String message;
    }

    // 策略模式结合多态
    public interface PaymentStrategy {
        PaymentResult process(Order order);

        boolean supports(PaymentType type);
    }

    // 支付宝支付策略
    public class AlipayStrategy implements PaymentStrategy {
        private final AlipayClient alipayClient;

        public AlipayStrategy(AlipayClient client) {
            this.alipayClient = client;
        }

        @Override
        public PaymentResult process(Order order) {
            // 支付宝特定的处理逻辑
            AlipayRequest request = buildAlipayRequest(order);
            AlipayResponse response = alipayClient.execute(request);
            return convertToResult(response);
        }

        @Override
        public boolean supports(PaymentType type) {
            return type == PaymentType.ALIPAY;
        }

        private PaymentResult convertToResult(AlipayResponse response) {
            // 转换结果
            return new PaymentResult(response.isSuccess(), response.getMessage());
        }

        private AlipayRequest buildAlipayRequest(Order order) {
            // 构建支付宝请求
            return new AlipayRequest(order.getOrderId(), order.getAmount());
        }
    }

    // 微信支付策略
    public class WechatPayStrategy implements PaymentStrategy {
        @Override
        public PaymentResult process(Order order) {
            // 微信支付特定逻辑
            return new PaymentResult(true, "微信支付成功");
        }

        @Override
        public boolean supports(PaymentType type) {
            return type == PaymentType.WECHAT;
        }
    }

    // 支付处理器：利用多态实现开闭原则
    public class PaymentProcessor {
        private final List<PaymentStrategy> strategies;

        public PaymentProcessor(List<PaymentStrategy> strategies) {
            this.strategies = new ArrayList<>(strategies);
        }

        public PaymentResult pay(Order order, PaymentType paymentType) {
            return strategies.stream()
                    .filter(strategy -> strategy.supports(paymentType))
                    .findFirst()
                    .map(strategy -> strategy.process(order))
                    .orElseThrow(() -> new UnsupportedOperationException("不支持的支付方式: " + paymentType));
        }
    }

    public static void main(String[] args) {

        PolymorphismDemo polymorphismDemo = new PolymorphismDemo();


        ArrayList<PaymentStrategy> paymentStrategies = Lists.newArrayList
                (
                polymorphismDemo.new AlipayStrategy(polymorphismDemo.new AlipayClient()),
                polymorphismDemo.new WechatPayStrategy()
        );

        PaymentProcessor paymentProcessor = polymorphismDemo.new PaymentProcessor(paymentStrategies);

        PaymentResult pay = paymentProcessor.pay(polymorphismDemo.new Order("1", 200.0), PaymentType.ALIPAY);

        System.out.println(pay);


    }

}

package com.serendipity.createdesign.factory.demo05;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * ClassName SimpleFactoryDemo01
 * Description TODO
 * Author 11931
 * Date 2023-11-24:0:08
 * Version 1.0
 *
 * @author 11931
 * <p>
 * 简单工厂设计模式实现
 */
@Slf4j
@SuppressWarnings("all")
public class SimpleFactoryDemo01 {

    private abstract class Product {
        private String name;

        abstract void dosomething();
    }

    private class Aproduct extends Product {
        private String name;

        public Aproduct(String name) {
            this.name = name;
        }

        @Override
        void dosomething() {
            log.info("Aproduct name ={}", name);
        }

        @Override
        public String toString() {
            return "Aproduct{" + "name='" + name + '\'' + '}';
        }
    }

    private class Bproduct extends Product {

        private String name;

        public Bproduct(String name) {
            this.name = name;
        }

        @Override
        void dosomething() {
            log.info("Bproduct name ={}", name);
        }

        @Override
        public String toString() {
            return "Bproduct{" + "name='" + name + '\'' + '}';
        }
    }

    /**
     * 实现工厂接口
     */
    private interface Factory {
        Product createProduct();
    }

    private class AFactory implements Factory {

        @Override
        public Product createProduct() {
            return new Aproduct("A");
        }
    }

    private class BFactory implements Factory {

        @Override
        public Product createProduct() {
            return new Bproduct("B");
        }
    }

    @Test
    public void test() {
        Factory aFactory = new AFactory();
        Product product = aFactory.createProduct();
        log.info("Aproduct finished ={}", product);
        product.dosomething();
        log.warn("-------------------------------");
        Factory bFactory = new BFactory();
        Product product1 = bFactory.createProduct();
        log.info("Bproduct finished={}", product1);
        product1.dosomething();
    }
}

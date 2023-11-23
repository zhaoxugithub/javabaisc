package com.serendipity.create_design.builder.demo06;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * ClassName BuilderDemo01
 * Description TODO
 * Author 11931
 * Date 2023-11-23:0:43
 * Version 1.0
 *
 * @author 11931
 * 建造者模式模式
 */
@SuppressWarnings("all")
@Slf4j
public class BuilderDemo01 {
    /**
     * 这是一个实体
     */
    @Data
    private class Computer {
        // 必填
        private String cpu;
        private String ram;
        // 选填
        private int usbCount;
        private String keyboard;
        private String display;

        public Computer(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }
    }

    /**
     * 定义一个builder接口
     */
    private interface ComputerBuilder {
        ComputerBuilder setUsbCount();

        ComputerBuilder setKeyBoard();

        ComputerBuilder setDisplay();

        Computer build();
    }

    private class HuaWei implements ComputerBuilder {
        private Computer computer;

        public HuaWei(String cpu, String ram) {
            computer = new Computer(cpu, ram);
        }

        @Override
        public ComputerBuilder setUsbCount() {
            computer.setUsbCount(1);
            return this;
        }

        @Override
        public ComputerBuilder setKeyBoard() {
            computer.setKeyboard("huawei keyboard");
            return this;
        }

        @Override
        public ComputerBuilder setDisplay() {
            computer.setDisplay("huawei display");
            return this;
        }

        @Override
        public Computer build() {
            return computer;
        }
    }

    private class IPhone implements ComputerBuilder {

        private Computer computer;

        private IPhone(String cpu, String ram) {
            computer = new Computer(cpu, ram);
        }

        @Override
        public ComputerBuilder setUsbCount() {
            computer.setUsbCount(3);
            return this;
        }

        @Override
        public ComputerBuilder setKeyBoard() {
            computer.setKeyboard("IPhone keyboard");
            return this;
        }

        @Override
        public ComputerBuilder setDisplay() {
            computer.setDisplay("IPhone display");
            return this;
        }

        @Override
        public Computer build() {
            return computer;
        }
    }

    /**
     * 指挥者
     */
    private class Director {
        ComputerBuilder cb;

        public Director(ComputerBuilder cb) {
            this.cb = cb;
        }

        public void constrain() {
            cb.setDisplay()
              .setKeyBoard()
              .setUsbCount();
        }
    }


    /**
     * 这种情况是没有director这个角色之前
     */
    @Test
    public void test01() {
        ComputerBuilder huaWei = new HuaWei("huawei-x86", "32");
        Computer computer = huaWei.setDisplay()
                                  .setKeyBoard()
                                  .setUsbCount()
                                  .build();
        log.info("huawei computer={}", computer);

        ComputerBuilder iphone = new IPhone("M1", "16");
        Computer iphoneCompter = iphone.setUsbCount()
                                       .setKeyBoard()
                                       .setDisplay()
                                       .build();

        log.info("iphone computer={}", iphoneCompter);
    }


    /**
     * 这个角色时有director之后
     */
    @Test
    public void test02() {
        HuaWei huaWei = new HuaWei("x86", "32");
        Director director = new Director(huaWei);
        director.constrain();
        Computer build = huaWei.build();
        log.info("huawei computer={}", build);
    }
}

package com.serendipity.createdesign.builder.demo03;

import lombok.Data;

@Data
public class Computer {
    //必填
    private String cpu;
    private String ram;
    //选填
    private int usbCount;
    private String keyboard;
    private String display;

    private Computer(Builder builder) {

        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.display = builder.display;
        this.usbCount = builder.usbCount;
        this.usbCount = builder.usbCount;

    }

    public static class Builder {

        private String cpu;
        private String ram;
        private int usbCount;
        private String keyboard;
        private String display;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

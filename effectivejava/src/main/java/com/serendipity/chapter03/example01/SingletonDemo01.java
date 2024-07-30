package com.serendipity.chapter03.example01;

// 单例模式
public class SingletonDemo01 {


    // 饿汉式
    private static class A1 {
        public static final A1 a1 = new A1();

        private A1() {
        }

        public void process() {
            System.out.println("A1 process.");
        }
    }

    private static class A2 {
        private static final A2 a2 = new A2();

        private A2() {
        }

        public static A2 getInstance() {
            return a2;
        }

        public void process() {
            System.out.println("A2 process");
        }
    }

    private static class A3 {
        private volatile static A3 instance;

        private A3() {
        }

        public static A3 getInstance() {
            if (instance == null) {
                synchronized (A3.class) {
                    if (instance == null) {
                        instance = new A3();
                    }
                }
            }
            return instance;
        }

        public void process() {
            System.out.println("A3 process");
        }
    }

    private static class A4 {
        private A4() {
        }

        private static class A4Inner {
            private static final A4 a4 = new A4();
        }


        public static A4 getInstance() {
            return A4Inner.a4;
        }


        private void process() {
            System.out.println("A4 process");
        }
    }

    // 单利的最好实现
    private enum A5 {
        INSTANCE;

        public void process() {
            System.out.println("A5 process");
        }
    }

    public static void main(String[] args) {
        A5 instance = A5.INSTANCE;
        instance.process();
    }

}

package com.serendipity.create_design.factory.simplefactory2;

public class Main {

    public static void main(String[] args) {
        PhoneFactory phoneFactory = new PhoneFactory();
        IPhone iphone = (IPhone) phoneFactory.makePhone("iphone");
        Phone phone = phoneFactory.makePhone("miphone");
    }

}

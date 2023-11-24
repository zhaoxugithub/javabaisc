package com.serendipity.createdesign.factory.simplefactory2;

public class PhoneFactory {

    public Phone makePhone(String phoneType) {
        if ("iphone".equals(phoneType)) {
            return new IPhone();
        } else if ("miphone".equals(phoneType)) {
            return new MiPhone();
        }
        return null;
    }
}

package com.serendipity.create_design.factory.simplefactory;


import java.util.Map;

class PhoneFactory {

    public MakePhone makeIPhone(Map<String,String> params) {
        return new MakeIPhone(params);
    }

    public MakePhone makeXMIphone(Map<String,String> params) {
        return new MakeXMIPhone(params);
    }
}

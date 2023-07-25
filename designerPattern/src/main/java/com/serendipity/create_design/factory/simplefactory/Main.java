package com.serendipity.create_design.factory.simplefactory;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        PhoneFactory pf = new PhoneFactory();

        //生产iphone
        Map<String, String> map = new HashMap<>();
        map.put("name", "xr");
        map.put("size", "6.1");
        map.put("brand", "苹果");
        map.put("color", "黑");
        map.put("money", "5000");
        MakePhone phone = pf.makeIPhone(map);
        System.out.println(phone);

        //生产小米
        Map<String, String> map2 = new HashMap<>();
        map.put("name", "米10");
        map.put("size", "6.1");
        map.put("brand", "小米");
        map.put("color", "黑");
        map.put("money", "5000");
        MakePhone mi = pf.makeXMIphone(map);
        System.out.println(mi);
    }


}

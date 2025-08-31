package com.serendipity.myold.utils;

import java.util.UUID;

public class UUIDUtils {

    public static void main(String[] args) {
        UUID u = UUID.randomUUID();

        System.out.println(u.toString().replace("-", "").length());
        System.out.println(u);
    }
}

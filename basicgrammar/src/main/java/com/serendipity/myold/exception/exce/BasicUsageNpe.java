package com.serendipity.myold.exception.exce;

/**
 * ClassName BasicUsageNpe
 * Description TODO
 * Author 11931
 * Date 2022-10-22:10:55
 * Version 1.0
 **/
@SuppressWarnings("all")
public class BasicUsageNpe {
    private static boolean stringEquals(String x, String y) {
        return x.equals(y);
    }

    public static class User {
        private String name;
    }

    public static void main(String[] args) {
        // 1. 字符串使用 equals 可能会报空指针错误
        // System.out.println(stringEquals("xyz", null));
        // System.out.println(stringEquals(null, "xyz"));
        // 2. 对象数组 new 出来, 但是元素没有初始化
        User[] users = new User[10];
        for (int i = 0; i != 10; ++i) {
            users[i] = new User();
            users[i].name = "imooc-" + i;
        }
        // 3. List 对象 addAll 传递 null 会抛出空指针
        // List<User> users = new ArrayList<User>();
        // User user = null;
        // List<User> users_ = null;
        // users.add(user);
        // // 注意：这里会报空指针
        // users.addAll(users_);
    }
}

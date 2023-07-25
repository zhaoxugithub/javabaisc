package com.serendipity.myold.exception.exce;

/**
 * ClassName WhatIsNpe
 * Description TODO
 * Author 11931
 * Date 2022-10-22:1:53
 * Version 1.0
 **/

// 深度理解什么是空指针异常
public class WhatIsNpe {

    public static class User {

        private String name;
        private String[] address;

        public void print() {
            System.out.println("This is User Class!");
        }

        public String readBook() {
            System.out.println("User Read Imooc Escape!");
            return null;
        }
    }
    /**
     * <h2>自定义一个运行时异常</h2>
     */
    public static class CustomException extends RuntimeException {
    }
    public static void main(String[] args) {
        // 第一种情况: 调用了空对象的实例方法
//        User user = null;
//        user.print();
        // 第二种情况: 访问了空对象的属性
//        User user = null;
//        System.out.println(user.name);

        // 第三种情况: 当数组是一个空对象的时候, 取它的长度
//        User user = new User();
//        System.out.println(user.address.length);

        // 第四种情况: null 当做 Throwable 的值
//        CustomException exception = null;
//        throw exception;

        // 第五种情况: 方法的返回值是 null, 调用方直接去使用
        User user = new User();
        System.out.println(user.readBook().contains("MySQL"));

        // 第六种情况如下
        /*
          这个就容易产生空指针
          System.out.println(request.getParameter("username"))

          修改如下：
          if(request.getParameter("username){
             System.out.println(request.getParameter("username"))
          }else{
            throw exception;
          }
         */

        // 第七种情况：给对象重新赋值的时候又调用
        /*
            User user = new User();
            Tools tools = new Tools();
            //tools.getUser()可能会产生空指针
            user = tools.getUser();
            user.print();

            修改如下：
            Tools tools = new Tools();
            user = tools.getUser() == null?new user():tools.getUser
            user.print()

         */
    }
}

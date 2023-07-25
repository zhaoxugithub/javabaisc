package com.serendipity.myold.reflect.invocationhandlerproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {


    public static void main(String[] args) {

        InvocationHandler invocationHandler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("morning")){

//                    method.invoke()
                    System.out.println("morning....");
                }else {
                    System.out.println("other");
                }
                return null;
            }
        };

        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, invocationHandler);
        hello.morning();
        hello.afternoon();
    }

}

package com.serendipity.action_design.observer.demo01;

public class Main {

    public static void main(String[] args) {
         ConcreteSubject mSubscriptionSubject=new ConcreteSubject();
        //创建微信用户
        WeiXinUser user1=new WeiXinUser("杨影枫");
        WeiXinUser user2=new WeiXinUser("月眉儿");
        WeiXinUser user3=new WeiXinUser("紫轩");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.alarm("刘望舒的专栏更新了");
    }
}

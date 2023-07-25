package com.serendipity.action_design.observer.demo01;

//观察者模式  需要将观察者被被观察者引用
public interface Subject {
    /**
     * 添加订阅者
     * @param observer
     */
    void attach(Observer observer);

    /**
     * 删除订阅者
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知订阅者更新消息
     */
    void alarm(String message);
}

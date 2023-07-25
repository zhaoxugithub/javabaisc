package com.serendipity.action_design.observer.demo01;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> weiXinUsers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        weiXinUsers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weiXinUsers.remove(observer);
    }

    @Override
    public void alarm(String message) {
        for (Observer observer : weiXinUsers) {
            observer.update(message);
        }
    }
}

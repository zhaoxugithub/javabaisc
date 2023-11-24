package com.serendipity.actiondesign.mediator.demo01;

import java.util.Date;

public class ChatRoom {
    public void showMessage(User user, String message) {
        System.out.println(new Date().toString()
                + " [" + user.getName() +"] : " + message);
    }
}

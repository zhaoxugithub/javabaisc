package com.serendipity.action_design.mediator.demo01;

public class Main {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();
        User user = new User("zx", chatRoom);
        User user1 = new User("qq", chatRoom);
        user.sendMessage("hello world");
        user1.sendMessage("hello world2233");
    }
}

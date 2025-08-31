package com.serendipity.actiondesign.command.demo01;

public class Main {
    public static void main(String[] args) {
        Content content = new Content();
        System.out.println(content.msg);
        CopyCommand copyCommand = new CopyCommand(content);
        copyCommand.doit();
        System.out.println(content.msg);
        copyCommand.undo();
        System.out.println(content.msg);
    }
}

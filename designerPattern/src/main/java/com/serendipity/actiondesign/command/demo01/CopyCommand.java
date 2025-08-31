package com.serendipity.actiondesign.command.demo01;

public class CopyCommand extends Command {
    private final Content c;
    public String strToInsert = "http://www.mashibin.com";

    public CopyCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doit() {
        c.msg = c.msg + strToInsert;
    }

    @Override
    public void undo() {
        c.msg = c.msg.substring(0, c.msg.length() - strToInsert.length());
    }
}

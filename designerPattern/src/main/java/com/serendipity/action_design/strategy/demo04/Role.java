package com.serendipity.action_design.strategy.demo04;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/7 23:24
 * FileName: Role
 * Description: action_design.strategy.demo04
 */
public abstract class Role {

    public String name;

    private IAttackBehavior iAttackBehavior;
    private IDefendBehavior iDefendBehavior;
    private IDisplayBehavior iDisplayBehavior;
    private IRunBehavior iRunBehavior;

    public Role setIAttackBehavior(IAttackBehavior iAttackBehavior) {
        this.iAttackBehavior = iAttackBehavior;
        return this;
    }

    public Role setIDefendBehavior(IDefendBehavior iDefendBehavior) {
        this.iDefendBehavior = iDefendBehavior;
        return this;
    }

    public Role setIDisplayBehavior(IDisplayBehavior iDisplayBehavior) {
        this.iDisplayBehavior = iDisplayBehavior;
        return this;
    }

    public Role setIRunBehavior(IRunBehavior iRunBehavior) {
        this.iRunBehavior = iRunBehavior;
        return this;
    }


    public void attack() {
        iAttackBehavior.attack();
    }

    public void run() {
        iRunBehavior.run();
    }

    public void defend() {
        iDefendBehavior.defend();
    }

    public void display() {
        iDisplayBehavior.display();
    }
}

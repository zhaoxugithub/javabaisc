package com.serendipity.action_design.strategy.demo04;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/7 23:38
 * FileName: Main
 * Description: action_design.strategy.demo04
 */
public class Main {

    public static void main(String[] args) {

        RoleA roleA = new RoleA("A");
        roleA.setIRunBehavior(new RunBehaviorLinBo())
                .setIAttackBehavior(new AttackBehaviorJiuYan())
                .setIDefendBehavior(new DefendBehaviorJinGang())
                .setIDisplayBehavior(new DisplayBehavior1());

        roleA.attack();
        roleA.display();
        roleA.defend();
        roleA.run();

    }
}

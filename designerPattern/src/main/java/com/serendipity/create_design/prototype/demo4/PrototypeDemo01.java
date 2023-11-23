package com.serendipity.create_design.prototype.demo4;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName PrototypeDemo01
 * Description TODO
 * Author 11931
 * Date 2023-11-24:0:38
 * Version 1.0
 *
 * @author 11931
 * 原型模式
 */
@SuppressWarnings("all")
@Slf4j
public class PrototypeDemo01 {

    @Data
    private class APrototype implements Cloneable {
        private String name;
        private int age;
        private List<String> hobbies;

        public APrototype(String name, int age, List<String> hobbies) {
            this.age = age;
            this.name = name;
            this.hobbies = hobbies;
        }

        @Override
        protected APrototype clone() throws CloneNotSupportedException {
            return (APrototype) super.clone();
        }
    }

    @Test
    public void testAprototype() throws CloneNotSupportedException {
        APrototype aPrototype = new APrototype("p1", 30, List.of("reading", "runnig"));
        APrototype aPrototypeCopy = aPrototype.clone();
        // 两个对象不一致: false
        log.info("aPrototype==aPrototypeCopy:{},aPrototype={},aPrototypeCopy={}", aPrototypeCopy == aPrototype, aPrototype, aPrototypeCopy);
        // 这两个对象中的引用对象是一样的
        log.info("aPrototype.hobbies == aPrototypeCopy.hobbies:{}", aPrototype.hobbies == aPrototypeCopy.hobbies);
    }


    private class BPrototype implements Cloneable {
        private String name;
        private int age;
        private List<String> hobbies;

        public BPrototype(String name, int age, List<String> hobbies) {
            this.name = name;
            this.age = age;
            this.hobbies = hobbies;
        }

        @Override
        protected BPrototype clone() throws CloneNotSupportedException {
            BPrototype bPrototype = (BPrototype) super.clone();
            bPrototype.hobbies = new ArrayList<>(hobbies);
            return bPrototype;
        }
    }

    @Test
    public void testBprototype() throws CloneNotSupportedException {
        BPrototype bPrototype = new BPrototype("bPrototype", 20, List.of("game", "run"));
        BPrototype bPrototypeCopy = bPrototype.clone();
        log.info("bPrototype==bPrototypeCopy:{}", bPrototype == bPrototypeCopy);
        log.info("bPrototype.hobbies==bPrototypeCopy.hobbies:{}", bPrototype.hobbies == bPrototypeCopy.hobbies);
    }
}

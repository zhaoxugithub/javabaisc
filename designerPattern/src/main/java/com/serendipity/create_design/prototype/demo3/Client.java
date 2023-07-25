package com.serendipity.create_design.prototype.demo3;

public class Client {

    public static void main(String[] args) {

        DeepProtoType deepProtoType = new DeepProtoType();
        deepProtoType.name = "大牛";
        deepProtoType.deepCloneableTarget = new DeepCloneableTarget("牛大大", "deepCloneableTarget");

        DeepProtoType clone = (DeepProtoType) deepProtoType.deepClone();
        System.out.println(deepProtoType);
        System.out.println(clone);
    }
}

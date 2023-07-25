package com.serendipity.create_design.prototype.demo3;

import java.io.Serializable;

/**
 * 浅克隆，基本类型是可以复制，但是引用类型是克隆引用
 */
public class DeepCloneableTarget implements Cloneable, Serializable {

    private String cloneName;
    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneClass = cloneClass;
        this.cloneName = cloneName;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "DeepCloneableTarget{" +
                "cloneName='" + cloneName + '\'' +
                ", cloneClass='" + cloneClass + '\'' +
                '}';
    }
}

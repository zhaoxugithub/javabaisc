package com.serendipity.createdesign.prototype.demo3;

import java.io.*;

/**
 * 深克隆的方法
 */
public class DeepProtoType implements Serializable, Cloneable {

    public String name;
    public DeepCloneableTarget deepCloneableTarget;

    public DeepProtoType() {
    }

    /**
     * 深克隆
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() {

        DeepProtoType deepProtoType = null;
        try {
            this.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
            deepProtoType = (DeepProtoType) super.clone();
            deepProtoType.deepCloneableTarget = this.deepCloneableTarget;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return deepProtoType;
    }

    /**
     * 序列换的方式实现深克隆
     *
     * @return
     */
    public Object deepClone() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        DeepProtoType deepProtoType = null;
        try {
            //字节流包装成对象流
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //将对象写到对象流中
            oos.writeObject(this);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            deepProtoType = (DeepProtoType) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deepProtoType;
    }

    @Override
    public String toString() {
        return "DeepProtoType{" +
                "name='" + name + '\'' +
                ", deepCloneableTarget=" + deepCloneableTarget +
                '}';
    }
}

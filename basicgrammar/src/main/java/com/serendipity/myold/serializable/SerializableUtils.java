package com.serendipity.myold.serializable;

import java.io.*;

public class SerializableUtils {

    /**
     * 序列化数组
     */
    public void serializableArray(Object obj) throws IOException {
        FileOutputStream fos = new FileOutputStream("array.txt");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(obj);
        os.flush();
        os.close();
    }

    /**
     * 序列化数组
     */
    public void serializablePerson(SerializableDemo02.Person person) throws IOException {
        FileOutputStream fos = new FileOutputStream("array.txt");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(person);
        os.flush();
        os.close();
    }

    public void deSerializePerson() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("array.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SerializableDemo02.Person person =(SerializableDemo02.Person)  ois.readObject();
        System.out.println(person);
        ois.close();
    }

    /**
     * 反序列化
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void deSerializeArray() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("array.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Integer[] array = (Integer[]) ois.readObject();
        for (Integer integer : array) {
            System.out.println(integer);
        }
        ois.close();
    }
}

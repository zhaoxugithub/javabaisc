package com.serendipity.myold.serializable;

import org.junit.jupiter.api.Test;

import java.io.*;

public class SerializableDemo01 {

    transient Integer[] array = {1, 2, null, 4, null, 5, 6};
    Integer[] array2 = {1, 2, null, 4, null, 5, 6};

    transient Integer[] array3 = new Integer[10];

    {
        array3[0] = 1;
    }

    /**
     * 序列化数组
     */
    public void serializableArray() throws IOException {
        FileOutputStream fos = new FileOutputStream("array.txt");
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(array3);
        os.flush();
        os.close();
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

    @Test
    public void test() throws IOException, ClassNotFoundException {
        serializableArray();
        deSerializeArray();
    }
}

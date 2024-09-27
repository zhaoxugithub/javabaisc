package com.serendipity.myold.serializable;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

public class SerializableDemo02 {

    private final SerializableUtils serializableUtils = new SerializableUtils();

    class Person implements Serializable {
        private String name;
        private transient String password;

        public Person(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }

    @Test
    public void serializablePerson() throws IOException, ClassNotFoundException {
        Person person = new Person("zhangsan", "123456");
        serializableUtils.serializablePerson(person);
        serializableUtils.deSerializePerson();
    }

}

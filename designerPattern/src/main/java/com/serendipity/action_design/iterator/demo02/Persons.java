package com.serendipity.action_design.iterator.demo02;

import java.util.ArrayList;
import java.util.List;

//实现Collection 表明Persons 这个类具有了迭代器功能
public class Persons implements Collection<Person> {

    private List<Person> list = new ArrayList<>();

    //可以new出自己的迭代器
    @Override
    public Iterator iterator() {


        return new PersonIterator(this);
    }

    @Override
    public Persons add(Person person) {
        list.add(person);
        return this;
    }

    @Override
    public int size() {
        return list.size();
    }

    public Person getPerson(int index) {
        return list.get(index);
    }

    class PersonIterator implements Iterator<Person> {

        private Persons persons;
        private int index;

        public PersonIterator(Persons persons) {
            this.persons = persons;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            if (index < persons.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Person next() {
            return getPerson(index++);
        }
    }
}

package com.serendipity.action_design.iterator.demo02;

public class Main {

    public static void main(String[] args) {

        Persons persons = new Persons();
        persons.add(new Person(1, "2", 29))
                .add(new Person(2, "2", 33))
                .add(new Person(3, "ssss", 40));

        Iterator iterator = persons.iterator();
        while (iterator.hasNext()) {
            Person next = (Person) iterator.next();
            System.out.println(next);
        }
    }

}

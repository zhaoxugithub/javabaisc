package com.serendipity.createdesign.factory.abstractfactory;

public class Main {

    public static void main(String[] args) {

        FactoryProctor factoryProctor = new FactoryProctor();
        AbstractFactory shape = factoryProctor.createFactory("shape");
        Shape shape1 = shape.createShape("circle");



    }
}

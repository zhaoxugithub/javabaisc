package com.serendipity.createdesign.factory.abstractfactory;

public class FactoryProctor {

    public AbstractFactory createFactory(String type) {

        if ("shape".equals(type)) {
            return new ShapeFactory();
        } else if ("color".equals(type)) {
            return new ColorFactory();
        }
        return null;
    }


}

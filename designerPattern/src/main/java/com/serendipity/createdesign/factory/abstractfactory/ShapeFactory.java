package com.serendipity.createdesign.factory.abstractfactory;

public class ShapeFactory implements AbstractFactory {
    @Override
    public Shape createShape(String type) {
        if ("rectangle".equals(type)) {
            return new Rectangle();
        } else if ("circle".equals(type)) {
            return new Circle();
        } else {
            return null;
        }
    }

    @Override
    public Color createColor(String type) {
        return null;
    }
}

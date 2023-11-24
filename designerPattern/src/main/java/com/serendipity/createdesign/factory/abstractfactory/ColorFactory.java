package com.serendipity.createdesign.factory.abstractfactory;

public class ColorFactory implements AbstractFactory {
    @Override
    public Shape createShape(String type) {
        return null;
    }

    @Override
    public Color createColor(String type) {
        if ("red".equals(type)) {
            return new Red();
        } else if ("yellow".equals(type)) {
            return new Yellow();
        }
        return null;
    }
}

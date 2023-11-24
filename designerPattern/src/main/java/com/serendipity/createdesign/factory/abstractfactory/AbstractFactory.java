package com.serendipity.createdesign.factory.abstractfactory;

/**
 * 抽象工厂用来产生抽象的产品
 */
public interface AbstractFactory {
    //产生形状
    Shape createShape(String type);

    //产生颜色
    Color createColor(String type);
}

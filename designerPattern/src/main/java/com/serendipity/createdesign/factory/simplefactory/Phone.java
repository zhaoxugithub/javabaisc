package com.serendipity.createdesign.factory.simplefactory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Phone {
    private String name;
    private String size;
    private String brand;
    private String color;
    private String money;
}

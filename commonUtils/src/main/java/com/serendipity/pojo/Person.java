package com.serendipity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 11931
 * @createTime: 2024/04/02 0:58
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Person {
    private String name;
    private int age;
    private Address address;
}

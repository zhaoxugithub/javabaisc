package com.serendipity.chapter02.ch01;

import lombok.Builder;
import lombok.Data;
/*
    @Builder: 生成全属性的构造方法
    @Data： 生成无参构造

    两个一起用会导致 无法使用无参构造

 */
// 自动实现构造
@Data
@Builder
public class Person01 {
    private  String firstName;
    private final String lastName;
    private final int age;
    private final String address;
    private final String phoneNumber;
}

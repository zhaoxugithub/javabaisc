package com.serendipity.chapter02.ch01;

import lombok.Builder;
import lombok.Data;

/**
 * NoArgsConstructor 和 Builder 一起用会报错
 */
@Data
@Builder
public class Person03 {
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String phoneNumber;
}

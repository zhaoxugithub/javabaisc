package com.serendipity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 11931
 * @createTime: 2024/04/02 1:07
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String province;
    private String city;
}

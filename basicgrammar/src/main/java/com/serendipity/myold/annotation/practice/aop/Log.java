package com.serendipity.myold.annotation.practice.aop;


import java.lang.annotation.*;

/**
 * 自定义一个log注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME) // 运行期间有效
@Target({ElementType.PARAMETER, ElementType.METHOD}) // 作用在变量和方法上
public @interface Log {

    /**
     * 模块
     *
     * @return
     */
    public String title() default "";

    /**
     * 功能
     *
     * @return
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     *
     * @return
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     *
     * @return
     */
    public boolean isSaveRequestData() default true;

}

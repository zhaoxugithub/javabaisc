package com.serendipity.myold.utils.annotation;

import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.lang.Console;

import java.lang.reflect.Method;

public class LogAspect extends SimpleAspect {

    public LogAspect() {
    }

    public boolean before(Object target, Method method, Object[] args) {
        Console.log("Method [{}.{}] execute start", target.getClass()
                                                          .getName(), method.getName());

        return true;
    }

    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        Console.log("Method [{}.{}] return value [{}]", target.getClass()
                                                              .getName(), method.getName(), returnVal);
        return true;
    }
}

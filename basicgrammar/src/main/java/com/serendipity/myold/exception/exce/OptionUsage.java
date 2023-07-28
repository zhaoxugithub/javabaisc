package com.serendipity.myold.exception.exce;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * ClassName OptionUsage
 * Description Optional 使用
 * Author 11931
 * Date 2022-10-22:10:58
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class OptionUsage {

    public static class User {
        private String name;

        public String getName() {
            return name;
        }
    }

    // 这种判断空对象是一种常见的方法
    private static void isUserEqualNull() {
        User user = null;
        if (user == null) {
            log.info("user is null");
        } else {
            log.info("user is not null");
        }
        // 上述这段代码和下面这个一个意思
        // Optional.empty() 创建一个空的Optional容器
        Optional<Object> empty = Optional.empty();
        if (empty.isPresent()) {
            log.info("user is not null");
        } else {
            log.info("user is null");
        }
    }

    private static void badUsageOptional() {
        Optional<User> optional = Optional.ofNullable(null);
        User user = optional.orElse(null); // good
        user = optional.isPresent() ? optional.get() : null; // bad
        log.info("user = ", user);
    }

    public static void main(String[] args) {
        isUserEqualNull();
        badUsageOptional();
        User user = null;
        // 这种方法创建一个空的Optional对象
        Optional<Object> optionalEmpty = Optional.empty();
        // 这种创建Optional方法，如果user对象是空就会产生空指针异常
        // Optional<User> userOptional = Optional.of(user);
        // ofNullable 方法：如果user 是空的，就会创建一个空的Optional容器对象，
        // 如果不为空就返回这个对象的Optional容器
        Optional<User> optionalUser = Optional.ofNullable(user);
        // 如果optionalUser 容器里面是空的就返回new User()这个对象，如果不为空，就正常返回；换句话说，存在即返回，空则提供默认值
        // 上面的isPresent()方法就可以被orElse()替换
        optionalUser.orElse(new User());
        // 存在就返回，不存在就返回函数体内提供的对象
        optionalUser.orElseGet(() -> new User());
        // 存在就返回，不存在就抛出异常
        // optionalUser.orElseThrow(RuntimeException::new);
        // 存在才会执行后面这个函数体
        optionalUser.ifPresent(u -> System.out.println(u.getName()));
        // 如果u.getname为null 才返回hello
        optionalUser.map(u -> u.getName()).orElse("hello");
        // 如果c.length 为null,
        optionalUser.map(u -> u.getName()).map(c -> c.length()).orElse(0);
    }
}

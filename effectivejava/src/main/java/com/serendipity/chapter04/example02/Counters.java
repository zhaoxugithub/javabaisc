package com.serendipity.chapter04.example02;

/**
 * 通过工厂方法暴露实例，而非暴露实现类。
 * 好处：可在不破坏客户端代码的情况下更换实现。
 *
 * 仅接口是 public；实现类是包私有（默认访问级别）。
 * 通过工厂方法返回接口类型，降低外部对实现的依赖。
 */
public class Counters {
    private Counters() {
    }

    public static Counter newCounter() {
        // 将来可切换成 LongAdderCounter 等实现
        return new AtomicCounter();
    }


    /*

   我想开发一个可以导入excel文件的页面工具，不局限excel格式，可以在页面上自己配置导入格式，灵活导入，生成导入匹配结果和进度，导入的数据放在mysql里面
   我应该怎么设计？ 前端使用vue，后端使用java springboot

   包含前后端 有如下要求
   1. 支持页面页导入



     */
}

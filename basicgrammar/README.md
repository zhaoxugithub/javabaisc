# basicgrammar 模块文档

## 项目简介

`basicgrammar` 是 Java 基础学习模块，包含 **112 个 Java 源文件**，覆盖 Java 核心知识体系的 17 个主题领域。每个文件都包含 `main` 方法，可独立运行演示。

**根包路径：** `com.serendipity.myold`

---

## 目录结构

```
com/serendipity/myold/
├── ai/                          # AI/LLM 集成
├── annotation/                  # 注解
│   ├── annotation/              #   自定义注解定义
│   └── entity/                  #   注解测试实体
├── aop/                         # AOP / 代理模式
│   ├── cglib/                   #   CGLIB 动态代理
│   ├── dynamicProxy/            #   JDK 动态代理
│   │   ├── demo01/              #     基于对象实例的代理
│   │   ├── demo02/              #     基于 Class 的代理
│   │   └── demo03/              #     CGLIB 代理（Spring Enhancer）
│   └── staticproxy/             #   静态代理
├── base/                        # 核心基础 / OOP
│   └── oriented/                #   面向对象进阶
├── collection/                  # 集合框架
├── enums/                       # 枚举
├── exception/                   # 异常处理
│   ├── exce/                    #   异常基础与 NPE
│   └── try_with_resources/      #   try-with-resources
├── generics/                    # 泛型
│   └── base01/                  #   泛型基础
├── hutools/                     # Hutool 工具库
├── lambda/                      # Lambda 与 Stream
│   ├── ch01/ ~ ch09/            #   分章学习
├── log/                         # 日志
├── mock/                        # Mock 数据
│   └── entity/                  #   Mock 实体
├── reflect/                     # 反射
│   ├── invocationhandlerproxy/  #   InvocationHandler 代理
│   └── myreflect/               #   反射进阶
├── serializable/                # 序列化
└── utils/                       # 工具类
```

---

## 模块详解

### 1. AI/LLM 集成 (1 文件)

通过 HttpURLConnection 调用本地大模型 API，演示 Java 与 LLM 的集成方式。

| 文件 | 说明 |
|------|------|
| `ai/LocalLLMJavaApiTest.java` | 调用本地 Ollama 大模型 API 进行对话测试，使用 Jackson 解析 JSON 响应 |

**关键概念：** HttpURLConnection、JSON 解析、大模型 API 调用

---

### 2. 注解 Annotation (17 文件)

自定义注解定义、元注解使用、反射读取注解信息的完整学习路径。

**自定义注解定义：**

| 文件 | 说明 |
|------|------|
| `annotation/annotation/Hello1.java` | @Target(TYPE) 限制只能修饰类/接口 |
| `annotation/annotation/Hello2.java` | @Retention(RUNTIME) 运行时保留，含 type/level/value 属性 |
| `annotation/annotation/Hello3.java` | @Target(METHOD,FIELD) 可修饰方法和字段 |
| `annotation/annotation/Hellos.java` | 注解容器，用于重复注解 |
| `annotation/annotation/MyMethodAnnotation.java` | @Target(METHOD) 含 title 和 description 属性 |
| `annotation/annotation/TestDocAnnotation.java` | @Documented 元注解示例 |
| `annotation/annotation/TestInheritedAnnotation.java` | @Inherited 元注解示例，演示四个元注解用法 |

**注解使用与演示：**

| 文件 | 说明 |
|------|------|
| `annotation/AnnotationDemo.java` | 综合演示：JMockData 生成对象 + 反射读取注解 |
| `annotation/HelloAnnotationDemo.java` | 反射获取字段和方法上的 @Hello2 注解信息 |
| `annotation/MyMethodAnnotationDemo.java` | ClassLoader 加载类并反射获取 @MyMethodAnnotation 详情 |
| `annotation/TestDocAnnotationDemo.java` | @Documented 注解的作用，反射过滤带注解的方法 |
| `annotation/TestInheritedAnnotationDemo.java` | @Inherited 注解继承性：父类注解自动传递给子类 |
| `annotation/TestMethodAnnotationDemo.java` | 反射获取方法注解的 title 等详细信息 |
| `annotation/TypeParameterAndTypeUseAnnotationDemo.java` | TYPE_PARAMETER 和 TYPE_USE 两种注解目标的区别 |

**测试实体：**

| 文件 | 说明 |
|------|------|
| `annotation/entity/Person.java` | 注解测试实体，含 name/age/remark/email 字段 |
| `annotation/entity/Student.java` | 继承 Person，测试 @Inherited 注解继承性 |
| `annotation/entity/Teacher.java` | 空占位实体类 |

**关键概念：** 元注解（@Target / @Retention / @Documented / @Inherited）、注解容器、反射读取注解

---

### 3. AOP / 代理模式 (12 文件)

从静态代理到 JDK 动态代理、CGLIB 代理的完整演进。

**静态代理：**

| 文件 | 说明 |
|------|------|
| `aop/staticproxy/InterfaceA.java` | 代理示例接口，定义 run 和 play 方法 |
| `aop/staticproxy/InterfaceImpl.java` | InterfaceA 的实现类 |
| `aop/staticproxy/InterfaceImplProxy.java` | 静态代理类，在目标方法前后打印日志 |
| `aop/staticproxy/Main.java` | 静态代理演示入口，说明手写代理类的局限性 |

**JDK 动态代理：**

| 文件 | 说明 |
|------|------|
| `aop/cglib/DynamicProxyDemo.java` | JDK Proxy + InvocationHandler 实现动态代理 |
| `aop/dynamicProxy/demo01/DynamicProxyHandler.java` | 泛型代理处理器，接受代理对象实例 |
| `aop/dynamicProxy/demo01/DynamicProxyHandlerTest.java` | 测试方法耗时统计代理 |
| `aop/dynamicProxy/demo02/DynamicProxyHandler.java` | 改进版，接受 Class 类型参数，通过反射创建代理 |
| `aop/dynamicProxy/demo02/DynamicProxyHandlerTest.java` | 基于 Class 的动态代理测试 |

**CGLIB 代理：**

| 文件 | 说明 |
|------|------|
| `aop/dynamicProxy/demo03/CgLibDynamicProxyHandler.java` | Spring Enhancer + MethodInterceptor，无需接口 |
| `aop/dynamicProxy/demo03/CgLibDynamicProxyHandlerTest.java` | 验证静态方法 AOP 不生效 |
| `aop/dynamicProxy/demo03/PersonService.java` | CGLIB 测试目标类，含 public/private/static 方法 |
| `aop/dynamicProxy/demo03/ProductService.java` | CGLIB 测试目标类，含 addProduct 方法 |

**关键概念：** 静态代理 vs 动态代理、JDK Proxy vs CGLIB、InvocationHandler、MethodInterceptor

---

### 4. 核心基础 / OOP (14 文件)

面向对象编程四大特性的演示：抽象、继承、封装、多态。

| 文件 | 说明 |
|------|------|
| `base/AbstractionDemo.java` | 抽象概念：EntityManager 泛型、Predicate 函数式接口、URL 编码 |
| `base/DateTimeDemo.java` | Java 8 日期时间 API：Clock/Instant/LocalDate/LocalTime/LocalDateTime |
| `base/InheritanceDemo.java` | 继承 + 模板方法模式：Shape 抽象类，Circle/Rectangle 子类 |
| `base/IntegerDataTypeDemo.java` | Integer 数据类型：== 比较陷阱、-128~127 缓存池、自动装箱 |
| `base/MultiThreadDemo.java` | 多线程基础：ThreadMXBean 获取 Java 线程信息 |
| `base/NashornDemo.java` | Nashorn 脚本引擎：执行 JavaScript 代码 |
| `base/OptionalDemo.java` | Optional 容器：ofNullable/isPresent/orElse 防止 NPE |
| `base/oriented/FieldAccessDemo.java` | 字段访问：子类与父类同名字段时直接访问 vs 方法调用 |
| `base/oriented/SandwichDemo.java` | 对象加载顺序：父类静态→子类静态→父类构造→子类构造 |
| `base/PolymorphismDemo.java` | 多态 + 策略模式：Order 类通过不同 PaymentStrategy 运行时绑定 |
| `base/PrivateOverrideDemo.java` | private 方法不支持重写 |
| `base/StrategizeDemo.java` | 策略模式 + lambda 替代设计模式 |
| `base/StreamsDemo.java` | Stream API 基础：Task 对象按 Status 过滤、map/reduce |
| `base/SystemDemo.java` | System 类 API：clone、arraycopy、Properties、Console |
| `base/TerminationConditionDemo.java` | 终结条件：finalize() 方法检测未归还书籍，GC 清理机制 |

**关键概念：** 封装/继承/多态/抽象、模板方法模式、策略模式、Integer 缓存池、对象加载顺序

---

### 5. 集合框架 Collection (6 文件)

Java 集合框架核心接口及实现类的使用演示。

| 文件 | 说明 |
|------|------|
| `collection/BitMapTest.java` | BitSet 位图：大规模数据位操作，length 和 cardinality |
| `collection/CollectionDemo.java` | 集合框架概述 |
| `collection/ListDemo.java` | ArrayList 和 LinkedList 使用，Guava Lists 工具 |
| `collection/MapDemo.java` | HashMap、BiFunction 合并操作，Guava Maps 工具 |
| `collection/QueueTest.java` | ArrayBlockingQueue 的 remove/poll/offer/put 方法对比 |
| `collection/SetDemo.java` | LinkedHashSet 保持插入顺序、HashSet 去重特性 |

**关键概念：** List / Set / Map / Queue、BitSet 位图、Guava 集合工具

---

### 6. 枚举 Enum (2 文件)

从基础到进阶的枚举用法。

| 文件 | 说明 |
|------|------|
| `enums/EnumsDemo01.java` | 对比常量类和 enum 定义，enum 编译为 final class extends Enum |
| `enums/EnumsDemo02.java` | 枚举含构造方法/字段/方法、name()/ordinal()、switch 中使用 |

**关键概念：** enum 本质、构造方法、name/ordinal、switch 支持

---

### 7. 异常处理 Exception (14 文件)

异常体系、NPE 场景分析、Optional 解决方案、try-with-resources。

**异常基础：**

| 文件 | 说明 |
|------|------|
| `exception/exce/BaseException.java` | 自定义 RuntimeException 基类 |
| `exception/exce/ExceptionDemo01.java` | Integer.parseInt 异常捕获 |
| `exception/exce/ExceptionProcess.java` | 异常处理机制：抛出本质、try-catch-finally 流程 |
| `exception/exce/ForeachOptimize.java` | 集合遍历 ConcurrentModificationException 及优化 |
| `exception/exce/GeneralException.java` | HashMap/ArrayList 操作异常场景 |
| `exception/exce/NumberAndTime.java` | BigDecimal 精度问题、SimpleDateFormat 线程安全 |
| `exception/exce/StaffType.java` | 员工类型枚举(RD/QA/PM/OP)，辅助类 |

**NPE 专题：**

| 文件 | 说明 |
|------|------|
| `exception/exce/BasicUsageNpe.java` | 字符串 equals、对象属性未初始化导致的 NPE |
| `exception/exce/NullPointerExceptionDemo02.java` | Optional 通过 isPresent 解决 NPE |
| `exception/exce/OptionUsage.java` | Optional 正确用法，User 对象属性安全访问 |
| `exception/exce/UnboxingNpe.java` | 自动拆箱 NPE：Integer 为 null 时拆箱为 int |
| `exception/exce/WhatIsNpe.java` | 深度理解 NPE：属性、数组元素、方法调用场景 |

**try-with-resources：**

| 文件 | 说明 |
|------|------|
| `exception/try_with_resources/AutoClose.java` | AutoCloseable 实现类，close() 和 work() 异常处理 |
| `exception/try_with_resources/Main.java` | 对比传统 try-finally 和 AutoCloseable 自动关闭 |
| `exception/try_with_resources/MyException.java` | 自定义 checked 异常类 |

**关键概念：** 异常体系 (Checked/Unchecked)、NPE 常见场景与防范、try-with-resources、AutoCloseable

---

### 8. 泛型 Generics (19 文件)

从泛型基础到擦除、反射绕过泛型检查的完整知识体系。

**泛型基础 (base01)：**

| 文件 | 说明 |
|------|------|
| `generics/base01/GenericsBase01.java` | 对比重载和泛型方法解决类型无关的 add 操作 |
| `generics/base01/GenericsBase02.java` | 泛型类 Point\<T\> 定义与实例化 |
| `generics/base01/GenericsBase03.java` | 泛型接口 Info\<T\> 和 InfoImpl\<T\> |
| `generics/base01/GenericsBase04.java` | 泛型方法 `<T> T getObject(Class<T>)` 反射创建实例 |
| `generics/base01/GenericsBase05.java` | 泛型协变/逆变：List\<A\> 和 List\<B\> 的类型关系 |
| `generics/base01/GenericsBase06.java` | 泛型数组限制 |
| `generics/base01/GenericsBase07.java` | 泛型擦除：ParameterizedType 反射获取运行时类型 |

**泛型进阶：**

| 文件 | 说明 |
|------|------|
| `generics/GenericsTest_01.java` | 返回类型前 `<T>` 声明 vs 类名后 `<E>` 声明 |
| `generics/GenericsTest_02.java` | 泛型继承：A/B 继承关系在 List\<A\> 和 List\<B\> 中不兼容 |
| `generics/GenericsTest_03.java` | 反射绕过泛型检查，向 ArrayList\<String\> 添加 Integer |
| `generics/GenericsTest_04.java` | 泛型方法重载签名区别 |
| `generics/GenericsTest_05.java` | 泛型可变参数 `<T> T[] fun1(T... arg)` |
| `generics/GenericsTest_06.java` | Array.newInstance(Class\<T\>, int) 类型令牌创建泛型数组 |
| `generics/GenericsTest_07.java` | 泛型擦除验证：ArrayList\<String\> 和 ArrayList\<Integer\> getClass 相同 |
| `generics/GenericsTest_08.java` | 泛型类型安全：编译时检查阻止添加非 String 元素 |
| `generics/GenericsTest_09.java` | 泛型不变性：ArrayList\<String\> 不能赋值给 ArrayList\<Object\> |
| `generics/GenericsTest_10.java` | Pair 泛型类：类型推断和 Date 类型参数绑定 |
| `generics/Pair01_Main.java` | 所有泛型实例 getClass 返回同一个 Class |
| `generics/Pair02_Main.java` | Pair02 泛型容器：两个 T 类型字段 first/last |

**关键概念：** 泛型类/接口/方法、类型擦除、通配符(? extends/super)、类型令牌、反射绕过泛型

---

### 9. Hutool 工具库 (4 文件)

Hutool 工具库在类型转换、对象拷贝、日期处理、IO 操作中的应用。

| 文件 | 说明 |
|------|------|
| `hutools/HutoolCoreConvert.java` | 类型转换：Convert.toStr/toInt/toLong/toDate |
| `hutools/HutoolCoreCopyClass.java` | 对象拷贝：BeanUtil.copyProperties 属性复制、集合拷贝 |
| `hutools/HutoolCoreDateUtilTest.java` | 日期工具：DateUtil.date/now/formatBetween/DatePattern |
| `hutools/HutoolCoreIOUtil.java` | IO 工具：FileUtil.getInputStream/IoUtil.copy 文件流复制 |

**关键概念：** Convert 类型转换、BeanUtil 对象拷贝、DateUtil 日期操作、FileUtil/IOUtil 文件操作

---

### 10. Lambda 与 Stream (15 文件)

从 Lambda 基础到 Stream API、函数式接口、响应式编程的系统学习。

**Lambda 基础 (ch01)：**

| 文件 | 说明 |
|------|------|
| `lambda/ch01/Employee.java` | 实体类：含 status(FREE/BUSY/VOCATION) 枚举 |
| `lambda/ch01/MyPredicate.java` | 自定义 @FunctionalInterface MyPredicate\<T\> |
| `lambda/ch01/TestLambda1.java` | Lambda 入门：匿名内部类 vs Lambda 创建 Comparator |
| `lambda/ch01/TestLambda2.java` | Stream API 综合：filter/map/reduce/sorted/distinct/collect |

**函数式接口 (ch02-ch03)：**

| 文件 | 说明 |
|------|------|
| `lambda/ch02/TestLambda2.java` | 四大核心函数式接口：Consumer/Supplier/Function/Predicate |
| `lambda/ch03/TestLambda3.java` | 方法引用：对象::实例方法、类::静态方法、构造器引用 |

**Optional (ch04)：**

| 文件 | 说明 |
|------|------|
| `lambda/ch04/TestLambda4.java` | Optional 详解：of/ofNullable/empty/isPresent/orElse/flatMap |

**Stream 操作 (ch05-ch07)：**

| 文件 | 说明 |
|------|------|
| `lambda/ch05/TestLambda5.java` | Stream 创建：数组 Stream、集合 Stream、sorted/peek/limit/skip |
| `lambda/ch06/TestLambda6.java` | Stream 三步：创建→中间操作(filter/map)→终止操作(forEach/collect) |
| `lambda/ch07/TestLambda01.java` | Stream 进阶：IntStream/range/collect/groupBy/partitioningBy |
| `lambda/ch07/TestLambda02.java` | Collectors.toList/toSet/toMap/groupingBy 分区 |

**函数式进阶与响应式 (ch08-ch09)：**

| 文件 | 说明 |
|------|------|
| `lambda/ch08/TestLambda01.java` | BiFunction/Function 组合、Supplier 链式调用 |
| `lambda/ch09/TestReactive01.java` | 响应式编程：Reactor 框架 Flux/Mono 创建、subscribe、操作符 |

**关键概念：** Lambda 语法、函数式接口、方法引用、Stream 操作三步曲、Reactor 响应式编程

---

### 11. 日志 Logging (2 文件)

JDK 自带日志与第三方日志框架概述。

| 文件 | 说明 |
|------|------|
| `log/LogDemo01.java` | JDK logging：java.util.logging.Logger，日志级别 SEVERE/WARNING/INFO |
| `log/LogDemo02.java` | 第三方日志概述：Commons Logging 六级日志 (FATAL→TRACE) |

**关键概念：** JUL (java.util.logging)、日志级别、Commons Logging / SLF4J 门面

---

### 12. Mock 数据 (3 文件)

使用 JMockData 生成测试数据。

| 文件 | 说明 |
|------|------|
| `mock/entity/BasicBean.java` | 测试实体：包含所有基本类型/包装类型/数组/List/Set/Map 等 150+ 字段 |
| `mock/MockDataBase.java` | JMockData 基础：mock 基本类型、BigDecimal/Date/String |
| `mock/MockTest.java` | 结合 Hutool ProxyUtil + TimeIntervalAspect 测试 mock 性能 |

**关键概念：** JMockData、Mock 数据生成、基本类型与集合类型 Mock

---

### 13. 反射 Reflection (11 文件)

Class 对象获取、字段/方法/构造器反射、动态代理。

| 文件 | 说明 |
|------|------|
| `reflect/ClassAPI.java` | Class API：getFields/getDeclaredFields/getMethods/getConstructors |
| `reflect/CreateClassObject.java` | 创建 Class 对象三种方式：forName / .class / getClass |
| `reflect/ReflectDemo01.java` | 反射调用方法：Method.invoke + getDeclaredMethod 获取私有方法 |
| `reflect/ReflectDemo02.java` | Hello 接口定义（反射动态代理起点） |
| `reflect/myreflect/ConstructionTest.java` | 构造器反射：getConstructor/getDeclaredConstructor |
| `reflect/myreflect/ReflectDemo03.java` | 反射获取字段：getDeclaredFields/getFields 在继承体系中的区别 |
| `reflect/myreflect/ReflectDemo04.java` | Class 名称：getSimpleName/getName/getCanonicalName 对内部类/数组的不同 |
| `reflect/invocationhandlerproxy/Main.java` | InvocationHandler 动态代理：lambda 实现方法拦截 |

**测试实体：**

| 文件 | 说明 |
|------|------|
| `reflect/Person.java` | Person 基类：name/age 公共字段 |
| `reflect/Student.java` | 继承 Person：classRoom/address + 公有/私有构造方法 |
| `reflect/Teacher.java` | Teacher：tid/tname 私有字段 + getter/setter |

**关键概念：** Class 对象、反射获取字段/方法/构造器、Method.invoke、动态代理

---

### 14. 序列化 Serialization (3 文件)

Java 序列化机制与 transient 关键字。

| 文件 | 说明 |
|------|------|
| `serializable/SerializableDemo01.java` | 数组序列化：ObjectOutputStream 写入/读取 Integer[]，transient 跳过序列化 |
| `serializable/SerializableDemo02.java` | 对象序列化：transient 修饰 password 跳过序列化 |
| `serializable/SerializableUtils.java` | 序列化工具类：封装 ObjectOutputStream/ObjectInputStream |

**关键概念：** Serializable 接口、transient 关键字、ObjectOutputStream / ObjectInputStream

---

### 15. 工具类 Utils (5 文件)

常用工具类和辅助方法。

| 文件 | 说明 |
|------|------|
| `utils/ArrayUtilsDemo1.java` | Arrays：parallelSort/sort/parallelPrefix/IntUnaryOperator 并行数组操作 |
| `utils/ArrayUtilsDemo2.java` | Arrays：binarySearch/compare/copyOf/copyOfRange/equals/sort |
| `utils/SpringUtils.java` | Spring Assert.notNull 断言、instanceof 模式匹配 |
| `utils/StringUtilsDemo01.java` | StringUtils 占位类 |
| `utils/UUIDUtils.java` | UUID.randomUUID() 生成 32 位去横线唯一标识符 |

**关键概念：** Arrays 工具类、并行数组操作、UUID 生成、Spring Assert、模式匹配

---

### 16. 脚本引擎 Nashorn (1 文件)

通过 ScriptEngineManager 在 Java 中执行 JavaScript 代码。

| 文件 | 说明 |
|------|------|
| `base/NashornDemo.java` | ScriptEngineManager 获取 Nashorn 引擎执行 JavaScript |

> 注意：Nashorn 在 Java 11 中被标记为废弃，Java 15 中移除。推荐使用 GraalJS 替代。

**关键概念：** ScriptEngineManager、Nashorn、Java 脚本执行

---

### 17. 多线程基础 (1 文件)

线程基础概念和 API 演示。

| 文件 | 说明 |
|------|------|
| `base/MultiThreadDemo.java` | ThreadMXBean 获取所有 Java 线程信息并打印线程 ID 和名称 |

> 注意：完整的并发编程示例（线程池、锁、CompletableFuture 等）位于 `thread` 模块。

**关键概念：** ThreadMXBean、线程信息获取

---

## 技术栈

| 依赖 | 版本 | 用途 |
|------|------|------|
| **Spring Boot** | 3.4.0 | 核心框架，提供 Assert、Enhancer 等工具 |
| **Lombok** | 1.18.30 | 注解驱动的代码生成（@Data / @Slf4j 等） |
| **Hutool** | 5.8.25 | 国产工具库：类型转换、Bean 拷贝、日期、IO |
| **Guava** | 32.1.3-jre | Google 核心库：集合工具、函数式编程 |
| **Jackson Databind** | 2.15.2 | JSON 序列化/反序列化 |
| **JMockData** | - | Mock 测试数据自动生成 |
| **Project Reactor** | (随 Spring Boot) | 响应式编程：Flux/Mono |
| **JUnit Jupiter** | (随 Spring Boot) | 单元测试框架 |
| **Logback** | (随 Spring Boot) | 日志实现（SLF4J 后端） |

---

## 快速运行

```bash
# 编译 basicgrammar 模块
mvn compile -pl basicgrammar

# 运行指定示例
mvn exec:java -pl basicgrammar -Dexec.mainClass="com.serendipity.myold.lambda.ch01.TestLambda1"
```

大多数类包含 `main` 方法，也可直接在 IDE 中右键运行。

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a multi-module Maven project for learning and practicing Java concepts. It is organized as a collection of demo code examples rather than a production application. Each module focuses on a specific area of Java knowledge.

**Java Version:** JDK 21

**Root Package:** `com.serendipity.*`

## Module Structure

The project contains 9 modules:

| Module | Purpose |
|--------|---------|
| `basicgrammar` | Java fundamentals: annotations, AOP/proxy, collections, exceptions, generics, lambda, streams, Optional, reactive programming |
| `jvm` | JVM internals: bytecode, classloader hierarchy, JMM (memory model), GC algorithms, runtime data areas |
| `thread` | Concurrency: thread basics, synchronization, locks, thread pools, CompletableFuture, atomic operations, concurrent collections |
| `netty` | Network programming: BIO/NIO, ByteBuffer, Netty framework, reactor patterns, HTTP servers |
| `leetcode` | LeetCode algorithm problems organized by topic (DP, graph, heap, sorting, tree, greedy algorithms) |
| `effectivejava` | Examples from "Effective Java" book chapters (object creation, builders, singletons, static factories) |
| `designerPattern` | Design patterns: creational (factory, builder, singleton), action (command, iterator, observer, strategy, template), structural (adapter, decorator, proxy) |
| `commonUtils` | Utility library demos: Guava, Caffeine cache, Apache Commons (BeanUtils, StringUtils), Hutool |
| `newFeature` | Java 15-21 features: sealed classes, records, pattern matching for switch, virtual threads, string templates |

## Build Commands

```bash
# Compile all modules
mvn compile

# Compile a specific module
mvn compile -pl basicgrammar

# Clean and compile
mvn clean compile

# Package all modules
mvn package

# Package a specific module (skip tests)
mvn package -pl basicgrammar -DskipTests
```

## Running Demo Classes

Most classes contain a `main` method for demonstration. Run directly from IDE or via Maven exec plugin:

```bash
# Run a specific class using exec plugin
mvn exec:java -pl basicgrammar -Dexec.mainClass="com.serendipity.myold.lambda.ch01.TestLambda1"
```

## Dependencies

Key dependencies used across modules:
- **Lombok** (1.18.30) - annotation-based code generation
- **JUnit** 4.x and 5.x - testing
- **Logback/Log4j2** - logging
- **Hutool** (5.8.25) - Chinese utility library
- **Guava** (32.1.3-jre) - Google core libraries
- **Caffeine** (2.9.3) - high-performance caching
- **Spring Boot** (3.4.0) - used in basicgrammar module
- **Netty** (4.1.49.Final) - used in netty module

## Package Naming Convention

Each module uses sub-packages under `com.serendipity.*`:
- `com.serendipity.myold.*` - older code in basicgrammar
- `com.serendipity.learn.*` - learning examples in jvm, thread, leetcode
- `com.serendipity.*` (direct) - topic-specific packages in netty, designerPattern, newFeature

## Notes

- The `newFeature` module requires `--enable-preview` compiler flag for preview features
- No separate `src/test/java` directories exist; test/demo code runs via main methods
- Many files use Chinese comments and naming (e.g., "zuochengyun" algorithms)
# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Structure

This is a Java learning repository organized as a Maven multi-module project with the following modules:
- `basicgrammar` - Java basic syntax and grammar examples
- `jvm` - JVM concepts and internals
- `leetcode` - LeetCode algorithm solutions and data structures
- `netty` - Netty networking framework examples
- `thread` - Java concurrency and threading examples
- `effectivejava` - Effective Java patterns and best practices
- `designerPattern` - Design patterns implementation (creational, structural, behavioral)
- `commonUtils` - Common utility classes and helpers
- `newFeature` - Java new features exploration
- `groovylearn` - Groovy language learning

## Build System

**Maven-based build system** with Java 21:
- Main POM: `pom.xml` (parent POM with dependency management)
- Each module has its own `pom.xml`
- Uses Maven for dependency management and builds

**Key Maven Commands:**
- Build all modules: `mvn clean compile`
- Run tests: `mvn test`
- Package: `mvn package`
- Install dependencies: `mvn install`

## Dependencies

Common dependencies managed in parent POM:
- **Lombok** - Code generation and boilerplate reduction
- **Hutool** - Chinese utility library
- **Logback** - Logging framework
- **JMockData** - Mock data generation
- **Guava** - Google utilities
- **Caffeine** - Caching library
- **Apache Commons** - Lang3 and BeanUtils
- **JUnit 5** - Testing framework
- **Spring Boot** - Framework components

## Development Environment

- **Java Version**: 21
- **Build Tool**: Maven
- **IDE**: IntelliJ IDEA (`.idea` directory present)
- **Code Style**: Standard Java conventions with Lombok annotations

## Module-Specific Notes

### designerPattern
Contains comprehensive design pattern implementations organized by category:
- **Creational**: Singleton, Factory, Builder, Prototype
- **Structural**: Adapter, Composite, Decorator
- **Behavioral**: Command, COR, Iterator, Mediator, Observer, Strategy, Template Method

### leetcode
Algorithm and data structure implementations from Ma士兵's data structure course.

### thread
Java concurrency examples including thread pools, locks, and concurrent collections.

### netty
Netty networking framework examples and NIO buffer exercises.
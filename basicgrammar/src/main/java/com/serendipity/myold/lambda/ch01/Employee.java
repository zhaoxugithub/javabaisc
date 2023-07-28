package com.serendipity.myold.lambda.ch01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    public enum Status {
        FREE, BUSY, VOCATION;
    }

    private Integer id;
    private String name;
    private Integer age;
    private double salary;
    private Status status;

    public Employee(String name, Integer age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 && Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(age, employee.age) && status == employee.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary, status);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", salary=" + salary + ", status=" + status + '}';
    }
}

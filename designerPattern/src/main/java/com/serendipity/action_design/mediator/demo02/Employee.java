package com.serendipity.action_design.mediator.demo02;

public abstract class Employee {
    private String name;
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }


}

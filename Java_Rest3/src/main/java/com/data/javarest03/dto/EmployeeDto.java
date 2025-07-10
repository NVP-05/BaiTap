package com.data.javarest03.dto;

public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Double salary;

    public EmployeeDto(Long id, String name, String email, Double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Double getSalary() {
        return salary;
    }
}

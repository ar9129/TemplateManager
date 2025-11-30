package com.aditya.demo.model;



public class Department {
    private String Name ;
    private Integer Capacity;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCapacity() {
        return Capacity;
    }

    public void setCapacity(Integer capacity) {
        Capacity = capacity;
    }

    public Department(String name, Integer capacity) {
        Name = name;
        Capacity = capacity;
    }

    @Override
    public String toString() {
        return "Department{" +
                "Name='" + Name + '\'' +
                ", Capacity=" + Capacity +
                '}';
    }
}

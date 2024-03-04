package org.example.carloanapplication;

public class Vehicle {
    private String type;
    private String age;
    private double price;

    public Vehicle(String type, String age, double price) {
        this.type = type;
        this.age = age;
        this.price = price;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
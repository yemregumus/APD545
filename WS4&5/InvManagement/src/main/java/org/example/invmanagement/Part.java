package org.example.invmanagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({InHouse.class, Outsourced.class})
public abstract class Part {

    protected String companyName;
    protected int machineId;
    private String id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Part() {
        // no-arg constructor
    }
    public Part(String name, double price, int stock, int min, int max) {
        this.id = generateUniqueId();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @XmlElement
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @XmlElement
    public int getMachineId() {
        return machineId;
    }
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    private String generateUniqueId() {
        Random random = new Random();
        int id = random.nextInt(900000) + 100000; // This will generate a random 6-digit number
        return String.valueOf(id);
    }

}
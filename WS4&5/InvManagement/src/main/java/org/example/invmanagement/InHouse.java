package org.example.invmanagement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InHouse extends Part {
    private int machineId = 0;

    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(name,price,stock,min,max);
        this.machineId = machineId;
    }
    public InHouse() {
        // no-arg constructor
    }

    @Override
    public int getMachineId() {
        return machineId;
    }


    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}

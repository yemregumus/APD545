package org.example.invmanagement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Outsourced extends Part {
    private String companyName="";

    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name,price,stock,min,max);
        this.companyName = companyName;
    }

    public Outsourced() {
        // no-arg constructor
    }
    @Override
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

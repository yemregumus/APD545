package org.example.invmanagement;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.invmanagement.Part;

import java.util.List;

@XmlRootElement
public class AllDataWrapper {
    private List<Part> parts;
    private List<Product> products;

    // JAXB needs a no-arg constructor
    public AllDataWrapper() {}

    public AllDataWrapper(List<Part> parts, List<Product> products) {
        this.parts = parts;
        this.products= products;
    }

    @XmlElementWrapper(name = "AllParts")
    @XmlElement(name = "part")
    public List<Part> getParts() {
        return parts;
    }
    @XmlElementWrapper(name = "AllProducts")
    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
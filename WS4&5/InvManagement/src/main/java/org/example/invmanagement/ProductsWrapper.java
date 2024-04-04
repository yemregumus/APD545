package org.example.invmanagement;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.invmanagement.Product;

import java.util.List;

@XmlRootElement
public class ProductsWrapper {
    private List<Product> products;

    // JAXB needs a no-arg constructor
    public ProductsWrapper() {}

    public ProductsWrapper(List<Product> products) {
        this.products = products;
    }

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
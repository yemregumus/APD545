package org.example.invmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventory {
    private final List<Part> allParts;
    private final List<Product> allProducts;

    public Inventory() {
        this.allParts = new ArrayList<>();
        this.allProducts = new ArrayList<>();
    }

    public void addPart(Part part) {
        allParts.add(part);
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public Part lookupPartById(String partId) {
        for (Part part : allParts) {
            if (part.getId().equals(partId)) {
                return part;
            }
        }
        return null;
    }

    public List<Part> lookupPartByName(String partName) {
        List<Part> matchingParts = new ArrayList<>();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    public Product lookupProductById(String productId) {
        for (Product product : allProducts) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> lookupProduct(String productName) {
        List<Product> matchingProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public void deletePart(Part selectedPart) {
        try{
            allParts.remove(selectedPart);
            System.out.println("Part deleted");
        } catch (Exception e) {
            System.out.println("Part not found");
        }

    }

    public void deleteProduct(Product selectedProduct) {
        try{
            allProducts.remove(selectedProduct);
            System.out.println("Product deleted");
        } catch (Exception e) {
            System.out.println("Product not found");
        }

    }

    public List<Part> getAllParts() {
        return allParts;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllParts(List<Part> allParts) {
        this.allParts.addAll(allParts);
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts.addAll(allProducts);
    }
}

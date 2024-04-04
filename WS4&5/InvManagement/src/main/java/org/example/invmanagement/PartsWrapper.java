package org.example.invmanagement;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.invmanagement.Part;

import java.util.List;

@XmlRootElement(name = "parts")
public class PartsWrapper {
    private List<Part> parts;

    // JAXB needs a no-arg constructor
    public PartsWrapper() {}

    public PartsWrapper(List<Part> parts) {
        this.parts = parts;
    }


    @XmlElement(name = "part")
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
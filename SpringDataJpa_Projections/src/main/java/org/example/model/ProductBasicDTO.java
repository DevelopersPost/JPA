package org.example.model;


import org.springframework.data.annotation.PersistenceCreator;

public class ProductBasicDTO {
    private String name;
    private Double price;

    @PersistenceCreator
    public ProductBasicDTO(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}


package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private final Long id;
    private final String name;
    private final Double price;
    private final CategoryDTO category;

    public ProductDTO(Long id, String name, Double price, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

}

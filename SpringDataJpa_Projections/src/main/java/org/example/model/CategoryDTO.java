package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private final Long id;
    private final String name;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

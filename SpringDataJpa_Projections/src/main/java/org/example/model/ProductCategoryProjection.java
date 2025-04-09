package org.example.model;

import org.springframework.beans.factory.annotation.Value;

public interface ProductCategoryProjection {
    String getName();

    Double getPrice();

    CategoryProjection getCategory();

    interface CategoryProjection {
        Long getId();
        String getName();
    }
}


package org.example.model;

import org.springframework.beans.factory.annotation.Value;

public interface ProductView {

    String getName();

    Double getPrice();

    @Value("#{target.category.name}")
    String getCategoryName();

    @Value("#{target.price > 1000 ? 'Expensive' : 'Affordable'}")
    String getPriceCategory();
}

package org.example.model;

public class ProductCategoryDTO {
    private final String productNamex;
    private final Double productPricex;
    private final String categoryNamex;

    public ProductCategoryDTO(String name, Double price, String categoryName) {
        this.productNamex = name;
        this.productPricex = price;
        this.categoryNamex = categoryName;
    }

    public String getProductNamex() {
        return productNamex;
    }

    public Double getProductPricex() {
        return productPricex;
    }

    public String getCategoryNamex() {
        return categoryNamex;
    }
}


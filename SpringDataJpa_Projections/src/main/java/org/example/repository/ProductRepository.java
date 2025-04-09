package org.example.repository;

import org.example.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // interface based projection

    @Query("SELECT p.name AS name, p.price AS price, c.name AS categoryName FROM Product p JOIN p.category c WHERE c.name =:categoryName")
    List<BasicProductCategoryProjection> findBasicProductsByCategoryNameWithCustomQuery(@Param("categoryName") String categoryName);

   List<BasicProductCategoryProjection> findByCategoryName(@Param("categoryName")String categoryName);

     List<ProductCategoryProjection> findProductsByCategoryName(@Param("categoryName")String categoryName);

     @Query("SELECT p.name AS name, p.price AS price, c AS category FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    List<ProductCategoryProjection> findProductsByCategoryNameWithCustomQuery(@Param("categoryName") String categoryName);

    // DTO based projection

    @Query("SELECT new org.example.model.ProductCategoryDTO(p.name, p.price, c.name) FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    List<ProductCategoryDTO> findProductDTOsByCategoryNameWithCustomQuery(@Param("categoryName") String categoryName);

    List<ProductCategoryDTO> getProductsByCategoryName(@Param("categoryName") String categoryName);

    // below query for class based nested projection is not working (not supported by JPA)


    /*@Query("SELECT new com.example.dto.ProductDTO(p.id, p.name, p.price, new com.example.dto.CategoryDTO(c.id, c.name)) " +
            "FROM Product p JOIN p.category c")
    List<ProductDTO> findAllProductsWithCategory();*/


    // dynamic projection

    @Query("SELECT p.name AS name, p.price AS price, c AS category FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    <T> List<T> findByCategoryName(@Param("categoryName") String categoryName, Class<T> type);

    <T> List<T> findByCategory_Name(@Param("categoryName") String categoryName, Class<T> type);

    List<ProductView> findByCategory_Name(@Param("categoryName") String categoryName);

    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName")
    List<ProductView> findProductsByCategory1(@Param("categoryName") String categoryName);

}


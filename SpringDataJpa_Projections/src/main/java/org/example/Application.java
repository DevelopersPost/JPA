package org.example;

import org.example.model.*;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run() {
        return (x) -> {

        //     Inteface based projection


            List<BasicProductCategoryProjection> result1 = productRepository.findBasicProductsByCategoryNameWithCustomQuery("Electronics");
            result1.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategoryName()));

           List<BasicProductCategoryProjection> result2 = productRepository.findByCategoryName("Electronics");
            result2.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategoryName()));

             List<ProductCategoryProjection> result3 = productRepository.findProductsByCategoryName("Electronics");
            result3.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategory().getName() + " - "));

            List<ProductCategoryProjection> result4 = productRepository.findProductsByCategoryNameWithCustomQuery("Electronics");
            result4.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " +
                    product.getCategory().getName() + " - " + product.getCategory().getId()));



          //   Class based projection

            List<ProductCategoryDTO> result5 = productRepository.findProductDTOsByCategoryNameWithCustomQuery("Electronics");
            result5.forEach(product -> System.out.println(product.getProductNamex() + " - " + product.getProductPricex() + " - " + product.getCategoryNamex()));

            List<ProductCategoryDTO> result6 = productRepository.getProductsByCategoryName("Electronics");
            result6.forEach(product -> System.out.println(product.getProductNamex() + " - " + product.getProductPricex() + " - " + product.getCategoryNamex()));

            // below lines for nested projection will wont work as nested projection is not supported in class based projection

            /*List<ProductDTO> result7 = productRepository.findAllProductsWithCategory();
            result7.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategory().getName()));
*/


           //  Dynamic projection

            List<ProductCategoryBasicProjection> result8 = productRepository.findByCategoryName("Electronics", ProductCategoryBasicProjection.class);
            result8.forEach(product -> System.out.println(product.getName()));

            List<ProductCategoryProjection> result9 = productRepository.findByCategoryName("Electronics", ProductCategoryProjection.class);
            result9.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategory().getName()));

            List<ProductBasicDTO> result10 =productRepository.findByCategory_Name("Electronics", ProductBasicDTO.class);
            result10.forEach(product -> System.out.println(product.getName()+" - "+product.getPrice()));

            List<ProductCategoryDTO> result11 =productRepository.findByCategory_Name("Electronics", ProductCategoryDTO.class);
            result11.forEach(product -> System.out.println(product.getProductNamex() + " - " + product.getProductPricex() + " - " + product.getCategoryNamex()));

            List<BasicProductCategoryProjection> result12 =productRepository.findByCategory_Name("Electronics", BasicProductCategoryProjection.class);
            result12.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategoryName()));


            // open porjection

            List<ProductView> result13 = productRepository.findByCategory_Name("Electronics");
            result13.forEach(product -> System.out.println(product.getName() + " - " + product.getPrice() + " - " + product.getCategoryName() + " - " + product.getPriceCategory()));

        };
    }
}
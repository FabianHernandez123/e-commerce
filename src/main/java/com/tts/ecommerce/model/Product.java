package com.tts.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    private Integer quantity;
    //be cautious about using float for price
    private float price;

    private String brand;
    private String category;
    private String description;
    private String size;
    private String name;
    private String image;

    public Product(Long id, int quantity, float price, String description, String name, String brand, String category,
                   String image) {
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.image = image;
    }

}

package com.vertxpractise.starter.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
  private long id;
  private String name;
  private double price;
  private String description;

    public Product(long id , String name, double price, String description){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}

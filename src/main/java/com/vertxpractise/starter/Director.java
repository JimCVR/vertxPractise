package com.vertxpractise.starter;

import com.vertxpractise.starter.models.Order;
import com.vertxpractise.starter.models.Product;
import com.vertxpractise.starter.models.User;

import java.util.ArrayList;
import java.util.List;

public class Director {
  public List<Order> createOrderList() {
    List<Order> orderList = new ArrayList<>();
    List<Long> productsId = new ArrayList<>();
    List<Long> productsId2 = new ArrayList<>();
    List<Long> productsId3 = new ArrayList<>();
    productsId.add(1L);
    productsId.add(3L);
    productsId2.add(2L);
    Order order1 = new Order(1, productsId);
    Order order2 = new Order(2, productsId2);
    Order order3 = new Order(4, productsId3);
    orderList.add(order1);
    orderList.add(order2);
    orderList.add(order3);
    return orderList;
  }

  public List<Product> createProductList() {
    List<Product> productList = new ArrayList<>();
    Product product1 = new Product(1, "Milk Bundle", 5.42, "6-pack Milk");
    Product product2 = new Product(2, "Bread", 1.23, "16 bread slices");
    Product product3 = new Product(3, "Beer", 1.5, "2L Beer bottle");
    productList.add(product1);
    productList.add(product2);
    productList.add(product3);
    return productList;
  }

  public List<User> createUserList() {
    List<User> userList = new ArrayList<>();
    User user1 = new User(1, "Jaime", "jaime@gmail.com");
    User user2 = new User(2, "Pablo", "pablo@gmail.com");
    User user3 = new User(3, "Laura", "laura@gmail.com");
    userList.add(user1);
    userList.add(user2);
    userList.add(user3);
    return userList;
  }
}

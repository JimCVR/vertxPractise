package com.vertxpractise.starter.modules;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {
  private long userId;
  private List<Long> products;

    public Order(long userId, List<Long> products){
        this.userId = userId;
        this.products = products;
    }
}

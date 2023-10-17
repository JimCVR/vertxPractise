package com.vertxpractise.starter.modules;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
  private long id;
  private String name;
  private String email;

    public User(long id , String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
}

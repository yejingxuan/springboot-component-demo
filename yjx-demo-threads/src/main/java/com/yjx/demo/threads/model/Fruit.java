package com.yjx.demo.threads.model;

import lombok.Data;

@Data
public class Fruit {

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    private String name;
}

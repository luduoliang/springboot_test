package com.springboot_test.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" }) 
public class Hero {
	  
    private int id;
    private String name;
    private float hp;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public float getHp() {
        return hp;
    }
    
    public void setHp(float hp) {
        this.hp = hp;
    }
      
}
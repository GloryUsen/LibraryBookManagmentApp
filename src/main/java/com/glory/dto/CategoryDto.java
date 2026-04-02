package com.glory.dto;


public class CategoryDto {


    private Long id;
    private String name;


    public String getName(){
        return name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}

package com.glory.dto;

// import lombok.Data;
// import lombok.NoArgsConstructor;


// @Data
// @NoArgsConstructor

public class RoleDto {
 
    private Long id; 
    private String name;

    public RoleDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getid(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}

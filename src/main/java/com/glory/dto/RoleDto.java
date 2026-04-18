package com.glory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoleDto {





    
    public Long getId(){
        return id;
        
    }
    private Long id;

    public String getName(){
        return name;
        
    }
    
    private String name;

}

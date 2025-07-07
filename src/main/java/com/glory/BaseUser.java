package com.glory;

public class BaseUser {
    protected String name;
    protected int id;

    public BaseUser(String usernames, int userId){
        this.name = usernames;
        this.id = userId;

    }

    public void displayInfo(){
        System.out.println("User ID: " + id + "User Name: " + name);

    }
}

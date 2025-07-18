package com.glory;

public class BaseUser {
    protected String name;
    protected int id;

    public BaseUser(String usernames, int userId){
        this.name = usernames;
        this.id = userId;

    }

    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }

    public void displayInfo(){
        System.out.println("User ID: " + id + " User Name: " + name);

    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        BaseUser user = (BaseUser) object;
        return id == user.id;
        }

        public int hashCode(){
        return Integer.hashCode(id);


    }
}

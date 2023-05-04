package org.greenSoftware.dto;

public class UserDTO {
    private String name,
            email,
            password;
    private int level;
    
    public UserDTO(){}
    public UserDTO(String name,String email,String password,int level){
        this.name=name;
        this.email=email;
        this.password=password;
        this.level=level;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setLevel(int level){
        this.level=level;
    }
    public int getLevel(){
        return level;
    }
}
package org.greenSoftware.dto;

public class UserDTO {
    private String userName,
            email,
            password;
    private int level;
    
    public UserDTO(){}
    public UserDTO(String userName,String email,String password,int level){
        this.userName=userName;
        this.email=email;
        this.password=password;
        this.level=level;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
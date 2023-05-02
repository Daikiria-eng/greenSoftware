package org.greenSoftware.dto;

public class UserResponseDTO extends UserDTO{
    private boolean status;
    private String token;
    
    public UserResponseDTO(){}
    public UserResponseDTO(UserDTO user){
        super(user.getUserName(),user.getEmail(),user.getPassword(),user.getLevel());
    }
    public UserResponseDTO(UserDTO user,boolean status,String token){
        super(user.getUserName(),user.getEmail(),user.getPassword(),user.getLevel());
        this.status=status;
        this.token=token;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
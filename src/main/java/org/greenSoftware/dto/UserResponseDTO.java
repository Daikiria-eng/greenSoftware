package org.greenSoftware.dto;

public class UserResponseDTO extends UserDTO{
    private boolean valid;
    private String token;
    
    public UserResponseDTO(){}
    public UserResponseDTO(UserDTO user){
        super(user.getName(),user.getEmail(),user.getPassword(),user.getLevel());
    }
    public UserResponseDTO(UserDTO user,boolean valid,String token){
        super(user.getName(),user.getEmail(),user.getPassword(),user.getLevel());
        this.valid=valid;
        this.token=token;
    }
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
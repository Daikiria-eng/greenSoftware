package org.greenSoftware.dao.impl;

import java.security.NoSuchAlgorithmException;
import org.greenSoftware.constants.Constants;
import org.greenSoftware.dao.UserDAO;
import org.greenSoftware.dto.ResponseDTO;
import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.dto.UserResponseDTO;
import org.greenSoftware.repository.UserRepository;
import org.greenSoftware.util.Util;

public class UserDAOimpl implements UserDAO{
    UserRepository userRepo=new UserRepository();

    public ResponseDTO insertUser(UserDTO user) {
        user.setUserName(Util.escapeSpecialChars(user.getUserName()));
        if(Util.validateEmail(user.getEmail())){
            try {
                user.setPassword(Util.hashIt(user.getPassword()));
                user.setEmail(Util.escapeSpecialChars(user.getEmail()));

                return (userRepo.insertUser(user)!=1)?
                    new ResponseDTO(Constants.ERROR_STATUS,"Not inserted"):
                    new ResponseDTO(Constants.SUCCES_STATUS,"inserted");
            } catch (NoSuchAlgorithmException e) {
                System.out.println("[-] Error hashing password: "+e);
                e.printStackTrace();
            }

            return new ResponseDTO(Constants.ERROR_STATUS,"error creating password");
        }else
            return new ResponseDTO(Constants.ERROR_STATUS,"Verify email");
    }

    public UserResponseDTO validateUser(UserDTO user) {
        if(Util.validateEmail(user.getEmail())){
            Util.escapeSpecialChars(user.getEmail());

            return userRepo.validateUser(user);
        }else return new UserResponseDTO(user, false, null);
    }
    
    public static void main(String[] args) {
        UserDAOimpl ud=new UserDAOimpl();
        System.out.println(ud.validateUser(new UserDTO("", "xxx@xxx.xxx", "Xxx123", 0)).getToken());
    }
}
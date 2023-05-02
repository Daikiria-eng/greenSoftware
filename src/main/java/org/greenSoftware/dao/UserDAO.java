package org.greenSoftware.dao;

import org.greenSoftware.dto.ResponseDTO;
import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.dto.UserResponseDTO;

public interface UserDAO {
    public ResponseDTO insertUser(UserDTO user);
    
    public UserResponseDTO validateUser(UserDTO user);
}

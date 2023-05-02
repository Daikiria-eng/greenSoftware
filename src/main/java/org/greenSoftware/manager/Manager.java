package org.greenSoftware.manager;

import java.util.List;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dto.ResponseDTO;
import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.dto.UserResponseDTO;

public interface Manager{
    public List<ModuleDTO> getAllModules();
    
    public ModuleDTO getModule(ModuleDTO module);
    
    public ResponseDTO insertUser(UserDTO user);
    
    public UserResponseDTO validateUser(UserDTO user);
}
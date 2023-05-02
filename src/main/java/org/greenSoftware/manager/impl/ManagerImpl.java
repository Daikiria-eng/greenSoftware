package org.greenSoftware.manager.impl;

import java.util.List;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dto.ResponseDTO;
import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.dto.UserResponseDTO;
import org.greenSoftware.dao.ModulesDAO;
import org.greenSoftware.dao.UserDAO;
import org.greenSoftware.dao.impl.ModuleDAOimpl;
import org.greenSoftware.dao.impl.UserDAOimpl;
import org.greenSoftware.manager.Manager;

public class ManagerImpl implements Manager{
    ModulesDAO moduleDao=new ModuleDAOimpl();
    UserDAO userDao=new UserDAOimpl();

    public List<ModuleDTO> getAllModules() {
        return moduleDao.getAllModules();
    }
    
    public ModuleDTO getModule(ModuleDTO module){
        return moduleDao.getModule(module);
    }
    
    public ResponseDTO insertUser(UserDTO user){
        return userDao.insertUser(user);
    }

    public UserResponseDTO validateUser(UserDTO user) {
        return userDao.validateUser(user);
    }
}
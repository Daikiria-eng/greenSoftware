package org.greenSoftware.manager.impl;

import java.util.List;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dao.ModulesDAO;
import org.greenSoftware.dao.impl.ModuleDAOimpl;
import org.greenSoftware.manager.Manager;

public class ManagerImpl implements Manager{
    ModulesDAO moduleDao=new ModuleDAOimpl();

    public List<ModuleDTO> getAllModules() {
        return moduleDao.getAllModules();
    }
    
    public ModuleDTO getModule(ModuleDTO module){
        return moduleDao.getModule(module);
    }
}
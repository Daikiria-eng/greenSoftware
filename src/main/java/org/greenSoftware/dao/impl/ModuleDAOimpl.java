package org.greenSoftware.dao.impl;

import java.util.List;
import org.greenSoftware.dao.ModulesDAO;
import org.greenSoftware.repository.ModuleRepository;
import org.greenSoftware.dto.ModuleDTO;

public class ModuleDAOimpl implements ModulesDAO{
    ModuleRepository moduleRepo=new ModuleRepository();

    public List<ModuleDTO> getAllModules() {
        return moduleRepo.getAllModules();
    }

    public ModuleDTO getModule(ModuleDTO module) {
        return moduleRepo.getModule(module);
    }
    
}
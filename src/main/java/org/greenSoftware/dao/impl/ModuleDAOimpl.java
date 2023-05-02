package org.greenSoftware.dao.impl;

import java.util.List;
import org.greenSoftware.dao.ModulesDAO;
import org.greenSoftware.repository.ModuleRepository;
import org.greenSoftware.util.Util;
import org.greenSoftware.dto.ModuleDTO;

public class ModuleDAOimpl implements ModulesDAO{
    ModuleRepository moduleRepo=new ModuleRepository();

    public List<ModuleDTO> getAllModules() {
        return moduleRepo.getAllModules();
    }

    public ModuleDTO getModule(ModuleDTO module) {
        module.setModuleName(Util.escapeSpecialChars(module.getModuleName()));

        return moduleRepo.getModule(module);
    }
}
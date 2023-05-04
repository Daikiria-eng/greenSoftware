package org.greenSoftware.dao.impl;

import java.util.List;
import org.greenSoftware.dao.ModuleDAO;
import org.greenSoftware.repository.ModuleRepository;
import org.greenSoftware.util.Util;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dto.QuestionDTO;

public class ModuleDAOimpl implements ModuleDAO{
    ModuleRepository moduleRepo=new ModuleRepository();

    public List<ModuleDTO> getAllModules() {
        return moduleRepo.getAllModules();
    }

    public ModuleDTO getModule(ModuleDTO module) {
        module.setName(Util.escapeSpecialChars(module.getName()));

        return moduleRepo.getModule(module);
    }
    
    public QuestionDTO getQuestions(ModuleDTO module){
        module.setName(Util.escapeSpecialChars(module.getName()));

        return moduleRepo.getQuestions(module);
    }
}
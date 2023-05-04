package org.greenSoftware.dao;

import java.util.List;
import org.greenSoftware.dto.ModuleDTO;
import org.greenSoftware.dto.QuestionDTO;

public interface ModuleDAO {
    public List<ModuleDTO> getAllModules();

    public ModuleDTO getModule(ModuleDTO module);
    
    public QuestionDTO getQuestions(ModuleDTO module);
}
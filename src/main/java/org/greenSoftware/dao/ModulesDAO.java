package org.greenSoftware.dao;

import java.util.List;
import org.greenSoftware.dto.ModuleDTO;

public interface ModulesDAO {
    public List<ModuleDTO> getAllModules();

    public ModuleDTO getModule(ModuleDTO module);
}
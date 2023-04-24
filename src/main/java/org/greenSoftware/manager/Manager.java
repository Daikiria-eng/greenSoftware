package org.greenSoftware.manager;

import java.util.List;
import org.greenSoftware.dto.ModuleDTO;

public interface Manager{
    public List<ModuleDTO> getAllModules();
    
    public ModuleDTO getModule(ModuleDTO module);
}
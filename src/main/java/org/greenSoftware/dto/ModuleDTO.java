package org.greenSoftware.dto;

public class ModuleDTO {
    private String moduleName,
        description,
        examContent;

    public ModuleDTO(){}
    public ModuleDTO(String moduleName,String description, String examContent){
        this.moduleName=moduleName;
        this.description=description;
        this.examContent=examContent;
    }

    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getExamContent() {
        return examContent;
    }
    public void setExamContent(String examContent) {
        this.examContent = examContent;
    }
}
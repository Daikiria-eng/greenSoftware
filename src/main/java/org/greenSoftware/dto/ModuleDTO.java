package org.greenSoftware.dto;

public class ModuleDTO {
    private String name,
        description,
        examContent;

    public ModuleDTO(){}
    public ModuleDTO(String name,String description, String examContent){
        this.name=name;
        this.description=description;
        this.examContent=examContent;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
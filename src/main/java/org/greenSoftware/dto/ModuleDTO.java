package org.greenSoftware.dto;

import java.util.List;

public class ModuleDTO {
    private String name,
        description;
    private List<QuestionDTO> examContent;

    public ModuleDTO(){}
    public ModuleDTO(String name,String description,List<QuestionDTO> examContent){
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
    public List<QuestionDTO> getExamContent() {
        return examContent;
    }
    public void setExamContent(List<QuestionDTO> examContent) {
        this.examContent=examContent;
    }
}
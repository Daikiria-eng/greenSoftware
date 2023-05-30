package org.greenSoftware.dto;

public class ModuleContentDTO {
    private String contentText,
        exampleLang,
        example;
    private int contentOrder;

    public ModuleContentDTO(){}
    public ModuleContentDTO(String contentText,String exampleLang,String example, int contentOrder){
        this.contentText=contentText;
        this.exampleLang=exampleLang;
        this.example=example;
        this.contentOrder=contentOrder;
    }

    public String getContentText() {
        return contentText;
    }
    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
    public String getExampleLang() {
        return exampleLang;
    }
    public void setExampleLang(String exampleLang) {
        this.exampleLang = exampleLang;
    }
    public String getExample() {
        return example;
    }
    public void setExample(String example) {
        this.example = example;
    }
    public int getContentOrder() {
        return contentOrder;
    }
    public void setContentOrder(int contentOrder) {
        this.contentOrder = contentOrder;
    }
}
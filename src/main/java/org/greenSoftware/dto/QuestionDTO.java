package org.greenSoftware.dto;

public class QuestionDTO {
    private String questionString,answer;
    private Options options;

    public QuestionDTO(){}
    public QuestionDTO(String questionString,Options options,String answer){
        this.questionString=questionString;
        this.options=options;
        this.answer=answer;
    }
    public String getQuestionString() {
        return questionString;
    }
    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Options getOptions() {
        return options;
    }
    public void setOptions(Options options) {
        this.options = options;
    }
}
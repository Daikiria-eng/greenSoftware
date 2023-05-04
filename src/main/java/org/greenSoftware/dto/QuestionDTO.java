package org.greenSoftware.dto;

public class QuestionDTO {
    private String questionString,
        firstAns,
        secondAns,
        thirdAns,
        fourthAns;

    public QuestionDTO(){}
    public QuestionDTO(String questionString,String firstString,String secondString,String thirdString, String fourthString){
        this.questionString=questionString;
        this.firstAns=firstString;
        this.secondAns=secondString;
        this.thirdAns=thirdString;
        this.fourthAns=fourthString;
    }
    public String getQuestionString() {
        return questionString;
    }
    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }
    public String getFirstAns() {
        return firstAns;
    }
    public void setFirstAns(String firstAns) {
        this.firstAns = firstAns;
    }
    public String getSecondAns() {
        return secondAns;
    }
    public void setSecondAns(String secondAns) {
        this.secondAns = secondAns;
    }
    public String getThirdAns() {
        return thirdAns;
    }
    public void setThirdAns(String thirdAns) {
        this.thirdAns = thirdAns;
    }
    public String getFourthAns() {
        return fourthAns;
    }
    public void setFourthAns(String fourthAns) {
        this.fourthAns = fourthAns;
    }
}
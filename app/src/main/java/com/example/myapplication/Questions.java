package com.example.myapplication;

public class Questions {
    String question = null;
    String answer  = null;
    String option1  = null;
    String option2  = null;
    String option3  = null;


    public Questions(String q , String an , String o1 , String o2 , String o3 ){
        question = q;
        answer = an;
        option1 = o1;
        option2 = o2;
        option3 = o3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String quaston) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

}



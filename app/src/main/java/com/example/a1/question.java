package com.example.a1;

public class question {
    private String the_question;
    private String the_answer;

    public question(String the_question, String the_answer) {
        this.the_question = the_question;
        this.the_answer = the_answer;
    }

    public String getThe_question() {
        return the_question;
    }

    public void setThe_question(String the_question) {
        this.the_question = the_question;
    }

    public String getThe_answer() {
        return the_answer;
    }

    public void setThe_answer(String the_answer) {
        this.the_answer = the_answer;
    }
}

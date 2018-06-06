package com.example.dell.cabs_pos20.utilities;

import java.io.Serializable;

public class FAQ implements Serializable {

    private int question;
    private int answer;

    public FAQ() {
    }

    public FAQ(int question, int answer) {

        this.question = question;
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}

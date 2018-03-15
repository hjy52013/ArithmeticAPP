package com.hjy.test0308;

/**
 * Created by hjy on 2018/3/13.
 */

public class Question {

    String question;
    double answer;
    double userAnswer;

    public Question() {
    }

    public Question(String question, double answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public double getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(double userAnswer) {
        this.userAnswer = userAnswer;
    }
}

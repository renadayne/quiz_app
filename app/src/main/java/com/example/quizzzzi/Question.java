package com.example.quizzzzi;

import java.io.Serializable;

public class Question implements Serializable {
    private final String questionText, answer1, answer2;
    private final String questioAnswer;


    public Question(String questionText, String answer1, String answer2, String questioAnswer) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.questioAnswer = questioAnswer;
    }

    public String getAnswer1() { return answer1; }

    public String getAnswer2() { return answer2; }

    public String getQuestionText() { return questionText; }

    public String getQuestioAnswer() { return questioAnswer; }
}

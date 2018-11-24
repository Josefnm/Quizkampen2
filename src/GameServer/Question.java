package GameServer;

import java.io.Serializable;

public class Question implements Serializable {

    private String question;
    private String[] answers;
    private String correctAnswer;

    public Question(String question, String[] answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer(int i) {
        return answers[i];
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

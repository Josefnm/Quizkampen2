package QuizServer;

import java.io.Serializable;

/**
 *
 * @author Josef
 */
public class Question implements Serializable{

    private String category;
    private String question;
    private String[] answers;
    private String correctAnswer;

    public Question(String category, String question, String[] answers, int correctAnswer) {
        this.category = category;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = answers[correctAnswer];
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

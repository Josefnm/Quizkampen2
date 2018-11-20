package GameServer;

import java.io.Serializable;

public class Question implements Serializable {

    private String category;
    private String question;
    private String[] answers;
    
    private String correctAnswer;
    
    public Question(String category, String question, String[] answers, String correctAnswer){
        this.category=category;
        this.question=question;
        this.answers=answers;
        this.correctAnswer=correctAnswer;
    }

    public String getCategory() {
        return category;
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

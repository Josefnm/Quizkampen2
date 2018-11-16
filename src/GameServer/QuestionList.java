package GameServer;

import java.util.ArrayList;

public class QuestionList {

    ArrayList<Question> questions = new ArrayList<>();

    public QuestionList() {
        questions.add(new Question("Matte", "Vad är 1+1",
                new String[]{"2", "5", "10", "20"}, "2"));
        questions.add(new Question("Matte", "Vad är 2+2",
                new String[]{"2", "5", "4", "20"}, "4"));
        questions.add(new Question("Sport", "Vilken form har en boll",
                new String[]{"fyrkantig", "trekantig", "pentagon", "rund"}, "rund"));
        questions.add(new Question("Sport", "Vilken sport Sysslar Zlatan med",
                new String[]{"Ishockey", "Fotboll", "Balett", "Boxning"}, "Fotboll"));
        questions.add(new Question("history", "what is the year?",
                new String[]{"1999", "1990", "1992", "1998"}, "1998"));
        questions.add(new Question("history", "what is the person?",
                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
        questions.add(new Question("natural", "what is the weather?",
                new String[]{"winter", "summer", "autom", "spring"}, "winter"));
        questions.add(new Question("natural", "what is the water?",
                new String[]{"hot", "cold", "iced", "steam"}, "iced"));
    }
}

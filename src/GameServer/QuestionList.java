package GameServer;

import java.util.ArrayList;
import java.util.List;

public class QuestionList {

    ArrayList<Question> questions = new ArrayList<>();
    List<Question> fourQuestions = new ArrayList<>();
    ArrayList<List<Question>> questionLista = new ArrayList<>();
    ArrayList<Question> matte = new ArrayList<>();
    ArrayList<Question> sport = new ArrayList<>();
    ArrayList<Question> historia = new ArrayList<>();
    ArrayList<Question> natur = new ArrayList<>();

    public QuestionList() {
        matte.add(new Question( "Vad är 1+1",
                new String[]{"2", "5", "10", "20"}, "2"));
        matte.add(new Question( "Vad är 2+2",
                new String[]{"2", "5", "4", "20"}, "4"));
        matte.add(new Question( "Vilken form har en boll",
                new String[]{"fyrkantig", "trekantig", "pentagon", "rund"}, "rund"));
        sport.add(new Question( "Vilken sport Sysslar Zlatan med",
                new String[]{"Ishockey", "Fotboll", "Balett", "Boxning"}, "Fotboll"));
        historia.add(new Question( "what is the year?",
                new String[]{"1999", "1990", "1992", "1998"}, "1998"));
        historia.add(new Question( "what is the person?",
                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
        natur.add(new Question( "what is the weather?",
                new String[]{"winter", "summer", "autom", "spring"}, "winter"));
        natur.add(new Question( "what is the water?",
                new String[]{"hot", "cold", "iced", "steam"}, "iced"));

        questionLista.add(matte);
        questionLista.add(sport);
        questionLista.add(historia);
        questionLista.add(natur);
        
    }

    public Question getByNum(int num) {
        return questions.get(num);
    }

    public List<Question> getFour(int i) {//för att testa
        return questionLista.get(i);
    }
    
}

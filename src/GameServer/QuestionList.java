package GameServer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class QuestionList {

    private List<ArrayList<Question>> questionList = new ArrayList<>();
    private ArrayList<Question> matte = new ArrayList<>();
    private ArrayList<Question> sport = new ArrayList<>();
    private ArrayList<Question> historia = new ArrayList<>();
    private ArrayList<Question> natur = new ArrayList<>();
    private int roundsPerGame;
    private int questionsPerRond;

    public QuestionList() {
        setQuestions();
        questionList.add(matte);
        questionList.add(sport);
        questionList.add(historia);
        questionList.add(natur);

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/GameServer/GameLength.properties"));
        } catch (Exception e) {
            System.out.println("Filen inte hittas");
        }
        System.out.println(properties.getProperty("roundsPerGame"));
        System.out.println(properties.getProperty("questionsPerRound"));
        roundsPerGame = Integer.parseInt(properties.getProperty("roundsPerGame"));
        questionsPerRond = Integer.parseInt(properties.getProperty("questionsPerRound"));
    }

    private ArrayList<Question> getRandomQuestions(ArrayList<Question> qs) {
        Collections.shuffle(qs);
        ArrayList<Question> questions = new ArrayList<>(qs.subList(0, questionsPerRond));
        return questions;
    }

    public ArrayList<ArrayList<Question>> GetRandom() {
        ArrayList<ArrayList<Question>> randomCategories = new ArrayList<>();
        ArrayList<Integer> ronds = new ArrayList<>();
        for (int k = 0; k < roundsPerGame; k++) {
            ronds.add(k);
        }
        Collections.shuffle(ronds);
        for (int p = 0; p < roundsPerGame; p++) {
            randomCategories.add(getRandomQuestions(questionList.get(ronds.get(p))));
        }
        return randomCategories;
    }

    private void setQuestions() { //Vi kanske ska läsa dessa från ett textdokument istället?
        matte.add(new Question("Vad är 1+1",
                new String[]{"2", "5", "10", "20"}, "2"));
        matte.add(new Question("Vad är 2+2",
                new String[]{"2", "5", "4", "20"}, "4"));
        matte.add(new Question("Vad är 1+1",
                new String[]{"2", "5", "10", "20"}, "2"));
        matte.add(new Question("Vad är 1+1",
                new String[]{"2", "5", "10", "20"}, "2"));
        sport.add(new Question("Vilken sport Sysslar Zlatan med",
                new String[]{"Ishockey", "Fotboll", "Balett", "Boxning"}, "Fotboll"));
        sport.add(new Question("Vilken sport Sysslar Zlatan med",
                new String[]{"Ishockey", "Fotboll", "Balett", "Boxning"}, "Fotboll"));
        sport.add(new Question("Vilken form har en boll",
                new String[]{"fyrkantig", "trekantig", "pentagon", "rund"}, "rund"));
        sport.add(new Question("Vilken sport Sysslar Zlatan med",
                new String[]{"Ishockey", "Fotboll", "Balett", "Boxning"}, "Fotboll"));
        historia.add(new Question("what is the year?",
                new String[]{"1999", "1990", "1992", "1998"}, "1998"));
        historia.add(new Question("what is the person?",
                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
        historia.add(new Question("what is the year?",
                new String[]{"1999", "1990", "1992", "1998"}, "1998"));
        historia.add(new Question("what is the person?",
                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
        historia.add(new Question("what is the person?",
                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
        natur.add(new Question("what is the weather?",
                new String[]{"winter", "summer", "autom", "spring"}, "winter"));
        natur.add(new Question("what is the water?",
                new String[]{"hot", "cold", "iced", "steam"}, "iced"));
        natur.add(new Question("what is the water?",
                new String[]{"hot", "cold", "iced", "steam"}, "iced"));
    }
}

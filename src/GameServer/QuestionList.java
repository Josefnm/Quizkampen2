package GameServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionList {

    ArrayList<ArrayList<Question>> questionList = new ArrayList<>();
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

        questionList.add(matte);
        questionList.add(sport);
        questionList.add(historia);
        questionList.add(natur);
        
    }
    
    // Metoden tar in en lista med frågor som sedan kommer att ge ut 2 frågor från listan
    public ArrayList<Question> getTwoQuestions(ArrayList<Question> a){
        Random rand = new Random();
        ArrayList <Question> twoQuestions = new ArrayList<>();
        int temp= -1;
        int i =0;
        while(i<2){
            int j = rand.nextInt(a.size());
            if(temp!=j){
                twoQuestions.add(a.get(j));
                i++;
            temp = j;
            }
        }   
        return twoQuestions ;
    }

    public ArrayList<ArrayList<Question>> getTwoCategories() {//för att testa
        System.out.println("getTwocategories in questionList");
        Random rand = new Random();        
        ArrayList<ArrayList<Question>> twoCategories = new ArrayList<>();
        int temp= -1;
        int i =0;
        while(i<2){
            int j = rand.nextInt(questionList.size());
            if(temp!=j){
                twoCategories.add(getTwoQuestions(questionList.get(j)));
                i++;
            temp = j;
            }
        }
        return twoCategories;
    }

//    public ArrayList<Question> getFour() {//för att testa
//        return fourQuestions;
//    }
    
}

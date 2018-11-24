package GameServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionList {

    ArrayList<ArrayList<Question>> questionList = new ArrayList<>();
    ArrayList<Question> matte = new ArrayList<>();
    ArrayList<Question> sport = new ArrayList<>();
    ArrayList<Question> historia = new ArrayList<>();
    ArrayList<Question> natur = new ArrayList<>();

    public QuestionList() {
        matte.add(new Question("Vad är 1+",
                new String[]{"2", "5", "10", "20"}, "2"));
        matte.add(new Question("Vad är 2+",
                new String[]{"2", "19", "4", "20"}, "4"));
        matte.add(new Question("Vad är 1+1",
                new String[]{"2", "78", "10", "20"}, "2"));
        matte.add(new Question("Vad är 2+2",
                new String[]{"2", "66", "4", "20"}, "4"));
        sport.add(new Question("Vilken form har en ",
                new String[]{"fyrkantig", "99", "pentagon", "rund"}, "rund"));
        sport.add(new Question("Vilken sport Sysslar Zlatan med",
                new String[]{"Ishockey", "44", "Balett", "Boxning"}, "Fotboll"));
        sport.add(new Question("Vilken form har en boll",
                new String[]{"fyrkantig", "33", "pentagon", "rund"}, "rund"));
        sport.add(new Question("Vilken sport Sysslar Zlatan ",
                new String[]{"Ishockey", "11", "Balett", "Boxning"}, "Fotboll"));
        historia.add(new Question("what is the year?",
                new String[]{"8888", "1990", "1992", "1998"}, "1998"));
        historia.add(new Question("what is the person?",
                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
        historia.add(new Question("what is the ?",
                new String[]{"1111", "1990", "1992", "1998"}, "1998"));
        historia.add(new Question(" is the person?",
                new String[]{"5555", "Fox", "Ranbon", "Franklin"}, "Fox"));
        natur.add(new Question("what is the weather?",
                new String[]{"winter", "summer", "autom", "spring"}, "winter"));
        natur.add(new Question("what is the water?",
                new String[]{"hot", "cold", "iced", "steam"}, "iced"));
        natur.add(new Question("what is the weat?",
                new String[]{"6666", "summer", "autom", "spring"}, "winter"));
        natur.add(new Question("what the water?",
                new String[]{"3333", "cold", "iced", "steam"}, "iced"));

        questionList.add(matte);
        questionList.add(sport);
        questionList.add(historia);
        questionList.add(natur);
    }

    // Metoden tar in en lista med frågor som sedan kommer att ge ut 2 frågor från listan
    public ArrayList<Question> getTwoQuestions(ArrayList<Question> a, int questionsForeach) {
        Random rand = new Random();
        ArrayList<Question> twoQuestions = new ArrayList<>();
//        int temp = -1;
//        int i = 0;
        Collections.shuffle(a);
//        System.out.println(a.get(0).getQuestion());
        for(int r=0; r<questionsForeach; r++){
            twoQuestions.add(a.get(r));
        }
        
//         twoQuestions=(ArrayList<Question>)a.subList(0, questionsForeach);
        
        return twoQuestions;
    }

    public ArrayList<ArrayList<Question>> getTwoCategories(int rond, int questionsForeach) {
        Random rand = new Random();
        ArrayList<ArrayList<Question>> twoCategories = new ArrayList<>();
        int temp = -1;
        int i = 0;
        ArrayList<Integer> ronds = new ArrayList<>();
//        ArrayList<Integer> questions = new ArrayList<>();
        for(int k=0; k<questionList.size(); k++){
            ronds.add(k);
        }
        Collections.shuffle(ronds);
        for(int p=0; p<ronds.size(); p++){
            System.out.println(ronds.get(p));
        }
//        for(int k=0; k<questionsForeach; k++){
//            questions.add(k);
//        }
//        Collections.shuffle(questions);
        
//        Collections.shuffle(Collections.shuffle(matte));
        for(int p=0; p<rond; p++){
            twoCategories.add(getTwoQuestions(questionList.get(ronds.get(p)), questionsForeach));
        }
//        while (i < rond) {
//            int j = rand.nextInt(questionList.size());
//            if (temp != j) {
//                twoCategories.add(getTwoQuestions(questionList.get(j), questionsForeach));
//                i++;
//                temp = j;
//            }
//        }
        return twoCategories;
    }
}

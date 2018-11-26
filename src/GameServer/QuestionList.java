package GameServer;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionList {
    private String mattefile = "src\\gameserver\\questions\\matte.txt";
    private Path mattepath = Paths.get(mattefile);
    private String sportfile = "src\\gameserver\\questions\\sport.txt";
    private Path sportpath = Paths.get(sportfile);
    private String naturfile = "src\\gameserver\\questions\\natur.txt";
    private Path naturpath = Paths.get(naturfile);
    private String historiafile = "src\\gameserver\\questions\\historia.txt";
    private Path historiapath = Paths.get(historiafile);
    protected ArrayList<ArrayList<Question>> questionList = new ArrayList<>();
    protected ArrayList<Question> matte = new ArrayList<>();
    protected ArrayList<Question> sport = new ArrayList<>();
    protected ArrayList<Question> historia = new ArrayList<>();
    protected ArrayList<Question> natur = new ArrayList<>();  
    private String temp;    

//    ArrayList<ArrayList<Question>> questionList = new ArrayList<>();
//    ArrayList<Question> matte = new ArrayList<>();
//    ArrayList<Question> sport = new ArrayList<>();
//    ArrayList<Question> historia = new ArrayList<>();
//    ArrayList<Question> natur = new ArrayList<>();

    public QuestionList() {
        questionList.add(matte);
        questionList.add(sport);
        questionList.add(natur);
        questionList.add(historia);  
        int i = 0;
         try(BufferedReader br = Files.newBufferedReader(mattepath)){
            while((temp=br.readLine()) != null && !temp.trim().equals("")){
                String[] str = temp.split(",");
                matte.add(new Question(str[0], new String[]{str[1],str[2],str[3],str[4]}, str[5]));
            }
        }
        catch(Exception e){
            System.out.println("file not found");
            e.printStackTrace();
        }
        try(BufferedReader br = Files.newBufferedReader(sportpath)){
            while((temp=br.readLine()) != null && !temp.trim().equals("")){
                String[] str = temp.split(",");
                sport.add(new Question(str[0], new String[]{str[1],str[2],str[3],str[4]}, str[5]));
            }
        }
        catch(Exception e){
            System.out.println("file not found");
            e.printStackTrace();
        }
        try(BufferedReader br = Files.newBufferedReader(naturpath)){
            while((temp=br.readLine()) != null && !temp.trim().equals("")){
                String[] str = temp.split(",");
                natur.add(new Question(str[0], new String[]{str[1],str[2],str[3],str[4]}, str[5]));
            }
        }
        catch(Exception e){
            System.out.println("file not found");
            e.printStackTrace();
        }
        try(BufferedReader br = Files.newBufferedReader(historiapath)){
            while((temp=br.readLine()) != null && !temp.trim().equals("")){
                String[] str = temp.split(",");
                historia.add(new Question(str[0], new String[]{str[1],str[2],str[3],str[4]}, str[5]));
            }
        }
        catch(Exception e){
            System.out.println("file not found");
            e.printStackTrace();
        }
        
//        matte.add(new Question("Vad är 1+",
//                new String[]{"2", "5", "10", "20"}, "2"));
//        matte.add(new Question("Vad är 2+",
//                new String[]{"2", "19", "4", "20"}, "4"));
//        matte.add(new Question("Vad är 1+1",
//                new String[]{"2", "78", "10", "20"}, "2"));
//        matte.add(new Question("Vad är 2+2",
//                new String[]{"2", "66", "4", "20"}, "4"));
//        sport.add(new Question("Vilken form har en ",
//                new String[]{"fyrkantig", "99", "pentagon", "rund"}, "rund"));
//        sport.add(new Question("Vilken sport Sysslar Zlatan med",
//                new String[]{"Ishockey", "44", "Balett", "Boxning"}, "Fotboll"));
//        sport.add(new Question("Vilken form har en boll",
//                new String[]{"fyrkantig", "33", "pentagon", "rund"}, "rund"));
//        sport.add(new Question("Vilken sport Sysslar Zlatan ",
//                new String[]{"Ishockey", "11", "Balett", "Boxning"}, "Fotboll"));
//        historia.add(new Question("what is the year?",
//                new String[]{"8888", "1990", "1992", "1998"}, "1998"));
//        historia.add(new Question("what is the person?",
//                new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox"));
//        historia.add(new Question("what is the ?",
//                new String[]{"1111", "1990", "1992", "1998"}, "1998"));
//        historia.add(new Question(" is the person?",
//                new String[]{"5555", "Fox", "Ranbon", "Franklin"}, "Fox"));
//        natur.add(new Question("what is the weather?",
//                new String[]{"winter", "summer", "autom", "spring"}, "winter"));
//        natur.add(new Question("what is the water?",
//                new String[]{"hot", "cold", "iced", "steam"}, "iced"));
//        natur.add(new Question("what is the weat?",
//                new String[]{"6666", "summer", "autom", "spring"}, "winter"));
//        natur.add(new Question("what the water?",
//                new String[]{"3333", "cold", "iced", "steam"}, "iced"));


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

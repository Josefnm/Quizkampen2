package GameServer;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionList {
    private String file = "src\\gameserver\\questions\\total.txt";
    private Path path = Paths.get(file);
    ArrayList<ArrayList<Question>> questionList = new ArrayList<>();
    ArrayList<Question> matte = new ArrayList<>();
    ArrayList<Question> sport = new ArrayList<>();
    ArrayList<Question> historia = new ArrayList<>();
    ArrayList<Question> natur = new ArrayList<>();

    public QuestionList() {
        String temp;    
         try(BufferedReader br = Files.newBufferedReader(path)){
            while((temp=br.readLine()) != null && !temp.trim().equals("")){
                String[] str = temp.split(",");
                Question question = new Question(str[1], new String[]{str[2],str[3],str[4],str[5]}, str[6]);
                if(str[0].equals("matte")) matte.add(question);
                if(str[0].equals("sport")) sport.add(question);
                if(str[0].equals("natur")) natur.add(question);
                if(str[0].equals("historia")) historia.add(question);
            }
        }
        catch(Exception e){
            System.out.println("file not found");
            e.printStackTrace();
        }
        questionList.add(matte);
        questionList.add(sport);
        questionList.add(natur);
        questionList.add(historia);  
    }

    // Metoden tar in en lista med frågor som sedan kommer att ge ut 2 frågor från listan
    public ArrayList<Question> getTwoQuestions(ArrayList<Question> a, int questionsForeach) {
        Random rand = new Random();
        ArrayList<Question> twoQuestions = new ArrayList<>();
        Collections.shuffle(a);
        for(int r=0; r<questionsForeach; r++){
            twoQuestions.add(a.get(r));
        }
        return twoQuestions;
    }

    public ArrayList<ArrayList<Question>> getTwoCategories(int rond, int questionsForeach) {
        Random rand = new Random();
        ArrayList<ArrayList<Question>> twoCategories = new ArrayList<>();
        int temp = -1;
        int i = 0;
        ArrayList<Integer> ronds = new ArrayList<>();
        for(int k=0; k<questionList.size(); k++){
            ronds.add(k);
        }
        Collections.shuffle(ronds);
        for(int p=0; p<ronds.size(); p++){
            System.out.println(ronds.get(p));
        }
        for(int p=0; p<rond; p++){
            twoCategories.add(getTwoQuestions(questionList.get(ronds.get(p)), questionsForeach));
        }
        return twoCategories;
    }
}

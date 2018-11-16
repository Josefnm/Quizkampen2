/*
 * Java
 */
package GameServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julia
 */
public class Dao implements Serializable{
    Question histryQ1 = new Question("history", "what is the year?", 
            new String[]{"1999", "1990", "1992", "1998"}, "1998", "");
    
    Question histryQ2 = new Question("history", "what is the person?", 
            new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox", "");
    
    Question naturalQ1 = new Question("natural", "what is the weather?", 
            new String[]{"winter", "summer", "autom", "spring"}, "winter", "");
    
    Question naturalQ2 = new Question("natural", "what is the water?", 
            new String[]{"hot", "cold", "iced", "steam"}, "iced", "");
    List<Question> qAndAn = new ArrayList<>();
    Question[] first = new Question[]{histryQ1, histryQ2};
    public Dao(){
        qAndAn.add(histryQ1);
        qAndAn.add(histryQ2);
        qAndAn.add(naturalQ1);
        qAndAn.add(naturalQ2);
    }
    public Question getByNum(int num){
        return qAndAn.get(num);
    }
    public Question[] getFirst(){
        return first;
    }
    public List<Question> getALL(){
        return qAndAn;
    }
    
    
}


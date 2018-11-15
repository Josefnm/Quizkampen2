/*
 * Java
 */
package GameServer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julia
 */
public class Dao {
    Questions histryQ1 = new Questions("history", "what is the year?", 
            new String[]{"1999", "1990", "1992", "1998"}, "1998", "");
    
    Questions histryQ2 = new Questions("history", "what is the person?", 
            new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox", "");
    
    Questions naturalQ1 = new Questions("natural", "what is the weather?", 
            new String[]{"winter", "summer", "autom", "spring"}, "winter", "");
    
    Questions naturalQ2 = new Questions("natural", "what is the water?", 
            new String[]{"hot", "cold", "iced", "steam"}, "iced", "");
    List<Questions> qAndAn = new ArrayList<>();
    public Dao(){
        qAndAn.add(histryQ1);
        qAndAn.add(histryQ2);
        qAndAn.add(naturalQ1);
        qAndAn.add(naturalQ2);
    }
    public Questions getByNum(int num){
        for(int i=0; i<4; i++ ){
            if(i+1 == num){
                return qAndAn.get(i);
            }
        }
        return null;
    }
    
    
}


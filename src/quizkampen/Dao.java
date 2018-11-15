/*
 * Java
 */
package quizkampen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Julia
 */
public class Dao {
    QandA histryQ1 = new QandA("history", "what is the year?", 
            new String[]{"1999", "1990", "1992", "1998"}, "1998", "");
    
    QandA histryQ2 = new QandA("history", "what is the person?", 
            new String[]{"Linkon", "Fox", "Ranbon", "Franklin"}, "Fox", "");
    
    QandA naturalQ1 = new QandA("natural", "what is the weather?", 
            new String[]{"winter", "summer", "autom", "spring"}, "winter", "");
    
    QandA naturalQ2 = new QandA("natural", "what is the water?", 
            new String[]{"hot", "cold", "iced", "steam"}, "iced", "");
    List<QandA> qAndAn = new ArrayList<>();
    public Dao(){
        qAndAn.add(histryQ1);
        qAndAn.add(histryQ2);
        qAndAn.add(naturalQ1);
        qAndAn.add(naturalQ2);
    }
    public QandA getByNum(int num){
        for(int i=0; i<4; i++ ){
            if(i+1 == num){
                return qAndAn.get(i);
            }
        }
        return null;
    }
    public String findResult(String answer){
        String result="";
        for(int i=0; i<4; i++){
            if(answer.equals(histryQ1.getValjningar(i))) return result=histryQ1.result;
        }
        for(int i=0; i<4; i++){
            if(answer.equals(histryQ2.getValjningar(i))) return result=histryQ2.result;
        }
        for(int i=0; i<4; i++){
            if(answer.equals(naturalQ1.getValjningar(i))) return result=naturalQ1.result;
        }
        for(int i=0; i<4; i++){
            if(answer.equals(naturalQ2.getValjningar(i))) return result=naturalQ2.result;
        }
        return result;
    }
    
    
}

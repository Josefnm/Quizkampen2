/*
 * Java
 */
package objectserver;

import java.io.Serializable;

/**
 *
 * @author Julia
 */
public class QandA implements Serializable{
    private String amne;
    private String fraga;
    private String[] valjningar;
    String result;
    
    public QandA(String amne, String fraga, String[] valjningar, String result){
        this.amne=amne;
        this.fraga=fraga;
        this.valjningar=valjningar;
        this.result=result;
    }
    public String getResult(){
        return result;
    }
    public String getAmne() {
        return amne;
    }
    public String getFraga() {
        return fraga;
    }
    public String[] getValjningar() {
        return valjningar;
    }
}


import java.io.Serializable;

/*
 * Java
 */

/**
 *
 * @author Julia
 */
public class QandA implements Serializable{
    private String amne;
    private String fraga;
    private String[] valjningar;
    String result;
    private String svar;
    
    public QandA(String amne, String fraga, String[] valjningar, String result, String svar){
        this.amne=amne;
        this.fraga=fraga;
        this.valjningar=valjningar;
        this.result=result;
        this.svar=svar;
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

    public String getValjningar(int i) {
        return valjningar[i];
    }

    public String getSvar() {
        return svar;
    }
    
    
}

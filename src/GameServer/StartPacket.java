package GameServer;

import java.io.Serializable;
import java.util.ArrayList;

public class StartPacket implements Serializable{

    private ArrayList<Question> questions;
    private boolean firstPlayer;    //默认不是false吗？？

    public StartPacket(ArrayList<Question> questions, boolean firstPlayer) {
        this.questions = questions;
        this.firstPlayer = firstPlayer;
    }
    
    public StartPacket(ArrayList<Question> questions){
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }
    
}

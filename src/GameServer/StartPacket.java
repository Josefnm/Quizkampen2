package GameServer;

import java.io.Serializable;
import java.util.ArrayList;

public class StartPacket implements Serializable{

    private ArrayList<Question> questions;
    private boolean firstPlayer;
    //private boolean[] opponentScore;

    public StartPacket(ArrayList<Question> questions, boolean firstPlayer/*, boolean[] opponentScore*/) {
        this.questions = questions;
        this.firstPlayer = firstPlayer;
        //this.opponentScore=opponentScore;
    }
    public StartPacket(ArrayList<Question> questions) {
        this.questions = questions;
       
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    /*public boolean[] getOpponentScore() {
        return opponentScore;
    }*/
    
}

package GameServer;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoPacket implements Serializable {

    private ArrayList<Question> questions;
    private boolean IsfirstPlayer;
    private boolean[] opponentScore;
    
    
//olika konstruktorer beroende på vad man vill skicka
//    public InfoPacket(ArrayList<Question> questions, boolean isFirstPlayer) {
//        this.questions = questions;
//        this.IsfirstPlayer = isFirstPlayer;
//    }

    public InfoPacket(ArrayList<Question> questions, boolean isFirstPlayer, boolean[] opponentScore) {
        this.questions = questions;
        this.IsfirstPlayer = isFirstPlayer;
        this.opponentScore = opponentScore;
    }

//    public InfoPacket(ArrayList<Question> questions) {
//        this.questions = questions;
//    }

    
    //Getters
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean getIsfirstPlayer() {
        return IsfirstPlayer;
    }

    public boolean[] getOpponentScore() {
        return opponentScore;
    }
}

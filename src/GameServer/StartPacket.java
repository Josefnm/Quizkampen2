package GameServer;

import java.io.Serializable;
import java.util.ArrayList;

public class StartPacket implements Serializable{

    private ArrayList<Question> questions;
    private boolean firstPlayer;

    public StartPacket(ArrayList<Question> questions, boolean firstPlayer) {
        this.questions = questions;
        this.firstPlayer = firstPlayer;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }
    
}

package GameServer;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoPacket implements Serializable {

    private ArrayList<Question> questions;

    private boolean[] opponentScore;

    public InfoPacket(ArrayList<Question> questions, boolean[] opponentScore) {
        this.questions = questions;
        this.opponentScore=opponentScore;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean[] getOpponentScore() {
        return opponentScore;
    }
}

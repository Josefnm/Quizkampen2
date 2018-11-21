package GameServer;

import java.util.ArrayList;

/**
 *
 * @author Josef
 */
public class StartPacket {

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

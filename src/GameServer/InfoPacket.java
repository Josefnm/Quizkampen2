package GameServer;

import java.io.Serializable;
import java.util.ArrayList;

public class InfoPacket implements Serializable {

    private IdEnum id;
    private String name;
    private String chatMsg;
    private int roundsPerGame;
    private ArrayList<Question> questions;
    private boolean[] score;

    public InfoPacket(IdEnum id) {
        this.id = id;
    }

    public InfoPacket(String name) {
        id = IdEnum.LOGIN;
        this.name = name;
    }
    

    public InfoPacket(boolean[] opponentScore) {
        id = IdEnum.NEXT_ROUND;
        this.score = opponentScore;
        System.out.println(score[0]+""+score[1]+""+score[2]);
    }

    public InfoPacket(int roundsPerGame) {
        id = IdEnum.GAME_LENGTH;
        this.roundsPerGame = roundsPerGame;
    }

    public InfoPacket(String name, String chatMsg) {
        id=IdEnum.CHAT;
        this.name = name;
        this.chatMsg = chatMsg;
    }
    

    public InfoPacket(ArrayList<Question> questions, String name) {
        id = IdEnum.START;
        this.name = name;
        this.questions = questions;
    }

    public InfoPacket(ArrayList<Question> questions, boolean[] opponentScore) {
        id = IdEnum.NEXT_ROUND;
        this.questions = questions;
        this.score = opponentScore;
    }

    public IdEnum getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public boolean[] getScore() {
        return score;
    }

    public String getChatMsg() {
        return chatMsg;
    }
    
}

package GameServer;

import java.util.ArrayList;

public class GameRoom {

    private Player player1;
    private Player player2;

    private ArrayList<boolean[]> player1Score;
    private ArrayList<boolean[]> player2Score;

    private int currentRound = 0;

    private ArrayList<ArrayList<Question>> questions; //alla frågor för en hel spelomgång

    public GameRoom(Player player1, Player player2, ArrayList<ArrayList<Question>> questions) {
        this.player1Score = new ArrayList<>();
        this.player2Score = new ArrayList<>();
        this.player1 = player1;
        this.player2 = player2;
        this.questions = questions;
    }

    public boolean bothAnswered() { //true när båda spelare svarat på frågorna för ronden
        return player1Score.size() == player2Score.size();
    }

    public void addScore(Player player, boolean[] score) {
        if (player == player1) {
            player1Score.add(score);
        } else {
            player2Score.add(score);
        }
    }

    public void increaseCurrentRound() {
        currentRound++;
        System.out.println(currentRound);
    }

    public ArrayList<ArrayList<Question>> getQuestions() {
        return questions;
    }

    public ArrayList<Question> getCurrentQuestions() {
        //Fullösning för att inte få nullpointer när sista rundans poäng skickas med newRound från ServerProtocol
        try {
            return questions.get(currentRound);
        } catch (Exception e) {
            return null;
        }
    }
//currentRound-1 eftersom föregående rundas poäng ska skickas samtidigt som nästa rundas frågor
    public boolean[] getPlayer1Score() {
        return player1Score.get(currentRound - 1);  
    }

    public boolean[] getPlayer2Score() {
        return player2Score.get(currentRound - 1);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}

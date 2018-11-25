package GameServer;

import java.util.ArrayList;

public class GameRoom {

    private Player player1; //spelar först
    private Player player2;

    private ArrayList<boolean[]> player1Score = new ArrayList<>();
    private ArrayList<boolean[]> player2Score = new ArrayList<>();

    private int currentRound = 0;

    private ArrayList<ArrayList<Question>> allQuestions;

    public GameRoom(Player player1, Player player2, ArrayList<ArrayList<Question>> allQuestions) {
        this.player1 = player1;
        this.player2 = player2;

        this.allQuestions = allQuestions; //de utvalda frågorna för spelomgången
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

    public boolean isFirstPlayer(Player player) {
        return (player1 == player);
    }

    public void increaseCurrentRound() {
        currentRound++;
    }

    public ArrayList<ArrayList<Question>> getAllQuestions() {
        return allQuestions;
    }

    public ArrayList<Question> getCurrentQuestions() {
        try {
            return allQuestions.get(currentRound);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean[] getPlayer1Score() {
        try {
            return player1Score.get(currentRound);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean[] getPlayer2Score() {
        try {
            return player2Score.get(currentRound);
        } catch (Exception ex) {
            return null;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

}

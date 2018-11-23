package GameServer;

import java.util.ArrayList;

public class GameRoom {

    Player player1;
    Player player2;
    Player currentPlayer;
    int[] player1Score;
    int[] player2Score;
    int currentRound = 0;
    private ArrayList<ArrayList<Question>> allQuestions;

    public GameRoom(Player player1, Player player2, ArrayList<ArrayList<Question>> allQuestions) {
        this.player1 = player1;
        this.player1 = player2;
        currentPlayer = player1;
        this.allQuestions = allQuestions;
    }
    
    public boolean isCurrentPlayer(Player player){
        return (currentPlayer==player);
    }
    public ArrayList<ArrayList<Question>> getAllQuestions(){
        return allQuestions;
    }
    public ArrayList<Question> getCurrentQuestions(){
        return allQuestions.get(currentRound);      //目前永远都是0
    }
}

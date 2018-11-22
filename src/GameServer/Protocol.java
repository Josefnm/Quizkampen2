package GameServer;

import java.util.ArrayList;

public class Protocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, se om svar på 
    frågor är rätt osv*/
    private ArrayList<Player> playerList;
    private QuestionList questionList;

    public Protocol() {
        questionList = new QuestionList();
        playerList = new ArrayList<>();
    }

    //String för att testa, sedan ska den kunna ta emot olika saker som skickas från klienten
    public void getResponse(Player player, String s) {
        switch (s) {
            case "start":
                getOpponent(player);
                break;
            case "cancel":
                player.setIsAvailable(false);
                break;

        }
    }

    public void getResponse(Player player, int s) {
        System.out.println("wrong response");
        if (player.getGameRoom().currentPlayer != player) {

        }
    }

    public void getOpponent(Player player1) {
        for (Player player2 : playerList) {
            if (player2.getIsIsAvailable() && player1 != player2) {
                GameRoom gr = new GameRoom(player2, player1);
                player2.setGameRoom(gr);
                player1.setGameRoom(gr);
                player1.startGame();
                player2.startGame();
                return;
            }
        }
        player1.setIsAvailable(true);

    }

    public void changeCurrentPlayer() {

    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

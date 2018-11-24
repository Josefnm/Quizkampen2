package GameServer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Protocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, skicka poäng osv*/
    private ArrayList<Player> playerList;
    private QuestionList questionList;

    public Protocol() {
        questionList = new QuestionList();
        playerList = new ArrayList<>();

    }

    //Hanterar data som tagits emot från klienten
    public void getResponse(Player player, Object input) {
        if (input instanceof String) {
            switch (input.toString()) {
                case "start":
                    getOpponent(player);
                    break;
                case "cancel": //gör spelaren otillgänglig när spelaren trycker skickar cancel
                    player.setIsAvailable(false);
                    break;

            }
        } else if (input instanceof boolean[]) { //körs bär spelaren skickat svaren efter varje rond
            System.out.println("boolean recieved");
            setScore(player, (boolean[]) input);
        } else {
            System.out.println("response error");
        }
    }

    public void setScore(Player player, boolean[] score) {
        System.out.println("score recieved");
        GameRoom gr = player.getGameRoom();
        gr.addScore(player, score);
        if (gr.bothAnswered()) {
            System.out.println(gr.getPlayer1Score()[0]);
            sendNewRound(gr);
            gr.increaseCurrentRound();
        }
    }

    //hittar motspelare när clienten skickat "start"
    public void getOpponent(Player player2) {
        for (Player player1 : playerList) {
            if (player1.getIsIsAvailable() && player2 != player1) {
                //skapar upp ett gameroom som innehåller info för den specifika spelomgången
                GameRoom gr = new GameRoom(player1, player2, questionList.GetRandom());
                player1.setGameRoom(gr);
                player2.setGameRoom(gr);
                sendNewRound(gr);
                return;
            }
        }
        player2.setIsAvailable(true);
    }

    public void sendNewRound(GameRoom gr) {
        
        gr.getPlayer1().Send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer2Score()));
        gr.getPlayer2().Send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer1Score()));
        System.out.println("round packets sent");
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

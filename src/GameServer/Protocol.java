package GameServer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Protocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, se om svar på 
    frågor är rätt osv*/
    private ArrayList<Player> playerList;
    private QuestionList questionList;
    Properties p;
    int ronds;
    int questionPerRond;

    public Protocol() {
        questionList = new QuestionList();
        playerList = new ArrayList<>();
        p = new Properties();
        try {
            p.load(new FileInputStream("src/GameServer/ronds.properties"));
        } catch (Exception e) {
            System.out.println("Filen inte hittas");
        }
        ronds = Integer.parseInt(p.getProperty("rond"));
        questionPerRond = Integer.parseInt(p.getProperty("questionsForeach"));
        System.out.println(ronds + "-" + questionPerRond);
    }

    //String för att testa, sedan ska den kunna ta emot olika saker som skickas från klienten
    public void getResponse(Player player, Object o) {
        if (o instanceof String) {
            switch (o.toString()) {
                case "start":
                    getOpponent(player);
                    break;
                case "cancel":
                    player.setIsAvailable(false);
                    break;
            }
        } else if (o instanceof Boolean[]) {
            getScore(player, (boolean[]) o);
        } else {
            System.out.println("response error");
        }
    }

    public void getScore(Player player, boolean[] score) {
        System.out.println("score recieved");
        GameRoom gr = player.getGameRoom();
        if (gr.isFirstPlayer(player)) {
            gr.player1Score.add(score);
        } else {
            gr.player2Score.add(score);
            gr.increaseCurrentRound();
            sendStartPacket(gr);
        }//inte färdig
    }

//    public void getResponse(Player player, boolean[] s) {
//        System.out.println("wrong response");
//        if (player.getGameRoom().currentPlayer != player) {
//            
//        }
//    }
    public void getOpponent(Player player2) {
        for (Player player1 : playerList) {
            if (player1.getIsIsAvailable() && player2 != player1) {
                //skapar upp ett gameroom som innehåller info för den specifika spelomgången
                GameRoom gr = new GameRoom(player1, player2, questionList.getTwoCategories());
                player1.setGameRoom(gr);
                player2.setGameRoom(gr);
                sendStartPacket(gr);
                return;
            }
        }
        player2.setIsAvailable(true);
    }

    public void sendStartPacket(GameRoom gr) {
        System.out.println("startGame");
        gr.getPlayer1().Send(new InfoPacket(gr.getCurrentQuestions(), true));
        gr.getPlayer2().Send(new InfoPacket(gr.getCurrentQuestions(), false));
        System.out.println("sent questions");
    }

    public void sendRoundTwo(GameRoom gr) {

        gr.getPlayer1().Send(new InfoPacket(gr.getCurrentQuestions(), true));
        gr.getPlayer2().Send(new InfoPacket(gr.getCurrentQuestions(), false));
        System.out.println("round two sent");
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

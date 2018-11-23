package GameServer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Protocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, skicka poäng osv*/
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

    //Hanterar data som tagits emot från klienten
    public void getResponse(Player player, Object o) {
        if (o instanceof String) {
            switch (o.toString()) {
                case "start":
                    getOpponent(player);
                    break;
                case "cancel": //gör spelaren otillgänglig när spelaren trycker skickar cancel
                    player.setIsAvailable(false);
                    break;

            }
        } else if (o instanceof boolean[]) { //körs bär spelaren skickat svaren efter varje rond
            System.out.println("boolean recieved");
            getScore(player, (boolean[]) o);
        } else {
            System.out.println("response error");
        }
    }

    public void getScore(Player player, boolean[] score) {
        System.out.println("score recieved");
        GameRoom gr = player.getGameRoom();
        gr.addScore(player, score);
        if (gr.bothAnswered()) {
            gr.increaseCurrentRound();
            sendQuestionsAndScore(gr);
        }
    }

    
    //hittar motspelare när clienten skickat "start"
    public void getOpponent(Player player2) {
        for (Player player1 : playerList) {
            if (player1.getIsIsAvailable() && player2 != player1) {
                //skapar upp ett gameroom som innehåller info för den specifika spelomgången
                GameRoom gr = new GameRoom(player1, player2, questionList.getTwoCategories());
                player1.setGameRoom(gr);
                player2.setGameRoom(gr);
                sendQuestionsAndScore(gr);
                return;
            }
        }
        player2.setIsAvailable(true);
    }



    public void sendQuestionsAndScore(GameRoom gr) {

        gr.getPlayer1().Send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer2Score()));
        gr.getPlayer2().Send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer1Score()));
        System.out.println("round two sent");
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

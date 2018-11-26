package GameServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ServerProtocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, skicka poäng osv*/
    private ArrayList<Player> playerList;
    private QuestionList questionList;
    private int roundsPerGame;
    private int questionsPerRond;

    public ServerProtocol() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/GameServer/GameLength.properties"));
            roundsPerGame = Integer.parseInt(properties.getProperty("roundsPerGame"));
            questionsPerRond = Integer.parseInt(properties.getProperty("questionsPerRound"));
        } catch (IOException e) {
            roundsPerGame = 2;
            questionsPerRond = 3;
            System.out.println("Filen inte hittas");
        }

        questionList = new QuestionList(questionsPerRond, roundsPerGame);
        playerList = new ArrayList<>();
    }

    //Hanterar data som tagits emot från klienten
    public void getResponse(Player player, InfoPacket data) {

        switch (data.getId()) {
            case CHAT:
                playerList.forEach(e -> e.Send(data));
                break;
            case LOGIN:
                player.setPlayerName(data.getName());
                break;
            case START:
                System.out.println("start");
                findOpponent(player);
                break;
            case CANCEL: //gör spelaren otillgänglig när spelaren skickar cancel
                player.setIsAvailable(false);
                System.out.println("cancel");
                break;
            case NEXT_ROUND:
                System.out.println("score");
                recieveScore(player, data);
                break;
            case ENDED:
                break;
        }
    }

    public void recieveScore(Player player, InfoPacket data) {
        System.out.println("score recieved");
        GameRoom gr = player.getGameRoom();
        gr.addScore(player, (boolean[]) data.getScore());
        if (gr.bothAnswered()) {
            sendNewRound(gr);
            gr.increaseCurrentRound();
        }
    }

    //hittar motspelare när clienten skickat "start"
    public void findOpponent(Player player2) {

        for (Player player1 : playerList) {
            if (player1.getIsIsAvailable() && player2 != player1) {
                //skapar upp ett gameroom som innehåller data för den specifika spelomgången
                GameRoom gr = new GameRoom(player1, player2, questionList.GetRandom());
                player1.setGameRoom(gr);
                player2.setGameRoom(gr);
                player1.Send(new InfoPacket(gr.getCurrentQuestions(), player2.getPlayerName()));
                player2.Send(new InfoPacket(gr.getCurrentQuestions(), player1.getPlayerName()));
                System.out.println("start packets sent");
                return;
            }
        }
        player2.setIsAvailable(true);
    }

    public void sendNewRound(GameRoom gr) { //skickar nya frågor och motståndarens poäng
        gr.getPlayer1().Send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer2Score()));
        gr.getPlayer2().Send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer1Score()));
        System.out.println("round packets sent");
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }
}

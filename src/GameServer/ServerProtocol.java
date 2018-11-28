package GameServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
/**
 * handles data sent from the clients
 */
public class ServerProtocol {

    private ArrayList<Player> playerList; //lista med alla inlogga spelare
    private QuestionList questionList; //lista med alla inloggade spelare
    private int roundsPerGame;
    private int questionsPerRond;

    public ServerProtocol() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/GameServer/GameLength.properties"));
            roundsPerGame = Integer.parseInt(properties.getProperty("roundsPerGame"));
            questionsPerRond = Integer.parseInt(properties.getProperty("questionsPerRound"));
        } catch (IOException e) {
            //defaultvärden ifall properties inte funkar
            roundsPerGame = 2; 
            questionsPerRond = 3;
            System.out.println("Filen inte hittas");
        }

        questionList = new QuestionList(questionsPerRond, roundsPerGame);
        playerList = new ArrayList<>();
    }

    /**
     * Handles what to do with the InfoPackets sent from clients
     * @param player, source of the data
     * @param data 
     */
    public void handleInput(Player player, InfoPacket data) {

        switch (data.getId()) {
            case CHAT: //skickar chattmedelanden till alla klienter
                playerList.forEach(e -> e.send(data));
                break;
            case LOGIN:
                player.setPlayerName(data.getName());
                break;
            case START:
                start(player);
                break;
            case CANCEL: //gör spelaren otillgänglig när spelaren skickar cancel
                player.setIsAvailable(false);
                break;
            case NEXT_ROUND: 
                nextRound(player, data);
                break;
            case ENDED: //ej implementerad
                break;
        }
    }
/**
 * adds score from a players round. 
 * If both players have finished the round, sends new 
 * questions and the opponents score to each player 
 */
    private void nextRound(Player player, InfoPacket data) {
        GameRoom gr = player.getGameRoom();
        gr.addScore(player, data.getScore());
        if (gr.bothAnswered()) {
            gr.increaseCurrentRound(); 
            gr.getPlayer1().send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer2Score()));
            gr.getPlayer2().send(new InfoPacket(gr.getCurrentQuestions(), gr.getPlayer1Score()));
        }
    }

    /**
     * finds available opponent and initiates a new game,
     * or sets player as available otherwise
     */
    private void start(Player player2) {
        for (Player player1 : playerList) {
            if (player1.getIsIsAvailable()) {
                //skapar upp ett gameroom som innehåller data för den specifika spelomgången
                GameRoom gr = new GameRoom(player1, player2, questionList.GetRandom());
                player1.setIsAvailable(false);
                player2.setIsAvailable(false);
                player1.setGameRoom(gr);
                player2.setGameRoom(gr);
                player1.send(new InfoPacket(gr.getCurrentQuestions(), player2.getPlayerName()));
                player2.send(new InfoPacket(gr.getCurrentQuestions(), player1.getPlayerName()));
                System.out.println("start packets sent");
                return;
            }
        }
        player2.setIsAvailable(true);
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }
}

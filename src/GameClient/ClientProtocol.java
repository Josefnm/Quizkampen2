package GameClient;

import GameServer.InfoPacket;
import javafx.application.Platform;

/**
 * handles data sent from the server
 */
public class ClientProtocol {

    private ClientMain main;

    public ClientProtocol(ClientMain main) {
        this.main = main;
    }

    /**
     * Handles what to do with the InfoPackets sent from server
     * @param data
     */
    public void handleInput(InfoPacket data) {
        switch (data.getId()) {
            case CHAT:
                main.getChatScene().writeToChatArea(data.getName(), data.getChatMsg());
                break;
            case GAME_LENGTH: //ställer in antal ronder
                main.setRoundsPerGame(data.getRoundsPerGame());
                break;
            case START:
                main.getScoreScene().setOpponentName(data.getName());
                main.getQuestionScene().setQuestions(data.getQuestions());
                //Platform.runlater anropar Applications-tråden. Detta görs för att i javafx så vissa saker bara göras från den tråden.
                Platform.runLater(() -> {
                    main.setQuestionScene();
                    main.closePopupStage();
                });
                break;
            case NEXT_ROUND:
                main.getQuestionScene().setQuestions(data.getQuestions());
                main.getScoreScene().showScore(data.getScore(), 1);
                main.getScoreScene().enableStartBtn();
                main.getScoreScene().whoWon();
                break;
        }
    }
}

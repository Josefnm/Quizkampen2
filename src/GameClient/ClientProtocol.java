package GameClient;

import GameServer.InfoPacket;
import javafx.application.Platform;

public class ClientProtocol {

    private ClientMain main;

    public ClientProtocol(ClientMain main) {
        this.main = main;
    }

    public void handleInput(InfoPacket data) {
        switch (data.getId()) {
            case CHAT:
                main.getChatScene().writeToChatArea(data.getName(),data.getChatMsg());
                break;
            case GAME_LENGTH:
                main.setRoundsPerGame(data.getRoundsPerGame());
                break;
            case START:
                start(data);
                break;
            case NEXT_ROUND:
                main.getQuestionScene().setQuestions(data.getQuestions());
                main.getScoreScene().boolPoints(data.getScore(), 1);
                main.getScoreScene().enableStartBtn();
                break;
        }
    }

    private void start(InfoPacket data) {
       
        main.getScoreScene().setOpponentName(data.getName());
        //Platform.runlater anropar Applications-tråden. Detta görs för att i javafx så kan bara scener köras från den tråden.
        Platform.runLater(() -> {
            main.getQuestionScene().setQuestions(data.getQuestions());
            main.setQuestionScene();
            main.closePopupStage();
        });
    }

}

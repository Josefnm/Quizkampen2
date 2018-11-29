package GameClient.Scenes;

import GameClient.ClientMain;
import GameServer.IdEnum;
import GameServer.InfoPacket;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScoreScene {

    private int roundCounter;
    private int roundsPerGame;
    private int[] totalScores;
    private Button[][][] scoreArray;
    private Text[] totalScoreTexts;

    private Scene scoreScene;
    private ClientMain main;
    private Text userName;
    private Text opponentName;
    private Button startBtn;
    private Label winnerLabel;
    private ImageView opponentAvatar;
    private ImageView userAvatar;

    public ScoreScene(ClientMain main) {
        this.main = main;
        roundCounter = 0;
        roundsPerGame = main.getRoundsPerGame();
        scoreArray = new Button[2][roundsPerGame][3];
        totalScores = new int[]{0, 0};
        scoreArray = new Button[2][roundsPerGame][3];

        userAvatar = new ImageView(new Image(main.getUserAvatar())); //sköter vi själva
        userAvatar.setFitWidth(40);
        userAvatar.setPreserveRatio(true);
        userAvatar.setSmooth(true);
        userAvatar.setCache(true);

        opponentAvatar = new ImageView();
        opponentAvatar.setFitWidth(40);
        opponentAvatar.setPreserveRatio(true);
        opponentAvatar.setSmooth(true);
        opponentAvatar.setCache(true);

        userName = new Text(main.getUserName());
        userName.getStyleClass().add("text_white");
        opponentName = new Text("spelare2"); //kopplat till användarens input
        opponentName.getStyleClass().add("text_white");

        startBtn = new Button("SPELA"); //starta pågående eller nästa rond?
        startBtn.getStyleClass().add("button");
        startBtn.setId("startKnapp"); // grön
        startBtn.setDisable(true);

        Button giveUpBtn = new Button("Ge upp"); //starta pågående eller nästa rond?
        giveUpBtn.getStyleClass().add("button");
        giveUpBtn.setId("giveUpKnapp");

        VBox vBoxScore = new VBox();
        vBoxScore.setSpacing(25);
        vBoxScore.setAlignment(Pos.CENTER);

        for (int i = 0; i < roundsPerGame; i++) { //en rad med knappar för varje runda
            HBox hRow = new HBox();
            hRow.setSpacing(10);
            hRow.setAlignment(Pos.CENTER);
            for (int j = 0; j < 2; j++) { //player1 och player2
                HBox btns = new HBox();
                btns.setSpacing(2);
                for (int k = 0; k < 3; k++) { //3 knappar
                    Button b = new Button();
                    b.getStyleClass().add("button");
                    b.setMinSize(30, 25);
                    btns.getChildren().add(b);
                    scoreArray[j][i][k] = b;  //j=spelare, i=runda, k=fråga
                }
                hRow.getChildren().add(btns);
            }
            Text roundText = new Text("ROND " + (i + 1));
            roundText.getStyleClass().add("text_black");
            hRow.getChildren().add(1, roundText);
            vBoxScore.getChildren().add(hRow);
        }

        startBtn.setOnAction(e -> {
            startBtn.setDisable(true);
            roundCounter++;
            if (roundCounter < roundsPerGame) {
                main.setQuestionScene();
            } else {
                resetScore();
                main.getClient().send(new InfoPacket(IdEnum.ENDED));
                main.setStartScene();
            }
        });

        giveUpBtn.setOnAction(e -> main.setStartScene());

        Text ScoreP1 = new Text("0"); //dynamiskt
        ScoreP1.getStyleClass().add("toppen_score");
        Text Streck = new Text("-"); //kopplat till användarens input
        Streck.getStyleClass().add("toppen_score");
        Text ScoreP2 = new Text("0"); //dynamiskt
        ScoreP2.getStyleClass().add("toppen_score");
        totalScoreTexts = new Text[]{ScoreP1, ScoreP2};
        HBox toppen = new HBox(ScoreP1, Streck, ScoreP2);

        VBox vboxp1 = new VBox(userAvatar, userName);
        vboxp1.setSpacing(5);
        VBox vboxp2 = new VBox(opponentAvatar, opponentName);
        vboxp2.setSpacing(5);
        HBox hboxtop = new HBox(vboxp1, toppen, vboxp2);
        hboxtop.setSpacing(25);
        hboxtop.setAlignment(Pos.CENTER);
VBox topBox =new VBox(new Header(main),hboxtop);
topBox.setSpacing(7);
        winnerLabel = new Label();
        winnerLabel.setId("duVann");
        winnerLabel.setVisible(false);

        VBox finalVBox = new VBox(vBoxScore, winnerLabel);
        finalVBox.setSpacing(35);
        finalVBox.setAlignment(Pos.CENTER);

        HBox hboxKnappar = new HBox(giveUpBtn, startBtn);
        hboxKnappar.setSpacing(25);
        hboxKnappar.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(0, 20, 0, 0));
        BP.setId("pane");
        BP.setTop(topBox);
        BP.setCenter(finalVBox);
        BP.setBottom(hboxKnappar);

        this.scoreScene = new Scene(BP, main.getBoardWidth(), main.getBoardHeight());

        scoreScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }

    /**
     * sets colours on the score buttons
     *
     * @param bool array with wrong/correct answers
     * @param playerNr 0=this player, 1=opponent player
     */
    public void showScore(boolean[] bool, int playerNr) {
        for (int i = 0; i < 3; i++) {
            if (bool[i]) {
                scoreArray[playerNr][roundCounter][i].setId("greenScoreKnapp"); //grön
                totalScores[playerNr]++;
            } else {
                scoreArray[playerNr][roundCounter][i].setId("redScoreKnapp"); //röd
            }
        }
        totalScoreTexts[playerNr].setText(Integer.toString(totalScores[playerNr]));

    }

    public void whoWon() {
        if (roundCounter + 1 == roundsPerGame) { 
            Platform.runLater(() -> {
                if (totalScores[0] > totalScores[1]) {
                    winnerLabel.setText("DU VANN");
                } else if (totalScores[0] < totalScores[1]) {
                    winnerLabel.setText("DU FÖRLORADE");
                } else { //lika
                    winnerLabel.setText("LIKA");
                }
                winnerLabel.setVisible(true);
            });
        }
    }

    /**
     * resets scores after a game ended to prepare for a new game
     */
    public void resetScore() {
        winnerLabel.setVisible(false);
        roundCounter = 0;
        totalScores = new int[]{0, 0};
        Arrays.stream(scoreArray)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .forEach(btn -> btn.setId(null));
    }

    public void enableStartBtn() {
        startBtn.setDisable(false);
    }

    public void setOpponentName(String opponentName) {
        this.opponentName.setText(opponentName);
    }

    public void setOpponentAvatar(String avatarG) {
        this.opponentAvatar.setImage(new Image(avatarG));
    }

    public Scene getScene() {
        return scoreScene;
    }
}

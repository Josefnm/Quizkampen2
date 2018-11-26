package GameClient;

import GameClient.ClientMain;
import GameServer.IdEnum;
import GameServer.InfoPacket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ScoreScene {

    private int roundCounter;
    private int roundsPerGame;
    private Scene scoreScene;
    private ClientMain main;
    private Text userName;
    private Text opponentName;
    private Button startBtn;
    private Text[] totalScoreTexts;
    private int[] totalScores;
    private Button[][][] scoreArray;

    public ScoreScene(ClientMain main) {
        this.main = main;
        roundCounter = 0;
        roundsPerGame = main.getRoundsPerGame();
        scoreArray = new Button[2][roundsPerGame][3];
        totalScores = new int[]{0, 0};
        //label 1 "användarnamn"
        //label 2 "actual användarnamn"-betydligt större font
        //Avatarbild, den ska även gå att ändra
        Image avatarBoy = new Image("./images/boy.png"); //en getter från server i slutändan?
        Image avatarGirl = new Image("./images/girl.png");
        ImageView avatarG = new ImageView();
        ImageView avatar = new ImageView();

        avatar.setImage(avatarBoy);
        avatar.setFitWidth(40);
        avatar.setPreserveRatio(true);
        avatar.setSmooth(true);
        avatar.setCache(true);

        avatarG.setImage(avatarGirl);
        avatarG.setFitWidth(40);
        avatarG.setPreserveRatio(true);
        avatarG.setSmooth(true);
        avatarG.setCache(true);

        userName = new Text(main.getUserName()); 
        userName.setStyle("-fx-font-size: 15; -fx-fill: white;");
        opponentName = new Text("spelare2"); //kopplat till användarens input
        opponentName.setStyle("-fx-font-size: 15; -fx-fill: white;");
        //realUserP2.setStyle("-fx-text-fill: red; -fx-font-size: 35px;");, förstod font size men inte färgen
        startBtn = new Button("SPELA"); //starta pågående eller nästa rond?
        //startbtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        startBtn.setId("button-test2");
        startBtn.setMinSize(85, 45);
        startBtn.setDisable(true);
        Button giveUpBtn = new Button("Ge upp"); //starta pågående eller nästa rond?
        giveUpBtn.setMinSize(35, 25);
        //giveUpBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        giveUpBtn.setId("button-test3");

        Text ScoreP1 = new Text("0"); //dynamiskt
        ScoreP1.setFill(Color.WHITE);
        Text Streck = new Text("-"); //kopplat till användarens input
        Streck.setFill(Color.WHITE);
        Text ScoreP2 = new Text("0"); //dynamiskt
        ScoreP2.setFill(Color.WHITE);
        totalScoreTexts = new Text[]{ScoreP1, ScoreP2};
//        ScoreP1.setFont((20)); //antingen css direkt
//        Streck.setFont((20));
//        ScoreP2.setFont((20));

        VBox vBoxScore = new VBox();
        vBoxScore.setSpacing(25);
        vBoxScore.setAlignment(Pos.CENTER);

        for (int i = 0; i < roundsPerGame; i++) { //en rad med knappar för varje runda
            HBox hRow = new HBox();
            hRow.setSpacing(10);
            hRow.setStyle("-fx-background: rgba(76, 175, 80, 0.3); -fx-text-fill: white;");
            hRow.setAlignment(Pos.CENTER);
            for (int j = 0; j < 2; j++) { // en var för player1 och player2
                HBox btns = new HBox();
                btns.setSpacing(2);
                for (int k = 0; k < 3; k++) { //1 knapp för varje fråga
                    Button b = new Button();
                    b.setMinSize(30, 25);
                    btns.getChildren().add(b);
                    scoreArray[j][i][k] = b;  //j=spelare, i=runda, k=fråga
                }
                hRow.getChildren().add(btns);
            }
            Text roundText = new Text("ROND " + (i + 1));
            roundText.setId("textsvart");
            hRow.getChildren().add(1, roundText);
            vBoxScore.getChildren().add(hRow);
        }

        startBtn.setOnAction(e -> {
            startBtn.setDisable(true);
            roundCounter++;
            if (roundCounter < roundsPerGame) {
                main.setQuestionScene();
            } else {
                main.getClient().send(new InfoPacket(IdEnum.ENDED));
                main.setStartScene();
            }
        });

        giveUpBtn.setOnAction(e -> main.setStartScene());
        //bonus, onclick, popop med spinning wheel + cancelknapp vars funktion stänger popupen

        HBox toppen = new HBox(ScoreP1, Streck, ScoreP2);

        toppen.setStyle("-fx-font-size: 40; -fx-fill: white;");

        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(30, 20, 20, 20));
        BP.setId("pane");
        VBox vboxp1 = new VBox(avatar, userName);
        vboxp1.setSpacing(5);
        VBox vboxp2 = new VBox(avatarG, opponentName);
        vboxp2.setSpacing(5);
        HBox hboxtop = new HBox(vboxp1, toppen, vboxp2);
        hboxtop.setSpacing(25);

        HBox hboxKnappar = new HBox(giveUpBtn, startBtn);
        hboxKnappar.setSpacing(25);

        hboxtop.setAlignment(Pos.CENTER);
        hboxKnappar.setAlignment(Pos.BOTTOM_CENTER);
        BP.setTop(hboxtop);
        BP.setCenter(vBoxScore);
        BP.setBottom(hboxKnappar);

        //BP.setTop(startBtn);
        //BP.setBottom(avatar);
        this.scoreScene = new Scene(BP, main.getBoardWidth(), main.getBoardHeight());

        scoreScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return scoreScene;
    }

    public void boolPoints(boolean[] bool, int playerNr) {

        for (int i = 0; i < 3; i++) {

            if (bool[i]) {
                scoreArray[playerNr][roundCounter][i].setId("button-test2"); //grön
                totalScores[playerNr]++;
            } else {
                scoreArray[playerNr][roundCounter][i].setId("button-test3"); //röd
            }
        }
        totalScoreTexts[playerNr].setText(Integer.toString(totalScores[playerNr]));
    }

    public void setOpponentName(String opponentName) {
        this.opponentName.setText(opponentName);
    }
    public void enableStartBtn(){
        startBtn.setDisable(false);
    }
}

package GameClient;

import GameServer.IdEnum;
import GameServer.InfoPacket;
import java.util.Arrays;
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

    Text realUserP1;
    ImageView userAvatar;

    public ScoreScene(ClientMain main) {
        this.main = main;
        roundCounter = 0;
        roundsPerGame = main.getRoundsPerGame();
        scoreArray = new Button[2][roundsPerGame][3];
        totalScores = new int[]{0, 0};
        //label 1 "användarnamn"
        //label 2 "actual användarnamn"-betydligt större font
        //Avatarbild, den ska även gå att ändra
//        Image avatarBoy = new Image("./images/boy.png"); //en getter från server i slutändan?
//        Image avatarGirl = new Image("./images/girl.png");
        //ImageView avatarG = new ImageView();
        

        //avatar.setImage(avatarBoy);
        scoreArray = new Button[2][roundsPerGame][3];

        Image avatarGirl = new Image("./images/girl.png"); //tills lösning via server

        ImageView avatarG = new ImageView(); //spelare2 bör gå genom server
        userAvatar = new ImageView(main.getUserAvatar()); //sköter vi själva
        userAvatar.setFitWidth(40);
        userAvatar.setPreserveRatio(true);
        userAvatar.setSmooth(true);
        userAvatar.setCache(true);

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
        
        realUserP1 = new Text(""); //kopplat till användarens input
        realUserP1.getStyleClass().add("text_white");
        Text realUserP2 = new Text("spelare2"); //kopplat till användarens input
        realUserP2.getStyleClass().add("text_white");

        Button waitbtn = new Button("VÄNTA"); //logiken saknas men om du blir spelaren som väntar
        //basicly en placeholder utan funktion? bör helst ta "SPELA"-knappens plats
        waitbtn.setId("button-test4");
        waitbtn.setMinSize(85, 45);

        startBtn = new Button("SPELA"); //starta pågående eller nästa rond?
        startBtn.getStyleClass().add("button");
        startBtn.setId("startKnapp"); // grön
startBtn.setDisable(true);
        Button giveUpBtn = new Button("Ge upp"); //starta pågående eller nästa rond?
        giveUpBtn.getStyleClass().add("button");
        giveUpBtn.setId("giveUpKnapp");

        Text ScoreP1 = new Text("0"); //dynamiskt
        ScoreP1.getStyleClass().add("toppen_score");
        Text Streck = new Text("-"); //kopplat till användarens input
        Streck.setFill(Color.WHITE);

        Streck.getStyleClass().add("toppen_score");
        Text ScoreP2 = new Text("0"); //dynamiskt
        ScoreP2.getStyleClass().add("toppen_score");
        totalScoreTexts = new Text[]{ScoreP1, ScoreP2};

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
            Text text = new Text("ROND " + (i + 1));
            text.getStyleClass().add("text_black");
            hRow.getChildren().add(1, text);
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

        HBox toppen = new HBox(ScoreP1, Streck, ScoreP2);

        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(30, 20, 20, 20));
        BP.setId("pane");
        VBox vboxp1 = new VBox(userAvatar, userName);
        vboxp1.setSpacing(5);
        VBox vboxp2 = new VBox(avatarG, opponentName);
        vboxp2.setSpacing(5);
        HBox hboxtop = new HBox(vboxp1, toppen, vboxp2);
        hboxtop.setSpacing(25);

//        //hrow1.setOpacity(0.5); //eftersom den ovan inte funkade :)
//
        HBox hboxKnappar = new HBox(giveUpBtn, startBtn);

        //HBox hboxKnapparPH = new HBox(giveUpBtn, waitbtn); //för placeholdern
        hboxKnappar.setSpacing(25);

        hboxtop.setAlignment(Pos.CENTER);
        hboxKnappar.setAlignment(Pos.BOTTOM_CENTER);
        BP.setTop(hboxtop);
        BP.setCenter(vBoxScore);
        BP.setBottom(hboxKnappar);

        //BP.setTop(startBtn);
        //BP.setBottom(userAvatar);
        this.scoreScene = new Scene(BP, main.getBoardWidth(),main.getBoardHeight());

        scoreScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return scoreScene;
    }

    /**
     * sets colours on the score buttons
     *
     * @param bool array with wrong/correct answers
     * @param playerNr 0=this player, 1=opponent player
     */
    public void showPoints(boolean[] bool, int playerNr) {
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

    public void setOpponentName(String opponentName) {
        this.opponentName.setText(opponentName);
    }

    public void enableStartBtn() {
        startBtn.setDisable(false);
    }

    /**
     * resets scores after a game ended to prepare for a new game
     */
    public void resetScore() {
        roundCounter = 0;
        totalScores = new int[]{0, 0};
        Arrays.stream(scoreArray)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .forEach(btn -> btn.setId(null));
    }
}

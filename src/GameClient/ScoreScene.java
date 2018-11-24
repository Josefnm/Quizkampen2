package GameClient;

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

    int roundCounter = 0;
    int totalRounds = 4; //placeholder
    Scene scoreScene;
    GameMain main;
    private Text userName;
    InfoPacket input;
    Button startbtn;
    Text[] totalScoreTexts;
    int[] totalScores = new int[]{0, 0};

    Button[][][] scoreArray;

    public ScoreScene(GameMain main) {
        this.main = main;
        scoreArray = new Button[2][totalRounds][3];
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

        userName = new Text("spelare1"); //kopplat till användarens input
        userName.setFill(Color.WHITE);
        userName.setStyle("-fx-font-size: 15;");
        Text realUserP2 = new Text("spelare2"); //kopplat till användarens input
        realUserP2.setFill(Color.WHITE);
        realUserP2.setStyle("-fx-font-size: 15;");
        //realUserP2.setStyle("-fx-text-fill: red; -fx-font-size: 35px;");, förstod font size men inte färgen

//        Button waitbtn = new Button("VÄNTA"); //logiken saknas men om du blir spelaren som väntar
//        //basicly en placeholder utan funktion? bör helst ta "SPELA"-knappens plats
//        waitbtn.setId("button-test4");
//        waitbtn.setMinSize(85, 45);
        startbtn = new Button("SPELA"); //starta pågående eller nästa rond?
        //startbtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        startbtn.setId("button-test2");
        startbtn.setMinSize(85, 45);
        startbtn.setDisable(true);
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

        for (int i = 0; i < totalRounds; i++) { //en rad med knappar för varje runda
            HBox hRow = new HBox();
            hRow.setSpacing(10);
            hRow.setStyle("-fx-background: rgba(76, 175, 80, 0.3); -fx-text-fill: white;");
            hRow.setAlignment(Pos.CENTER);
            for (int j = 0; j < 2; j++) { //player1 och player2
                HBox btns = new HBox();
                btns.setSpacing(2);
                for (int k = 0; k < 3; k++) { //3 knappar
                    Button b = new Button();
                    b.setMinSize(30, 25);
                    btns.getChildren().add(b);
                    scoreArray[j][i][k] = b;  //j=spelare, i=runda, k=fråga
                }
                hRow.getChildren().add(btns);
            }
            Text text = new Text("ROND " + (i + 1));
            text.setId("textsvart");
            hRow.getChildren().add(1, text);
            vBoxScore.getChildren().add(hRow);
        }

        startbtn.setOnAction(e -> {
            startbtn.setDisable(true);
            main.questionScene.setQuestions(input.getQuestions());
            main.questionScene.setNextQuestion();
            main.setQuestionScene();

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
        VBox vboxp2 = new VBox(avatarG, realUserP2);
        vboxp2.setSpacing(5);
        HBox hboxtop = new HBox(vboxp1, toppen, vboxp2);
        hboxtop.setSpacing(25);

        HBox hboxKnappar = new HBox(giveUpBtn, startbtn);
        hboxKnappar.setSpacing(25);

        hboxtop.setAlignment(Pos.CENTER);
        hboxKnappar.setAlignment(Pos.BOTTOM_CENTER);
        BP.setTop(hboxtop);
        BP.setCenter(vBoxScore);
        BP.setBottom(hboxKnappar);

        //BP.setTop(startbtn);
        //BP.setBottom(avatar);
        this.scoreScene = new Scene(BP, main.getBoardHeight(), main.getBoardThicc());

        scoreScene.getStylesheets()
                .add(StartScene.class
                        .getResource("stylingCSS.css").toExternalForm());

        //boolPoints(player1Points, main.questionScene.setSvar()/*qs.getBooleans*/);
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

    public void ThreadCalling() {
        new Thread(() -> {
            System.out.println("Scorescene listener: " + Thread.currentThread().getName());
            try {

                input = (InfoPacket) main.client.getInStream().readObject();
                boolPoints(input.getOpponentScore(), 1);
                roundCounter++;
                startbtn.setDisable(false);
                System.out.println("input");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public void setUserName(String userName) {
        this.userName.setText(userName);
    }
}

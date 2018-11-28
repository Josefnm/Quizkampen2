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
    private int totalRounds; //placeholder
    Scene scoreScene;
    GameMain main;
    private Text userName;
    InfoPacket input;
    Button startbtn;
    Text[] totalScoreTexts;
    int[] totalScores = new int[]{0, 0};

    Button[][][] scoreArray;
    
    
    Text realUserP1;
    ImageView avatar;

   public ScoreScene(GameMain main,int rounds) {
        this.main = main;
        totalRounds=rounds;
        scoreArray = new Button[2][totalRounds][3];

        Image avatarGirl = new Image("./images/girl.png"); //tills lösning via server

        ImageView avatarG = new ImageView(); //spelare2 bör gå genom server
        avatar = new ImageView(); //sköter vi själva
        avatar.setFitWidth(40);
        avatar.setPreserveRatio(true);
        avatar.setSmooth(true);
        avatar.setCache(true);

        avatarG.setImage(avatarGirl);
        avatarG.setFitWidth(40);
        avatarG.setPreserveRatio(true);
        avatarG.setSmooth(true);
        avatarG.setCache(true);

        realUserP1 = new Text(""); //kopplat till användarens input
        realUserP1.getStyleClass().add("text_white");
        Text realUserP2 = new Text("spelare2"); //kopplat till användarens input
        realUserP2.getStyleClass().add("text_white");


        Button waitbtn = new Button("VÄNTA"); //logiken saknas men om du blir spelaren som väntar
        //basicly en placeholder utan funktion? bör helst ta "SPELA"-knappens plats
        waitbtn.setId("button-test4");
        waitbtn.setMinSize(85, 45);

        Button startbtn = new Button("SPELA"); //starta pågående eller nästa rond?
        startbtn.getStyleClass().add("button");
        startbtn.setId("startKnapp"); // grön
        
       
        Button giveUpBtn = new Button("Ge upp"); //starta pågående eller nästa rond?
        giveUpBtn.getStyleClass().add("button");
        giveUpBtn.setId("giveUpKnapp");

        

        Text ScoreP1 = new Text("0"); //dynamiskt
        ScoreP1.getStyleClass().add("toppen_score");
        Text Streck = new Text("-"); //kopplat till användarens input
        Streck.getStyleClass().add("toppen_score");
        Text ScoreP2 = new Text("0"); //dynamiskt
        ScoreP2.getStyleClass().add("toppen_score");
        totalScoreTexts = new Text[]{ScoreP1, ScoreP2};

        VBox vBoxScore = new VBox();
        vBoxScore.setSpacing(25);
        vBoxScore.setAlignment(Pos.CENTER);

        for (int i = 0; i < totalRounds; i++) { //en rad med knappar för varje runda
            HBox hRow = new HBox();
            hRow.setSpacing(10);
            //hRow.setStyle("-fx-background: rgba(76, 175, 80, 0.3); -fx-text-fill: white;");
            
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
            //text.setId("textsvart");
            text.getStyleClass().add("text_black");
            hRow.getChildren().add(1, text);
            vBoxScore.getChildren().add(hRow);
        }

        //lambda
        startbtn.setOnAction(e -> {
            startbtn.setDisable(true);
            roundCounter++;
            if (roundCounter < totalRounds) {
                main.getQuestionScene().setQuestions(input.getQuestions());
                main.getQuestionScene().setNextQuestion();
                main.setQuestionScene();
            } else {
                main.setStartScene();
            }
            


        });
        
        giveUpBtn.setOnAction(e -> main.setStartScene());
        

        HBox toppen = new HBox(ScoreP1, Streck, ScoreP2);


        //borderpane, vbox, hbox
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(30, 20, 20, 20));
        BP.setId("pane");
        VBox vboxp1 = new VBox(avatar, realUserP1);
        vboxp1.setSpacing(5);
        VBox vboxp2 = new VBox(avatarG, realUserP2);
        vboxp2.setSpacing(5);
        HBox hboxtop = new HBox(vboxp1, toppen, vboxp2);

        hboxtop.setSpacing(25);

//        //hrow1.setOpacity(0.5); //eftersom den ovan inte funkade :)
//
        HBox hboxKnappar = new HBox(giveUpBtn, startbtn);
        
        //HBox hboxKnapparPH = new HBox(giveUpBtn, waitbtn); //för placeholdern
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
                .add(getClass()
                        .getResource("stylingCSS.css").toExternalForm());

        
    }

    public Scene getScene() {
        return scoreScene;
    }


    public void boolPoints(boolean[] bool, int playerNr) {
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

    public void inputThread() {

        new Thread(() -> {
            System.out.println("Scorescene listener: " + Thread.currentThread().getName());
            try {

                input = (InfoPacket) main.client.getInStream().readObject();
                boolPoints(input.getOpponentScore(), 1);
                startbtn.setDisable(false);
                System.out.println("input");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    
    
    
    
    
    public void setAvatar(Image input)
    {
        System.out.println(input);
        avatar.setImage(input);
    }
    public void setAvatarOpponent(Image input)
    {
        
    }
    public void setUsername(String name)
    {
        realUserP1.setText(name);   
    }
}

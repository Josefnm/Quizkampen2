package GameClient;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author Josef
 */
public class ScoreScene {

    Scene scoreScene;
    Main main;
    Client client;
    QuestionScene qs;
    StartScene sc;
    Scene popUp;

    public ScoreScene(Main main) {
        this.main = main;

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

        
        
        Text realUserP1 = new Text("spelare1"); //kopplat till användarens input
        realUserP1.setFill(Color.WHITE);
        realUserP1.setStyle("-fx-font-size: 15;");
        Text realUserP2 = new Text("spelare2"); //kopplat till användarens input
        realUserP2.setFill(Color.WHITE);
        realUserP2.setStyle("-fx-font-size: 15;");
        //realUserP2.setStyle("-fx-text-fill: red; -fx-font-size: 35px;");, förstod font size men inte färgen
        

        
        Button waitbtn = new Button("VÄNTA"); //logiken saknas men om du blir spelaren som väntar
        //basicly en placeholder utan funktion? bör helst ta "SPELA"-knappens plats
        waitbtn.setId("button-test4");
        waitbtn.setMinSize(85, 45);
        
        Button startbtn = new Button("SPELA"); //starta pågående eller nästa rond?
        //startbtn.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        startbtn.setId("button-test2");
        startbtn.setMinSize(85, 45);
        Button giveUpBtn = new Button("Ge upp"); //starta pågående eller nästa rond?
        giveUpBtn.setMinSize(35, 25);
        //giveUpBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        giveUpBtn.setId("button-test3");
        
        Text ScoreP1 = new Text("1"); //dynamiskt
        ScoreP1.setFill(Color.WHITE);
        Text Streck = new Text("-"); //kopplat till användarens input
        Streck.setFill(Color.WHITE);
        Text ScoreP2 = new Text("5"); //dynamiskt
        ScoreP2.setFill(Color.WHITE);
        
//        ScoreP1.setFont((20)); //antingen css direkt
//        Streck.setFont((20));
//        ScoreP2.setFont((20));
        
        //dags för skitbuttons i rows? enkelt skapade i loops men kanske svårt med hboxande då?
        Button b1r1 = new Button();
        b1r1.setMinSize(30, 25);
        Button b2r1 = new Button();
        b2r1.setMinSize(30, 25);
        Button b3r1 = new Button();
        b3r1.setMinSize(30, 25);
        Text r1 = new Text("ROND 1");
//        r1.setFont((10));
        r1.setId("score-test");
        Button b4r1 = new Button();
        b4r1.setMinSize(30, 25);
        Button b5r1 = new Button();
        b5r1.setMinSize(30, 25);
        Button b6r1 = new Button();
        b6r1.setMinSize(30, 25);
        
        Button b1r2 = new Button();
        b1r2.setMinSize(30, 25);
        Button b2r2 = new Button();
        b2r2.setMinSize(30, 25);
        Button b3r2 = new Button();
        b3r2.setMinSize(30, 25);
        Text r2 = new Text("ROND 2");
        
       
//        r1.setFont((10));
        r2.setId("score-test");
        Button b4r2 = new Button();
        b4r2.setMinSize(30, 25);
        Button b5r2 = new Button();
        b5r2.setMinSize(30, 25);
        Button b6r2 = new Button();
        b6r2.setMinSize(30, 25);
        
       
        
        
        //lambda
        startbtn.setOnAction(e -> {main.setQuestionScene(); }); //hej funkar 100%
        giveUpBtn.setOnAction(e -> main.setStartScene());
        //bonus, onclick, popop med spinning wheel + cancelknapp vars funktion stänger popupen
        
        
        
        HBox toppen = new HBox(ScoreP1, Streck, ScoreP2);
        
        
        toppen.setStyle("-fx-font-size: 40; -fx-fill: white;");
        
//        HBox under = new HBox(realUserP1, realUserP2);
//        under.setSpacing(50);
        
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
        HBox hbox1 = new HBox(b1r1,b2r1,b3r1); //rond1baren
        hbox1.setSpacing(2);
        HBox hbox2 = new HBox(b4r1,b5r1,b6r1);
        hbox2.setSpacing(2);
        HBox hrow1 = new HBox(hbox1, r1, hbox2);
        hrow1.setSpacing(10);
        hrow1.setStyle("-fx-background: rgba(76, 175, 80, 0.3); -fx-text-fill: white;");
        //hrow1.setOpacity(0.5); //eftersom den ovan inte funkade :)
        
        HBox hboxKnappar = new HBox(giveUpBtn, startbtn);
        //HBox hboxKnapparPH = new HBox(giveUpBtn, waitbtn); //för placeholdern
        hboxKnappar.setSpacing(25);
        
        
        
        HBox hbox3 = new HBox(b1r2,b2r2,b3r2); //rond1baren
        hbox3.setSpacing(2);
        HBox hbox4 = new HBox(b4r2,b5r2,b6r2);
        hbox4.setSpacing(2);
        HBox hrow2 = new HBox(hbox3, r2, hbox4);
        hrow2.setSpacing(10);
        hrow2.setId("transparent");
        
        VBox vboxAllt = new VBox(hrow1, hrow2);
        vboxAllt.setSpacing(25);
        
        //hbox.setAlignment(Pos.CENTER);
        hboxtop.setAlignment(Pos.CENTER);
        vboxAllt.setAlignment(Pos.CENTER);
        hrow1.setAlignment(Pos.CENTER);
        hrow2.setAlignment(Pos.CENTER);
        hboxKnappar.setAlignment(Pos.BOTTOM_CENTER);
        

        

        
        
        BP.setTop(hboxtop);
        BP.setCenter(vboxAllt);
        BP.setBottom(hboxKnappar);
        
        
        //BP.setTop(startbtn);
        //BP.setBottom(avatar);
        
        
        

        this.scoreScene = new Scene(BP, main.getBoardHeight(), main.getBoardThicc());
        
        scoreScene.getStylesheets()
                        .add(StartScene.class
                                .getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return scoreScene;
    }
}
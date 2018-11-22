package GameClient;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScoreScene {

    Scene scoreScene;
    GameMain main;
    Client client;
    QuestionScene qs;
    StartScene sc;
    Scene popUp;

    public ScoreScene(GameMain main) {
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
        Text realUserP2 = new Text("spelare2"); //kopplat till användarens input
        Button startbtn = new Button("Play"); //starta pågående eller nästa rond?
        startbtn.setMinSize(75, 25);
        Button giveUpBtn = new Button("Ge Upp"); //starta pågående eller nästa rond?
        giveUpBtn.setMinSize(25, 15);

        Text ScoreP1 = new Text("1"); //dynamiskt
        Text Streck = new Text("-"); //kopplat till användarens input
        Text ScoreP2 = new Text("5"); //dynamiskt
        
//        ScoreP1.setFont((20)); //antingen css direkt
//        Streck.setFont((20));
//        ScoreP2.setFont((20));
        
        //dags för skitbuttons i rows? enkelt skapade i loops men kanske svårt med hboxande då?
        Button b1r1 = new Button();
        Button b2r1 = new Button();
        Button b3r1 = new Button();
        Text r1 = new Text("ROND 1");
//        r1.setFont((10));
        r1.setId("score-test");
        Button b4r1 = new Button();
        Button b5r1 = new Button();
        Button b6r1 = new Button();
        
        Button b1r2 = new Button();
        Button b2r2 = new Button();
        Button b3r2 = new Button();
        Text r2 = new Text("ROND 2");
//        r1.setFont((10));
        r2.setId("score-test");
        Button b4r2 = new Button();
        Button b5r2 = new Button();
        Button b6r2 = new Button();
        
       
        
        
        //lambda
        startbtn.setOnAction(e -> {main.setQuestionScene(); }); //hej funkar 100%
        giveUpBtn.setOnAction(e -> main.setStartScene());
        //bonus, onclick, popop med spinning wheel + cancelknapp vars funktion stänger popupen
        
        
        HBox toppen = new HBox(ScoreP1, Streck, ScoreP2);
        toppen.setStyle("-fx-font-size: 40");
//        HBox under = new HBox(realUserP1, realUserP2);
//        under.setSpacing(50);
        
        //borderpane, vbox, hbox
        BorderPane BP = new BorderPane();
        BP.setId("pane");
        VBox vboxp1 = new VBox(avatar, realUserP1);
        VBox vboxp2 = new VBox(avatarG, realUserP2);
        HBox hboxtop = new HBox(vboxp1, toppen, vboxp2);
        hboxtop.setSpacing(25);
        HBox hbox1 = new HBox(b1r1,b2r1,b3r1); //rond1baren
        HBox hbox2 = new HBox(b4r1,b5r1,b6r1);
        HBox hrow1 = new HBox(hbox1, r1, hbox2);
        hrow1.setSpacing(25);
        HBox hboxKnapp = new HBox(startbtn);
        
        
        HBox hbox3 = new HBox(b1r2,b2r2,b3r2); //rond1baren
        HBox hbox4 = new HBox(b4r2,b5r2,b6r2);
        HBox hrow2 = new HBox(hbox3, r2, hbox4);
        hrow2.setSpacing(25);
        
        VBox vboxAllt = new VBox(hboxtop, hrow1, hrow2, hboxKnapp);
        
        //hbox.setAlignment(Pos.CENTER);
        hboxtop.setAlignment(Pos.CENTER);
        hboxKnapp.setAlignment(Pos.CENTER);
        vboxAllt.setAlignment(Pos.CENTER);

        
        //BP.setBottom(hboxKnapp);
        BP.setCenter(vboxAllt);
        
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
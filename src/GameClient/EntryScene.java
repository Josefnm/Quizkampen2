/*
 * JavaUtveckling 2018
 */
package GameClient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EntryScene {

    GameMain main;
    
    Scene entryScene;
    StartScene startScene;
    String userNameInput;
    ScoreScene scoreScene;
    
    private Image avatarBoy;
    private Image avatarGirl;
    private Image avatarPikatchu;
    private Image avatarSnorlax;

    
//Användaren addar sitt användarnamn samt gender (gender en dropdownmeny?)    
    public EntryScene(GameMain main) {
        this.main = main; 
    
    avatarBoy = new Image("./images/boy.png"); //en getter från server i slutändan?
    avatarGirl = new Image("./images/girl.png");
    avatarPikatchu = new Image("./images/pikachu.png");
    avatarSnorlax = new Image("./images/Snorlax.png");
    
    Text logoNamn = new Text("Quizkampen"); //ändra font samt storlek, även plats?
    //logoNamn.setStyle("-fx-fill: white; -fx-font-size: 40; -fx-font-family: comic sans ms;");
    logoNamn.setId("logonamn");
    Text userName = new Text("Ange användarnamn:");
    userName.setId("textsvart");
    TextField userNameInputField = new TextField();
    userNameInputField.setMaxSize(100,25);
    
    //fixa choicebox
    Text whatGender = new Text("Jag identifierar mig som:");
    whatGender.setId("textsvart");
    ChoiceBox<String> genderMenu = new ChoiceBox();
    
    genderMenu.getItems().addAll("Välj ett alternativ", "Jag är en stor pojke!", 
                                "flicka!", "ÅÅåhh! snorlax!", "not sure :)");
    genderMenu.setValue("Välj ett alternativ"); //då får vi ett värde som står "synligt/överst"
    
    Button passBtn = new Button("Gå vidare");
    passBtn.setId("button-test4");
    passBtn.setOnAction(e -> {
        userNameInput = userNameInputField.getText();
        startScene.setUsername(userNameInput);
        scoreScene.setUsername(userNameInput);
        getChoice(genderMenu);
        main.setStartScene();
    });
    
    BorderPane bp = new BorderPane();
    bp.setPadding(new Insets(50, 20, 20, 20));
    
    
    VBox vbox = new VBox(userName, userNameInputField, whatGender, genderMenu, passBtn);
    vbox.setSpacing(5);
    vbox.setAlignment(Pos.CENTER);
    bp.setCenter(vbox);
    VBox vboxLOGO = new VBox(logoNamn);
    vboxLOGO.setAlignment(Pos.CENTER);
    
    
    bp.setId("pane");
    
    
    bp.setTop(vboxLOGO);
    bp.setCenter(vbox);
        
    this.entryScene = new Scene(bp, main.getBoardHeight(), main.getBoardThicc());

    entryScene.getStylesheets()
                .add(StartScene.class/*funkar ändå?*/
                        .getResource("stylingCSS.css").toExternalForm());   
        
    
    }
    public void getChoice(ChoiceBox<String> choice)
    {
        String genderChoice = choice.getValue();
        System.out.println(genderChoice);
        if (genderChoice == "Jag är en stor pojke!") { 
            startScene.setAvatar(avatarBoy);
            scoreScene.setAvatar(avatarBoy);
        }
        else if (genderChoice == "flicka!") {
            startScene.setAvatar(avatarGirl);
            scoreScene.setAvatar(avatarGirl);
        }
        else if (genderChoice == "ÅÅåhh! snorlax!") {
            startScene.setAvatar(avatarSnorlax);
            scoreScene.setAvatar(avatarSnorlax);
        }
        else { //gäller för default samt "not sure" atm
            startScene.setAvatar(avatarPikatchu);
            scoreScene.setAvatar(avatarPikatchu);
        }
    }
    public String getUsername()
    {
        System.out.println("in EntryScene, getUsername "+userNameInput);
        return userNameInput;
    }
    public Scene getScene()
    {
        return entryScene;
    }
    public void startInitiated(StartScene sc) {
        startScene = sc;
    }
    public void scoreInitiated(ScoreScene score) {
        scoreScene = score;
    }
}

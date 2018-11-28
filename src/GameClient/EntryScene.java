/*
 * JavaUtveckling 2018
 */
package GameClient;

import GameServer.IdEnum;
import GameServer.InfoPacket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EntryScene {
    
    private ClientMain main;
    private Scene entryScene;
    private TextField userNameInputField;
    
    String userNameInput;
    ScoreScene scoreScene;
    
    private String avatarBoy;
    private String avatarGirl;
    private String avatarPikatchu;
    private String avatarSnorlax;

//Användaren addar sitt användarnamn samt gender (gender en dropdownmeny?)    
    public EntryScene(ClientMain main) {
        this.main = main;
        
        avatarBoy = "./images/boy.png"; //en getter från server i slutändan?
        avatarGirl = "./images/girl.png";
        avatarPikatchu = "./images/pikachu.png";
        avatarSnorlax = "./images/Snorlax.png";
        
        Text logoNamn = new Text("Quizkampen"); //ändra font samt storlek, även plats?
        //logoNamn.setStyle("-fx-fill: white; -fx-font-size: 40; -fx-font-family: comic sans ms;");
        logoNamn.setId("logonamn");
        Text userName = new Text("Ange användarnamn:");
        //userName.setId("textsvart");
        userName.getStyleClass().add("text_black");
        userNameInputField = new TextField();
        userNameInputField.getStyleClass().add("text_white");
        userNameInputField.setMaxSize(100, 25);

        //fixa choicebox
        Text whatGender = new Text("Jag identifierar mig som:");
        //whatGender.setId("textsvart");
        whatGender.getStyleClass().add("text_black");
        ChoiceBox<String> genderMenu = new ChoiceBox();
        genderMenu.getStyleClass().add("text_black");
        
        genderMenu.getItems().addAll("Välj ett alternativ", "Jag är en stor pojke!",
                "flicka!", "ÅÅåhh! snorlax!", "not sure :)");
        genderMenu.setValue("Välj ett alternativ"); //då får vi ett värde som står "synligt/överst"

        Button passBtn = new Button("Gå vidare");
        //passBtn.setId("button-test4");
        passBtn.getStyleClass().add("button");
        passBtn.setId("goVidareKnapp");
        passBtn.setOnAction(e -> {
            getChoice(genderMenu);
            main.setUserName(userNameInputField.getText());
            main.getClient().send(new InfoPacket(userNameInputField.getText(),main.getUserAvatar(),IdEnum.LOGIN));
            main.initScenes();
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
        
        this.entryScene = new Scene(bp, main.getBoardWidth(), main.getBoardHeight());
        
        entryScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }
    
    public void getChoice(ChoiceBox<String> choice) {
        String genderChoice = choice.getValue();
        System.out.println(genderChoice);
        
        switch (genderChoice) {
            
            case "Jag är en stor pojke!":
                main.setUserAvatar(avatarBoy);
                break;
            case "flicka!":
                main.setUserAvatar(avatarGirl);
                break;
            case "ÅÅåhh! snorlax!":
                main.setUserAvatar(avatarSnorlax);
                break;
            case "not sure :)":
            default:
                main.setUserAvatar(avatarPikatchu);
                break;
        }
    }
    
    public String getUsername() {
        System.out.println("in EntryScene, getUsername " + userNameInput);
        return userNameInput;
    }
    
    public Scene getScene() {
        return entryScene;
    }
    
}

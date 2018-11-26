/*
 * JavaUtveckling 2018
 */
package GameClient.Scenes;

import GameClient.ClientMain;
import GameServer.IdEnum;
import GameServer.InfoPacket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EntryScene {

    private final ClientMain main;
    private Scene entryScene;
    private TextField userNameInputField;

//Användaren addar sitt användarnamn samt gender (gender en dropdownmeny?)    
    public EntryScene(ClientMain main) {
        this.main = main;
        //userNameInput = "hehe2";    
        Text logoNamn = new Text("Quizkampen"); //ändra font samt storlek, även plats?
        //logoNamn.setStyle("-fx-fill: white; -fx-font-size: 40; -fx-font-family: comic sans ms;");
        logoNamn.setId("logonamn");
        Text userName = new Text("Ange användarnamn:");
        userName.setId("textsvart");
        userNameInputField = new TextField();
        userNameInputField.setMaxSize(100, 25);

        //fixa choicebox
        Text whatGender = new Text("Jag identifierar mig som:");
        whatGender.setId("textsvart");
        MenuButton genderMenu = new MenuButton("Välj könsidentifikation");
        MenuItem pickedMan = new MenuItem("Pojke");
        MenuItem pickedWoman = new MenuItem("Flicka");
        MenuItem pickedRandom = new MenuItem("Not sure tbh");
        genderMenu.getItems().addAll(pickedMan, pickedWoman, pickedRandom);

        //TODO genderMenu-logik
        Button passBtn = new Button("Gå vidare");
        passBtn.setId("button-test4");
        passBtn.setOnAction(e -> {
            main.setUserName(userNameInputField.getText());
            main.getClient().send(new InfoPacket(userNameInputField.getText()));
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
        //bp.setBottom(hboxKnappar);
        //dropdownmeny för gender?
        entryScene = new Scene(bp, main.getBoardWidth(), main.getBoardHeight());
        entryScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return entryScene;
    }
}

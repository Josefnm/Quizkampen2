package GameClient;

import GameServer.InfoPacket;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatScene {

    private ClientMain main;
    private Scene chatScene;
    private TextField writeField;
    private TextArea chatArea;
    private Button returnBtn;
    private Button writeBtn;
    private VBox vBox;
    private HBox hBox;

    public ChatScene(ClientMain main) {
        this.main = main;

        writeField = new TextField();
        writeField.requestFocus();
        chatArea = new TextArea();
        chatArea.setEditable(false);
        writeBtn = new Button("Skicka");
        returnBtn = new Button("Tillbaka");
        hBox = new HBox(writeBtn, returnBtn);
        hBox.setAlignment(Pos.CENTER);
        vBox = new VBox(chatArea, writeField, hBox);
        vBox.setId("pane");
        chatArea.setPrefSize(main.getBoardWidth(), main.getBoardHeight());
        chatScene = new Scene(vBox, main.getBoardWidth(), main.getBoardHeight());
        chatScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());

        writeField.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendChatMsg();
            }
        });
        writeBtn.setOnAction(e -> {
            sendChatMsg();
        });
        returnBtn.setOnAction(e -> {
            main.setCurrentScene();
        });
    }

    private void sendChatMsg() {
        main.getClient().send(new InfoPacket(main.getUserName()+": "+writeField.getText()));
        writeField.setText("");
    }

    public void setFocus() {
        writeField.requestFocus();
    }

    public Scene getScene() {
        return chatScene;
    }

    public void writeToChatArea( String chatMsg) {
        chatArea.appendText( chatMsg + "\n");
    }
}

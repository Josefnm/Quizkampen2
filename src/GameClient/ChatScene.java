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

    private VBox vBox;
 

    public ChatScene(ClientMain main) {
        this.main = main;

        writeField = new TextField();
        writeField.requestFocus();
        writeField.getStyleClass().add("text_black");
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.getStyleClass().add("text_black");
        
        
        Header header=new Header(main);
        header.setReturnImg();
        vBox = new VBox(header,chatArea, writeField);
        vBox.setId("pane");
        chatArea.setPrefSize(main.getBoardWidth(), main.getBoardHeight());
        chatScene = new Scene(vBox, main.getBoardWidth(), main.getBoardHeight());
        chatScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());

        writeField.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendChatMsg();
            }
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

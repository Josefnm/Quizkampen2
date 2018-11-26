package GameClient;

import GameClient.ClientMain;
import GameServer.InfoPacket;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChatScene {
    
    private ClientMain main;
    private Scene chatScene;
    private TextField userInput;
    private TextArea chatArea;
    private Button returnBtn;
    private Button writeBtn;
    private VBox vBox;
    private HBox hBox;
    
    
    public ChatScene(ClientMain main) {
        this.main = main;
        
        {
            userInput = new TextField();
            userInput.requestFocus();
            chatArea = new TextArea();
            chatArea.setEditable(false);
            writeBtn = new Button("Skicka");
            returnBtn = new Button("Tillbaka");
            hBox = new HBox(writeBtn, returnBtn);
            hBox.setAlignment(Pos.CENTER);
            vBox = new VBox(chatArea, userInput, hBox);
            chatArea.setPrefSize(main.getBoardWidth(), main.getBoardHeight());
            chatScene = new Scene(vBox, main.getBoardWidth(), main.getBoardHeight());
        }
        writeBtn.setOnAction(e -> {
            main.getClient().send(new InfoPacket(main.getUserName(), userInput.getText()));
            userInput.setText("");
        });
        returnBtn.setOnAction(e->{
            main.setCurrentScene();
        });
    }
    public void setFocus(){
        userInput.requestFocus();
    }
    public Scene getScene() {
        return chatScene;
    }
    public void writeToChatArea(String name, String chatMsg){
        chatArea.appendText(name+": "+chatMsg+"\n");
    }
}

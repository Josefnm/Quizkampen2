package GameClient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientMain extends Application {

    private int boardHeight = 506;
    private int boardWidth = 300;
    private int roundsPerGame;
    private String userName;
    private Image userAvatar;

    private Stage primaryStage;
    private Stage popupStage;
    private StartScene startScene;
    private QuestionScene questionScene;
    private ScoreScene scoreScene;
    private EntryScene entryScene;
    private PopupScene popupScene;
    private ChatScene chatScene;
    private Scene currentScene;
    
    private ClientTCP client;
    

    @Override
    public void start(Stage primaryStage) {
        
        client = new ClientTCP(this);
        this.primaryStage = primaryStage;
        entryScene = new EntryScene(this);       
        primaryStage.setTitle("QUIZKAMPEN!");
        setEntryScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
/**
 * initiates scenes after info from EntryScene has been entered
 */
    public void initScenes() {
        popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED); //tar bort standardpanelen högst upp som vanliga windowsfönster har
        popupStage.initModality(Modality.APPLICATION_MODAL); //ger popupkänsla
        popupScene = new PopupScene(this);
        popupStage.setScene(popupScene.getScene());
        chatScene=new ChatScene(this);
        startScene = new StartScene(this);
        questionScene = new QuestionScene(this);
        scoreScene = new ScoreScene(this);
    }
    public void setChatScene(){
        this.currentScene=primaryStage.getScene();
        primaryStage.setScene(chatScene.getScene());
        chatScene.setFocus();
    }
    public void setCurrentScene(){
        primaryStage.setScene(currentScene);
    }
    
    public void setQuestionScene() {
        questionScene.setNextQuestion();
        primaryStage.setScene(questionScene.getScene());
    }

    public void setScoreScene() {
        primaryStage.setScene(scoreScene.getScene());
    }

    public void setEntryScene() {
        primaryStage.setScene(entryScene.getScene());
    }

    public void setStartScene() {
        primaryStage.setScene(startScene.getScene());
    }

    public void setPopupScene() { 
        popupStage.setX(primaryStage.getX() + getBoardWidth() / 4);
        popupStage.setY(primaryStage.getY() + getBoardHeight() / 4);
        popupStage.show();
    }

    public QuestionScene getQuestionScene() {
        return questionScene;
    }

    public ScoreScene getScoreScene() {
        return scoreScene;
    }
    public ChatScene getChatScene() {
        return chatScene;
    }

    public ClientTCP getClient() {
        return client;
    }

    public void closePopupStage() {
        popupStage.close();
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoundsPerGame() {
        return roundsPerGame;
    }

    public void setRoundsPerGame(int roundsPerGame) {
        this.roundsPerGame = roundsPerGame;
    }

    public Image getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(Image userAvatar) {
        this.userAvatar = userAvatar;
    }
}

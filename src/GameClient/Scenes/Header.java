package GameClient.Scenes;

import GameClient.ClientMain;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Josef
 */
public class Header extends BorderPane {

    ClientMain main;
    Button chatBtn;
    Button exitBtn;
    Label label;
    ImageView chatImg;
    ImageView exitImg;
    ImageView returnImage;
    double xOffset;
    double yOffset;

    public Header(ClientMain main) {
        this.main = main;
        chatBtn = new Button();
        exitBtn = new Button();
        label = new Label();

        chatImg = new ImageView(new Image("./images/chat.png"));
        chatImg.setFitHeight(30);
        chatImg.setPreserveRatio(true);
        chatImg.setSmooth(true);
        chatImg.setCache(true);
        returnImage = new ImageView(new Image("./images/return.png"));
        returnImage.setFitHeight(30);
        returnImage.setPreserveRatio(true);
        returnImage.setSmooth(true);
        returnImage.setCache(true);
        exitImg = new ImageView(new Image("./images/close.png"));
        exitImg.setFitHeight(30);
        exitImg.setPreserveRatio(true);
        exitImg.setSmooth(true);
        exitImg.setCache(true);

//        chatBtn.setBackground(Background.EMPTY);
//        exitBtn.setBackground(Background.EMPTY);
        //chatBtn.setId("transparent");
        chatBtn.setGraphic(chatImg);
        exitBtn.setGraphic(exitImg);
        exitBtn.setId("header");
        chatBtn.setId("header");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(45.0);
        setPrefWidth(main.getBoardWidth());
        setStyle("-fx-background-color: rgba(72, 61, 139, 0.3);");
        BorderPane.setAlignment(chatBtn, Pos.CENTER);
        BorderPane.setMargin(chatBtn, new Insets(0.0, 7.0, 0.0, 0.0));
        setRight(chatBtn);

        BorderPane.setAlignment(exitBtn, Pos.CENTER);

        BorderPane.setMargin(exitBtn, new Insets(0.0, 0.0, 0.0, 7.0));
        setLeft(exitBtn);

        BorderPane.setAlignment(label, Pos.CENTER);
        label.setText("Quizkampen");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setId("logonamn");
        BorderPane.setMargin(label, new Insets(0.0, 5.0, 0.0, 5.0));
        setCenter(label);
        this.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());

        chatBtn.setOnAction(e -> {
            if (main.getChatScene().getScene() != main.getScene()) {
                main.setChatScene();

            } else {
                main.setPreviousScene();

            }
        });
        exitBtn.setOnAction(e -> {
            try {
                main.getClient().getInStream().close();
                main.getClient().getOutStream().close();
                main.getClient().getSocket().close();
                Platform.exit();
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        label.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = main.getPrimaryStage().getX() - event.getScreenX();
                yOffset = main.getPrimaryStage().getY() - event.getScreenY();
            }
        });


label.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                main.getPrimaryStage().setX(event.getScreenX() + xOffset);
                main.getPrimaryStage().setY(event.getScreenY() + yOffset);
            }
        });
    }

    public void setReturnImg() {
        chatBtn.setGraphic(returnImage);
    }
}

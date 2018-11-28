package GameClient;

import GameClient.ClientMain;
import GameServer.IdEnum;
import GameServer.InfoPacket;
import GameServer.Question;
import com.sun.prism.paint.Color;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionScene {

    //egenskaper för frågor:
    ArrayList<String> score = new ArrayList<>();
    ArrayList<Question> questions;
    boolean[] roundScore= new boolean[3];
    String correctAnswer;
    int questionsAnsweredNr = 0;

    //egenskaper för scenen:
    ClientMain main;
    Button next = new Button("Next");
    private Scene scene;
    ArrayList<Button> buttons = new ArrayList();
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    Label questionLabel = new Label();

    public QuestionScene(ClientMain main) {
        this.main = main;
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            button.setMinSize(127,102);
            button.setOnAction(click);
            button.setId("svarsKnapp");
            buttons.add(button);
            grid.add(button, i/2, i%2);
        }
        border.setPadding(new Insets(30,10,20,10));
        next.setDisable(true);
        next.setMinSize(50, 25);
        next.setOnAction(nextBtnEvent);
        hbox.getChildren().add(next);
        hbox.setAlignment(Pos.CENTER);
        
        questionLabel.setMinSize(260, 180);
        questionLabel.setId("fråga");
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.setStyle("-fx-background-color: White; -fx-border-radius: 10 10 10 10;"
                + "-fx-background-radius: 10 10 10 10");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(6);
        grid.setVgap(6);
        vbox.getChildren().add(questionLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        border.setId("pane");
        border.setTop(vbox);
        border.setCenter(grid);
        border.setBottom(hbox);
        scene = new Scene(border,main.getBoardWidth(),main.getBoardHeight());
        scene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
        
    }

    EventHandler click = new EventHandler() {
        @Override
        public void handle(Event event) {
            Button btn = (Button) event.getSource();
            if (btn.getText().equals(correctAnswer)) {
                btn.setStyle("-fx-background-color: Green");
                roundScore[questionsAnsweredNr] = true; //skickas till servern för poäng
                for (Button b : buttons) {
                    b.setDisable(true);
                }
                

            } else {
                btn.setStyle("-fx-background-color: Red");
                roundScore[questionsAnsweredNr] = false;     
                for (Button b : buttons) {
                    if (b.getText().equals(correctAnswer)) {
                        b.setStyle("-fx-background-color: Green");
                    }
                    b.setDisable(true);
                }
            }
            //Next knappen kan bara användas om man har svarat på frågan
            next.setDisable(false);

        }
    };

    EventHandler nextBtnEvent = new EventHandler() {
        @Override
        public void handle(Event event) {
            questionsAnsweredNr++;
            if (questionsAnsweredNr == questions.size()) {
                main.getClient().send(new InfoPacket(roundScore.clone()));
                questionsAnsweredNr = 0;
                main.setScoreScene();
                main.getScoreScene().boolPoints(roundScore,0);
            } else {
                setNextQuestion();
            }
            next.setDisable(true);
        }
    };

    public void setNextQuestion() {
        Button btn = new Button();
        int i = 0;
        for (Button b : buttons) {
            correctAnswer = questions.get(questionsAnsweredNr).getCorrectAnswer();
            b.setDisable(false);
            b.setStyle(btn.getStyle());
            b.setText(questions.get(questionsAnsweredNr).getAnswer(i));
            questionLabel.setText(questions.get(questionsAnsweredNr).getQuestion());
            i++;
        }

    }

    public Scene getScene() {
        return scene;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        
    }
}

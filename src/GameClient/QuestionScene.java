package GameClient;

import GameServer.InfoPacket;
import GameServer.Question;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class QuestionScene {

    //egenskaper för frågor:
    private ArrayList<Question> questions;
    private boolean[] roundScore= new boolean[3];
    private String correctAnswer;
    private int questionsAnsweredNr = 0;

    //egenskaper för scenen:
    private ClientMain main;
    private Button next = new Button("Next");
    private Scene scene;
    private ArrayList<Button> buttons = new ArrayList();
    private HBox hbox = new HBox();
    private Label label = new Label();
    

    public QuestionScene(ClientMain main) {
        this.main = main;
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            button.setMinSize(300, 200);
            button.setOnAction(click);
            buttons.add(button);
            grid.add(button, i/2, i%2);
        }
        next.setDisable(true);
        next.setMinSize(50, 25);
        next.setOnAction(nextBtnEvent);
        hbox.getChildren().add(next);
        hbox.setAlignment(Pos.CENTER);
        label.setMinSize(600, 200);
        label.setAlignment(Pos.CENTER);
        border.setTop(label);
        grid.setAlignment(Pos.CENTER);
        border.setCenter(grid);
        border.setBottom(hbox);
        scene = new Scene(border);
    }

    EventHandler click = new EventHandler() {
        @Override
        public void handle(Event event) {
            Button btn = (Button) event.getSource();
            if (btn.getText().equals(correctAnswer)) {
                btn.setStyle("-fx-background-color: Green");
                roundScore[questionsAnsweredNr] = true; 
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
            if (questionsAnsweredNr == questions.size()) { //skickar svaren till servern när alla frågor är besvarade
                main.getClient().send(new InfoPacket(roundScore.clone()));
                questionsAnsweredNr = 0;
                main.setScoreScene();
                main.getScoreScene().showPoints(roundScore,0);
            } else {
                setNextQuestion();
            }
            next.setDisable(true);
        }
    };

    public void setNextQuestion() {
        int i = 0;
        for (Button b : buttons) {
            correctAnswer = questions.get(questionsAnsweredNr).getCorrectAnswer();
            b.setDisable(false);
            b.setStyle(null);
            b.setText(questions.get(questionsAnsweredNr).getAnswer(i));
            label.setText(questions.get(questionsAnsweredNr).getQuestion());
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

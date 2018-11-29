package GameClient;

import GameServer.InfoPacket;
import GameServer.Question;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class QuestionScene {

    //egenskaper för frågor:
    private ArrayList<Question> questions;
    private boolean[] roundScore = new boolean[3];
    private String correctAnswer;
    private int questionsAnsweredNr = 0;

    //egenskaper för scenen:
    private ClientMain main;
    private Button next = new Button("Next");
    private Scene scene;
    private ArrayList<Button> buttons = new ArrayList();
    private Label questionLabel = new Label();

    public QuestionScene(ClientMain main) {
        this.main = main;

        GridPane answerBtnGrid = new GridPane();

        for (int i = 0; i < 4; i++) {
            Button button = new Button();
            button.setMinSize(127, 102);
            button.setMaxSize(127, 102);
            button.setOnAction(click);
            button.getStyleClass().add("button");
            button.setId("svarsKnapp");
            buttons.add(button);
            answerBtnGrid.add(button, i / 2, i % 2);
        }

        next.setId("nextKnapp");
        next.setDisable(true);
        next.setMinSize(50, 25);
        next.setOnAction(nextBtnEvent);
        HBox hbox = new HBox();
        hbox.getChildren().add(next);
        hbox.setAlignment(Pos.CENTER);

        questionLabel.setMinSize(260, 180);
        questionLabel.setMaxSize(260, 180);
        questionLabel.setAlignment(Pos.CENTER);
        questionLabel.setStyle("-fx-background-color: White; -fx-border-radius: 10 10 10 10;"
                + "-fx-background-radius: 10 10 10 10");
        answerBtnGrid.setAlignment(Pos.CENTER);
        answerBtnGrid.setHgap(6);
        answerBtnGrid.setVgap(6);
        VBox vbox = new VBox();
        vbox.getChildren().add(new Header(main));
        vbox.getChildren().add(questionLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        BorderPane border = new BorderPane();
        border.setPadding(new Insets(0, 0, 20, 0));
        border.setId("pane");
        border.setTop(vbox);
        border.setCenter(answerBtnGrid);
        border.setBottom(hbox);
        scene = new Scene(border, main.getBoardWidth(), main.getBoardHeight());
        scene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());

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
            next.setDisable(true);
            questionsAnsweredNr++;
            if (questionsAnsweredNr == questions.size()) { //skickar svaren till servern när alla frågor är besvarade
                main.getClient().send(new InfoPacket(roundScore));
                questionsAnsweredNr = 0; //återställer räknaren
                main.setScoreScene();
                main.getScoreScene().showScore(roundScore, 0);
            } else {
                setNextQuestion();
            }
        }
    };

    public void setNextQuestion() {
        int i = 0;
        for (Button b : buttons) {
            correctAnswer = questions.get(questionsAnsweredNr).getCorrectAnswer();
            b.setDisable(false);
            b.setStyle(null);
            b.setText(questions.get(questionsAnsweredNr).getAnswer(i));
            b.setTextAlignment(TextAlignment.CENTER);
            b.setWrapText(true);
            i++;
        }
        questionLabel.setText(questions.get(questionsAnsweredNr).getCategory() + ":\n\n"
                + questions.get(questionsAnsweredNr).getQuestion());
        questionLabel.setId("question");
        questionLabel.setTextAlignment(TextAlignment.CENTER);
        questionLabel.setWrapText(true);

    }

    public Scene getScene() {
        return scene;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;

    }
}

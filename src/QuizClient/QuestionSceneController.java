package QuizClient;

import QuizServer.Question;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Josef
 */
public class QuestionSceneController implements Initializable {

    @FXML
    Label questionLabel;
    @FXML
    Button answer1Button;
    @FXML
    Button answer2Button;
    @FXML
    Button answer3Button;
    @FXML
    Button answer4Button;
    
    Button[] answerButtons;
    Question currentQuestion;
    Client client;

    public QuestionSceneController(Client client) {
        this.client = client;
        client.qsc = this;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("test1");
        answerButtons = new Button[]{answer1Button, answer2Button, answer3Button, answer4Button};
        for (int i = 0; i < 4; i++) {
            
            answerButtons[i].setOnAction(e -> {
                Button thisButton = (Button) e.getSource();
                boolean bool = client.send(thisButton.getText());
                if (bool) {
                    thisButton.setStyle("-fx-background-color: Green");
                } else{
                    thisButton.setStyle("-fx-background-color: Red");
                }

            });
        }
        setNewQuestion(client.q);
    }

    public void setNewQuestion(Question q) {

        currentQuestion = q;
        questionLabel.setText(q.getQuestion());
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(q.getAnswers()[i]);
        }
        answer1Button.setText(q.getAnswers()[0]);
        answer2Button.setText(q.getAnswers()[1]);
        answer3Button.setText(q.getAnswers()[2]);
        answer4Button.setText(q.getAnswers()[3]);

    }
}

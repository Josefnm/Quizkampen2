package GameClient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Josef
 */
public class StartScene { //fixar abstrakt senare

    Scene startScene;
    Main main;
    Client client;
    QuestionScene qs = new QuestionScene(main);
    
    public StartScene(Main main) {
        this.main=main;
        
        

        Button startbtn = new Button();

        startbtn.setText("Calculate");

        startbtn.setOnAction(new EventHandler<ActionEvent>() {

            

            @Override

            public void handle(ActionEvent event) {
                
                main.setQuestionScene(qs.getScene());
                System.out.println("hhehe");
            }
            
        });

        System.out.println("Constructor");
        
        StackPane sp = new StackPane();
  
        sp.getChildren().add(startbtn);

        this.startScene = new Scene(sp, 300, 250);
            //HEJ
    }
    public Scene getScene()
    {
        return startScene;
    }
    //main.set
    
        //primaryStage.setTitle("BMI Calculator");

        //primaryStage.setScene(startScene);

        //primaryStage.show();

    }
    
    
//    this.setName("QuizKampen");
//    createAndShowUI();
//    initComponents();
//    
//    private void createAndShowUI() {
//        this.setResizable(false);
//        this.setSize(500, 500);
//        this.setLocationRelativeTo(null); //Placerar fönstret i mitten
//        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//    private void initComponents() {
//        Button startButton = new Button("Start Game");
//    }
//    
//
//    public void actionPerformed(ActionEvent e) {
//        try {
//            //start
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//}

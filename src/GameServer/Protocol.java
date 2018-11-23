package GameServer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Protocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, se om svar på 
    frågor är rätt osv*/
    private ArrayList<Player> playerList;
    private QuestionList questionList;
    Properties p;
    int ronds;
    int questionPerRond;

    public Protocol() {
        questionList = new QuestionList();
        playerList = new ArrayList<>();
        p = new Properties();
        try{
            p.load(new FileInputStream("src/GameServer/ronds.properties"));
            System.out.println("ok");
        }
        catch(Exception e){
            System.out.println("Filen inte hittas");
        }
        ronds = Integer.parseInt(p.getProperty("rond"));
        questionPerRond = Integer.parseInt(p.getProperty("questionsForeach"));
        System.out.println(ronds + "-" + questionPerRond);
    }

    //String för att testa, sedan ska den kunna ta emot olika saker som skickas från klienten
    public void getResponse(Player player, String s) {
        switch (s) {
            case "start":
                getOpponent(player);  
                System.out.println("start"+player.getName());
                break;
            case "cancel":
                player.setIsAvailable(false);
                break;
        }
    }
    public void getResponse(Player player, int s) {       
        System.out.println("wrong response");
        if(player.getGameRoom().currentPlayer != player){
            
        }
    }
    
    

//    public void getResponse(Player player, boolean[] s) {
//        System.out.println("wrong response");
//        if (player.getGameRoom().currentPlayer != player) {
//            
//        }
//    }


    public void getOpponent(Player player1) {
        for (Player player2 : playerList) {
            if (player2.getIsIsAvailable() && player1 != player2) {
                System.out.println(questionList.questionList.size()+"size of questionList");
                ArrayList<ArrayList<Question>> tempQuestions=questionList.getTwoCategories();
                GameRoom gr = new GameRoom(player2, player1,tempQuestions);
                player2.setGameRoom(gr);
                player1.setGameRoom(gr);
                player1.startGame();
                player2.startGame();
                return;
            }
        }
        player1.setIsAvailable(true);

    }

    public void changeCurrentPlayer() {

    }
    
    public QuestionList getQuestionList(){
        return questionList;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}

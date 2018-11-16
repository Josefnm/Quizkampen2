package GameServer;

public class Main {

    Server server;
    QuestionList questionList;
    GameRoomHandler gameRoomHandler;
    public Main() {
        questionList=new QuestionList();
        gameRoomHandler=new GameRoomHandler();
        server = new Server();
        
    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}

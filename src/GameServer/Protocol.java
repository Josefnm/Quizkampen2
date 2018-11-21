package GameServer;

public class Protocol {

    /* Från den här klassen ska klientens föerfrågningar till servern hanteras.
    Ska kunna skicka tillbaka frågor, hitta motspelare, se om svar på 
    frågor är rätt osv*/
    PlayerHandler playerList;
    QuestionList questionList;

    public Protocol() {
        questionList = new QuestionList();
        playerList = new PlayerHandler();
    }

    //String för att testa, sedan ska den kunna ta emot olika saker som skickas från klienten
    public void getResponse(Player player, String s) {
        switch (s) {
            case "start":
                getOpponent(player);
                break;
            case "cancel":
                player.setIsAvailable(false);

        }
    }

    public void getOpponent(Player player1) {
        for (Player player2 : playerList.playerList) {
            if (player2.getIsIsAvailable()&&player1!=player2) {
                GameRoom gr=new GameRoom(player2, player1);
 
                player2.setIsAvailable(false);
                player1.setIsAvailable(false);
                player1.startGame();
                player2.startGame();
                return;
            }
        }
        player1.setIsAvailable(true);
       
    }

    public void changeCurrentPlayer() {

    }
}

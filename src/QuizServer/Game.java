package QuizServer;

/**
 *
 * @author Josef
 */
public class Game {
Player player1;
Player player2;

}


    /*
    KLient-Programmet:
    -Main // kör programmet, byter skener
    -Client //kopplar till server, tar emot och skickar data
    Scenes:
    -Inloggning
    -SvaraPåFrågor
    -SeResultat

    Server-programmet:
    Main  // kör programmet
    Server //Lyssnar efter nya klienter
    Player // Skapas av server, innehåller koppling till en specifik klient
    Game // Innehåller spelsessioner
    Question // varje fråga är en instans av denna klass

    
    








*/
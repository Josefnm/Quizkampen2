package GameServer;

import java.io.Serializable;

public enum IdEnum implements Serializable{
    /**
     * rounds per game
     */
    GAME_LENGTH,
    /**
     * Login info from client
     */
    LOGIN,
    /**
     * Start request from client
     * or questions and opponent data from server
     */
    START, 
    /**
     * cancels start request from client
     */
    CANCEL, 
    /**
     *  round result from client
     *  opponent round result and new questions from server
     */
    NEXT_ROUND, 
    ENDED,
    CHAT
}

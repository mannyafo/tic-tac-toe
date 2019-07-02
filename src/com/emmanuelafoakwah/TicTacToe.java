package com.emmanuelafoakwah;

/**
 * This class implements the core game logic, responsible for:
 * - Creating the game board
 * - Executing turns
 * - Checking for game over conditions (win or board full)
 * - Printing the contents of the board
 * - Printing an instructive board indicating the index positions of board locations
 * @author Emmanuel Afoakwah
 */

public class TicTacToe {

    // Creating class variables
    private char[] board;       // The game board - linear array with 9 slots
    private char userToken;      // User token
    private char aiToken;        // AI token
    private char winnerToken;    // Winner token
    private char currentToken;   // Token of the current turn player

    /**
     * Constructor for the game instance
     * @param userToken the token used to represent the player on the board
     * @param aiToken the token used to represent the ai on the board
     */
    public TicTacToe(char userToken, char aiToken) {
        this.userToken = userToken;
        this.aiToken = aiToken;
        this.winnerToken = '-';
        this.currentToken = userToken;
        this.board = generateBoard();
    }

    /**
     * Generates an "empty" game board
     * @return an "empty" char array representing the game board
     */
    public char[] generateBoard(){
        char[] board = new char[9];
        for(int i = 0; i<9; i++){
            board[i]='-';
        }
        return board;
    }

}

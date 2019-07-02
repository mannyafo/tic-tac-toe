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
        // Create empty char array of size 9 to represent the board
        char[] board = new char[9];

        // Loop through array and assign each element the "blank" token '-'
        for(int i = 0; i<9; i++){
            board[i]='-';
        }
        return board;
    }

    /**
     * Executes turn given the position to assign to player (user and ai - determined by current token)
     * @param position is the board position to assign to the current token
     * @return a boolean indicating whether the turn was completed
     */
    public boolean executeTurn(int position){
        // Turn player/ai position choice (1-9) into array index (0-8)
        int index = position-1;
        
        // If the selection is valid assign the current token and flip the turn, else return false
        if(validIndex(index)){
            board[index] = currentToken;
            currentToken = (currentToken==userToken) ? aiToken : userToken;
        }
        return validIndex(index);
    }

    /**
     * Checks whether the index choice is valid
     * @param index is the index to validate
     * @return a boolean indicating whether the index choice is valid
     */
    public boolean validIndex(int index){
         return withinRange(index) && isIndexTaken(index);
    }

    /**
     * Checks whether index is within array range (0-8)
     * @param index is the index to validate
     * @return a boolean indicating whether the index choice is within range
     */
    public boolean withinRange(int index){
        return index>0 && index<9;
    }

    /**
     * Checks whether the index selection is already occupied
     * @param index is the index to validate
     * @return a boolean indicating whether the index selection is available
     */
    public boolean isIndexTaken(int index){
        return board[index]!='-';
    }

}

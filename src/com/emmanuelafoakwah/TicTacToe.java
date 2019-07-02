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
        //boolean valid = validIndex(index);
        boolean valid = withinRange(index) && !isIndexTaken(index);
        // If the selection is valid assign the current token and flip the turn, else return false
        if(valid){
            board[index] = currentToken;
            currentToken = (currentToken==userToken) ? aiToken : userToken;
        }
        return valid;
    }

    /**
     * Checks whether the index choice is valid
     * @param index is the index to validate
     * @return a boolean indicating whether the index choice is valid
     */
    public boolean validIndex(int index){
        //System.out.println(withinRange(index));
        //System.out.println(!isIndexTaken(index));
         return withinRange(index) && !isIndexTaken(index);
    }

    /**
     * Checks whether index is within array range (0-8)
     * @param index is the index to validate
     * @return a boolean indicating whether the index choice is within range
     */
    public boolean withinRange(int index){
        System.out.println("Indx range: " + index);
        return index>-1 && index<9;
    }

    /**
     * Checks whether the index selection is already occupied
     * @param index is the index to validate
     * @return a boolean indicating whether the index selection is available
     */
    public boolean isIndexTaken(int index){
        System.out.println("Taken: " + board[index]);
        return board[index]!='-';
    }

    /**
     *  Checks the game board to determine whether a win condition has been met
     * @return a boolean indicating whether the win condition
     */
    public boolean winCondition(){
        // Booleans indicating whether the win condition has been met
        boolean diagonalAndMiddle = (rDiag() || lDiag() || middleRow() || secondCol()) && board[4]!='-';
        boolean topAndFirst = (topRow() || firstCol()) && board[0]!='-';
        boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8]!='-';

        // Assign winner token according to win conditions
        if(diagonalAndMiddle){
            this.winnerToken = board[4];
        }else if(topAndFirst){
            this.winnerToken = board[0];
        }else if(bottomAndThird){
            this.winnerToken = board[8];
        }
        return diagonalAndMiddle || topAndFirst || bottomAndThird;
    }

    /**
     * Checks the top row for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean topRow(){
        return board[0]==board[1] && board[1]==board[2];
    }

    /**
     * Checks the middle row for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean middleRow(){
        return board[3]==board[4] && board[4]==board[5];
    }

    /**
     * Checks the bottom row for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean bottomRow(){
        return board[6]==board[7] && board[7]==board[8];
    }

    /**
     * Checks the first column for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean firstCol(){
        return board[0]==board[3] && board[3]==board[6];
    }

    /**
     * Checks the second column for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean secondCol(){
        return board[1]==board[4] && board[4]==board[7];
    }

    /**
     * Checks the third column for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean thirdCol(){
        return board[2]==board[5] && board[5]==board[8];
    }

    /**
     * Checks the right diagonal for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean rDiag(){
        return board[0]==board[4] && board[4]==board[8];
    }

    /**
     * Checks the left diagonal for the win condition
     * @return a boolean indicating whether the win condition is met
     */
    public boolean lDiag(){
        return board[2]==board[4] && board[4]==board[6];
    }

    /**
     * Checks whether the game board is full (i.e. draw condition)
     * @return a boolean indicating whether the draw condition is met
     */
    public boolean drawCondition(){
        // Loops throught the array, if empty token is found return false, otherwise return true
        for(int i = 0; i<9; i++){
            if(board[i]=='-'){
                return false;
            }
        }
        return true;
    }

    /**
     * Processes game over conditions to determine whether to continue the game
     * If not return a string indicating the outcome of the game
     * @return a string indicating the game state after turn execution
     */
    public String turnOutcome(){
        // Booleans indicating whether the win/ draw conditions are met
        boolean win = winCondition();
        boolean draw = drawCondition();

        // Return string indicating game state
        if(win){
            return this.winnerToken + " wins!";
        }else if(draw){
            return "Draw!";
        }else{
            return "continue";
        }
    }

    /**
     * Returns an int array containing the free indexes on the game board
     * @return an array of integers containing the available indexes on the game board
     */
    public int[] availablePositions(){
        int n = 0; // Number of free indexes
        // Calculate free indexes
        for(int i = 0; i<9; i++){
            if(board[i]=='-'){
                n++;
            }
        }
        // Create and populate array of free indexes
        int[] av = new int[n];
        int index = 0;
        for(int i = 0; i<9; i++){
            if(board[i]=='-'){
                av[index]=(i+1);
                index++;
            }
        }
        return av;
    }

    /**
     * Prints the current status of the game board
     */
    public void printGameBoard(){
        System.out.println("-------------");
        for(int i = 0; i<9; i++){
            if(i%3 == 0 && i!=0){
                System.out.println();
                System.out.println("-------------");
            }
            System.out.print(" | " + board[i]);
        }
        System.out.println();
        System.out.println("-------------");
        System.out.println();
    }

    /**
     * Prints an instructive game board indicating the numbers the player
     * should enter to occupy a given position on the game board
     */
    public static void printInstructionBoard(){
        System.out.println("-------------");
        for(int i = 0; i<9; i++){
            if(i%3 == 0 && i!=0){
                System.out.println();
                System.out.println("-------------");
            }
            System.out.print(" | " + (i+1));
        }
        System.out.println();
        System.out.println("-------------");
        System.out.println();
    }

    /**
     * Get the user token
     * @return the user token
     */
    public char getUserToken() {
        return userToken;
    }

    /**
     * Get the current token
     * @return the current token
     */
    public char getCurrentToken() {
        return currentToken;
    }
}

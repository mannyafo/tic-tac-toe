package com.emmanuelafoakwah;

import java.util.Scanner;

/**
 * This class implements the session and game management
 * @author Emmanuel Afoakwah
 */
public class RunGame {

    // Create session variables
    private static Scanner scan = new Scanner(System.in);
    private static char playerToken = '-';
    private static char aiToken = '-';

    // Boolean indicating whether the player wishes to continue playing
    private static boolean play = true;

    public static void main(String[] args) {

        // Session start sequence - assign tokens
        System.out.println("Welcome to Tic-Tac-Toe!");
        getPlayerToken();
        getAIToken();

        // Show instructions
       printInstructions();

        // Session loop
        while(play){

            // Create game and ai instances
            TicTacToe game = new TicTacToe(playerToken, aiToken);
            AI ai = new AI();

            // Execute the core game loop
            gameLoop(game, ai);

            // Handle game over state
            sessionManager(game);
        }

    }

    /**
     * Displays the outcome of the previous game and allows the player to restart
     * @param game the game from which to display the outcome
     */
    public static void sessionManager(TicTacToe game){
        System.out.println(game.turnOutcome());
        System.out.println();

        System.out.println("Play again? - Enter Y or N");
        char again = scan.next().charAt(0);
        play = (again=='Y');
        System.out.println();
    }

    /**
     * This function implements the core game loop, processing:
     * - Player turn
     * - AI turn
     * Using the executeTurn() function in the game instance
     * @param game the game instance to run the game loop
     * @param ai the ai instance to execute the ai position selection
     */
    private static void gameLoop(TicTacToe game, AI ai){

        while(game.turnOutcome().equals("continue")){

            if(game.getCurrentToken()==game.getUserToken()){
                // User turn
                System.out.println("It's your turn, enter your position!");
                int pos = scan.nextInt();
                while(game.executeTurn(pos)==false){
                    System.out.println("Invalid input! please try again ...");
                    pos = scan.nextInt();
                }
                System.out.println("You selected: " + pos);
            }else{
                // AI turn
                System.out.println("AI's turn!");
                int aiPos = ai.selectPosition(game);
                game.executeTurn(aiPos);
                System.out.println("AI selected: " + aiPos);
            }
            // Show the state of the board after turn
            game.printGameBoard();
        }
    }

    /**
     * Prints instructions for the user
     */
    private static void printInstructions(){
        System.out.println();
        System.out.println("Enter the number of the board position you would like to take! \n");
        TicTacToe.printInstructionBoard();
        System.out.println();
    }

    /**
     * Gets the player token input from the player
     */
    private static void getPlayerToken(){
        System.out.println("Enter a single character to represent YOU in the game ...");
        playerToken = scan.next().charAt(0);
    }

    /**
     * Gets the AI token input from the player
     */
    private static void getAIToken(){
        System.out.println("Enter a single character to represent the AI in the game ...");
        aiToken = scan.next().charAt(0);
    }

}

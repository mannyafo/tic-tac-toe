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

        // Create game instance
        TicTacToe game = new TicTacToe(playerToken, aiToken);
        AI ai = new AI();

        // Show instructions
        printInstructions(game);

        // Session loop
        while(play){


            play=false;
        }


    }

    /**
     * Prints instructions for the user
     * @param game the game instance from which to print the instructive board
     */
    public static void printInstructions(TicTacToe game){
        System.out.println();
        System.out.println("Enter the number of the board position you would like to take! \n");
        game.printInstructionBoard();
        System.out.println();
    }

    /**
     * Gets the player token input from the player
     */
    public static void getPlayerToken(){
        System.out.println("Enter a single character to represent YOU in the game ...");
        playerToken = scan.next().charAt(0);
    }

    /**
     * Gets the AI token input from the player
     */
    public static void getAIToken(){
        System.out.println("Enter a single character to represent the AI in the game ...");
        aiToken = scan.next().charAt(0);
    }

}

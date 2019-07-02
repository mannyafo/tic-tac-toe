package com.emmanuelafoakwah;

import java.util.Random;

/**
 * This class implements the random artificial intelligence
 * @author Emmanuel Afoakwah
 */

public class AI {

    /**
     * This function randomly selects an available index on the gameo board
     * of the given game instance
     * @param game the game instance to select an index from
     * @return the index selected by the random selection algorithm
     */
    public int selectPosition(TicTacToe game){
        // Sets the array of available indexes
        int[] available = game.availableIndexes();

        // Randomly selects an available index
        Random r = new Random();
        int i = Math.abs(r.nextInt()%available.length);
        int selection = available[i];
        return selection;
    }

}

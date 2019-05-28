package com.example.ak.coinseeker;

import android.content.SharedPreferences;
import android.widget.Button;


import java.util.Random;

public class CoinSweeper {
    private int rows = 0;
    private int cols = 0;
    private int coins = 0;
    private int scans = 0;
    private int num_coins_found = 0;
    private int matrix[][] = new int[rows][cols];
    private int revealing_matrix[][] = new int[rows][cols];

    private void update_revealing_matrix(int row, int col, int offset) {
        for (int i = 0; i < rows; i++)
            revealing_matrix[i][col] += offset;
        for (int i = 0; i < cols; i++)
            revealing_matrix[row][i] += offset;
    }

    public int getHiddenCoinsCount(int x, int y){
        return revealing_matrix[x][y];
    }

    public int getMatrixValue(int x, int y){ return matrix[x][y]; }

    public int getScanCount(){
        return scans;
    }

    public int getCoinsFound(){
        return num_coins_found;
    }


    public void newGame(int N_of_Rows, int N_of_Cols, int N_of_coins) {
        //set game variables
        rows = N_of_Rows;
        cols = N_of_Cols;
        coins = N_of_coins;
        scans = 0;
        num_coins_found = 0;
        matrix = new int[rows][cols];
        revealing_matrix = new int[rows][cols];
        for (int i =0; i<N_of_Rows; i++){
            for (int j =0; j<N_of_Cols; j++){
                matrix[i][j] = 0;
                revealing_matrix[i][j] = 0;
            }
        }
        //generate random x, y index
        int count = 0;
        while (count < N_of_coins){
            Random random = new Random();
            int x = random.nextInt(N_of_Rows);
            int y = random.nextInt(N_of_Cols);
            //check for duplicated coin, set the corresponding index to -1 to represent unrevealed coin and update the revealing matrix
            if (matrix[x][y] == 0){
                matrix[x][y] = -1;
                update_revealing_matrix(x, y, 1);
                count++;
            }
        }
    }

    public int checkbutton(int row, int col){
                //display the coin.
                if (matrix[row][col] == -1){
                    num_coins_found++;
                    update_revealing_matrix(row, col, -1);//update the hidden coin count matrix
                    matrix[row][col] = 1; //change the value to 1 to represent the coin has been found
                    return 1;
                }
                else if (matrix[row][col] == 1)
                {
                    matrix[row][col] = 3; //this way when you press the coin again, the scan will not add again
                    scans++;
                    return 3;
                }

                else if(matrix[row][col]== 0){
                    matrix[row][col] = 2;
                    scans++;
                    return 2;
                }
                return -2; //-2 for clicking on already clicked buttons
            }


}


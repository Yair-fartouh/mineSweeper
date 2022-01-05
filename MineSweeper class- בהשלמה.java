/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mineswee123;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author YairF
 */
public class Mine {

    private int mineCount;
    private int rowCount;
    private int columnCount;
    private int[][] Board;
    private int[][] UpdatedBoard;

    public Mine() {
    }

    public int getMineCount() {
        return mineCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int[][] getBoard() {
        return Board;
    }

    public void setBoard(int[][] Board) {
        this.Board = Board;
    }

    public int[][] getUpdatedBoard() {
        return UpdatedBoard;
    }

    public void setUpdatedBoard(int[][] UpdatedBoard) {
        this.UpdatedBoard = UpdatedBoard;
    }

    /**
     * If one of these cases happens a message will pop up to the user. (1) If
     * there are more bombs than defined he will pop up a message "Too many
     * bombs". (2) If the array size is less than 2x2, then a message will pop
     * up. (3) If the user enters bombs less than zero or zero bombs, he will
     * enter the user an arrival message.
     *
     * @param row - Array lines.
     * @param column - Array columns.
     * @param bombs - The amount of bombs.
     */
    public void Executions(int row, int column, int bombs) {
        if (bombs > (row * column)) {
            throw new IllegalArgumentException("Too many bombs");
        }
        if (row < 2 || column < 2) {
            throw new IllegalArgumentException("Small area");
        }
        if (bombs <= 0) {
            throw new IllegalArgumentException("No bombs");
        }

    }

    /**
     * A function that receives an array size and quantity of bombs, and
     * randomly places the bombs on the array.
     *
     * @param i - Array lines.
     * @param j - Array columns.
     * @param booms - The amount of bombs.
     */
    public void board(int i, int j, int booms) {       //random
        this.rowCount = i;
        this.columnCount = j;
        this.mineCount = booms;
        Random rand = new Random();
        int r;
        int c;
        int boom;
        r = i;
        c = j;
        boom = booms;
        Board = new int[r][c];
        UpdatedBoard = new int[r][c];
        while (boom > 0) {
            r = rand.nextInt(i);
            c = rand.nextInt(j);
            if (Board[r][c] == 10) {
                continue;
            }
            Board[r][c] = 10;
            boom--;

        }
    }

    /**
     * A function that checks the amount of bombs around the received position.
     *
     * @param i - Row position.
     * @param j - Column position.
     * @return - Returns the amount of bombs around the location.
     */
    public int boomAround(int i, int j) {
        int sum;
        int r;
        int c;
        sum = 0;
        r = i;
        c = j;
        if (Board[r][c] == 10) {        //i'm on a bomb?
            sum = -1;
            return sum;
        }
        for (r = i - 1; r <= i + 1; r++) {
            for (c = j - 1; c <= j + 1; c++) {
                if (r < 0 || r >= rowCount || c < 0 || c >= columnCount) {  //The boundary of the array
                    continue;
                }
                if (i == r && j == c) {     //If I'm on my position, skip it.
                    continue;
                }
                if (boom(Board[r][c]) == true) {    //If there's a bomb, sum it up for me.
                    sum++;
                }

            }
        }
        return sum;
    }

    /**
     * If the amount obtained is -1, a bomb went up, and the game was over.
     *
     * @param sum - Amount of bombs.
     * @return - If this is "true", then you have lost.
     */
    public boolean GameOver(int sum) {
        if (sum == -1) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param index - Contents of the current location.
     * @return
     */
    public boolean boom(int index) {     //boom
        if (index == 10) {
            return true;
        }
        return false;
    }

    public void printArray() {

        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColumnCount(); j++) {
                System.out.print(UpdatedBoard[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println("---------------------");

    }

    /**
     * A function that unifies all functions and executes them.
     */
    public void begin() {
        Mine ma;
        ma = new Mine();
        Scanner sc;
        sc = new Scanner(System.in);
        int row;
        int column;
        int sum;
        boolean gameOver;
        boolean getBoolOfGame;
        gameOver = false;
        while (gameOver == false) {
            row = sc.nextInt();
            column = sc.nextInt();
            //Executions(row, column, mineCount);
            if (UpdatedBoard[row][column] != 0 && UpdatedBoard[row][column] != 10) {
                System.out.println("Already open, please enter another location");
                continue;
            }
            sum = boomAround(row, column);
            getBoolOfGame = GameOver(sum);
            if (getBoolOfGame == true) {
                System.out.println("Game over\nyou lose !!!");
                gameOver = true;
            }
            if (gameOver == false) {
                if (sum == 0) {
                    sum = 9;
                }
                UpdatedBoard[row][column] = sum;
                printArray();

            }
        }

    }

}

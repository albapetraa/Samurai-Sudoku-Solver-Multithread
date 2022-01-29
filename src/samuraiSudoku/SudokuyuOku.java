/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samuraiSudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Emre
 */
public class SudokuyuOku {

    public ArrayList<Integer> sayilar = new ArrayList<Integer>();
    public int[][] board
            = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public SudokuyuOku() {
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void calistir() throws FileNotFoundException {
        String x = new String();

        try {
            File myObj = new File("sudoku.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                x += data;
            }

            for (int i = 0; i < x.length(); i++) {
                int sayi = 0;
                char c = x.charAt(i);
                int sayac = 0;
                if (c != '*') {
                    sayi = Character.getNumericValue(c);
                    sayac++;
                } else {
                    sayi = 0;
                    sayac++;
                }
                sayilar.add(sayi);
            }
            isle();

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public boolean uygunMu(int row, int col) {
        int[][] yasak = {{0, 9}, {0, 10}, {0, 11}, {1, 9}, {1, 10}, {1, 11}, {2, 9}, {2, 10}, {2, 11}, {3, 9}, {3, 10}, {3, 11}, {4, 9}, {4, 10}, {4, 11}, {5, 9}, {5, 10}, {5, 11},
        {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}, {9, 5}, {10, 0}, {10, 1}, {10, 2}, {10, 3}, {10, 4}, {10, 5}, {11, 0}, {11, 1}, {11, 2}, {11, 3}, {11, 4}, {11, 5},
        {9, 15}, {9, 16}, {9, 17}, {9, 18}, {9, 19}, {9, 20}, {10, 15}, {10, 16}, {10, 17}, {10, 18}, {10, 19}, {10, 20}, {11, 15}, {11, 16}, {11, 17}, {11, 18}, {11, 19}, {11, 20},
        {15, 9}, {15, 10}, {15, 11}, {16, 9}, {16, 10}, {16, 11}, {17, 9}, {17, 10}, {17, 11}, {18, 9}, {18, 10}, {18, 11}, {19, 9}, {19, 10}, {19, 11}, {20, 9}, {20, 10}, {20, 11}};
        for (int i = 0; i < yasak.length; i++) {
            for (int j = 0; j < yasak[i].length - 1; j++) {
                int kontrolRow = yasak[i][j];
                int kontrolCol = yasak[i][j + 1];
                if (row == kontrolRow && col == kontrolCol) {
                    return false;
                }
            }
        }
        return true;
    }
    public int q = 0;

    public void isle() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                if (uygunMu(i, j)) {
                    if (q == 369) {
                        break;
                    }
                    board[i][j] = sayilar.get(q);
                    q++;
                } else {

                }

            }
        }
    }

    private ArrayList<String> getParts(String string, int partitionSize) {
        ArrayList<String> parts = new ArrayList<String>();
        int len = string.length();
        for (int i = 0; i < len; i += partitionSize) {
            parts.add(string.substring(i, Math.min(len, i + partitionSize)));
        }
        return parts;
    }
}

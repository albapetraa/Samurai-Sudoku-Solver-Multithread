/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samuraiSudoku;

import static samuraiSudoku.besThread.deneme;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Emre
 */
public class sThread1 extends Thread {

    private int[][] sudokuTahtasi;
    public static final int bosluk = 0;
    public static final int boyut = 9;
    int count = 0;
    public boolean isSolved = false;
    public int boardIndexRow = 0;
    public int boardIndexCol = 0;
    ArrayList<Integer> randomSayilar;
    public int deger = 0;
    public LinkedList<Islem> cozum;
    public LinkedList<Islem> islemAdimlari;

    public sThread1(int[][] sudokuTahtasi, int row, int col) {
        this.boardIndexRow = row;
        this.boardIndexCol = col;
        this.sudokuTahtasi = sudokuTahtasi;
        cozum = new LinkedList<Islem>();
        islemAdimlari = new LinkedList<Islem>();
        this.count = solveCounter(0, 0, sudokuTahtasi, count);
    }

    @Override
    public void run() {
        randomSayilar = randomSayilar();

        count = solveCounter(0, 0, sudokuTahtasi, count);
        synchronized (this) {
            try {
                System.out.println("Çözmeye başlandı. " + this.getName() + " count: " + count);
                if (count == 1) {
                    if (newSolveMethod()) {
                        System.out.println("DEGER: " + deger);
                        isSolved = true;
                        System.out.println("ÇÖZÜLDÜ " + this.getName());
                        this.stop();
                    } else {
                        System.out.println("1.ADIMDA ÇÖZÜLEMEDİ");
                    }
                }
                deger = 0;
                cozum = new LinkedList<Islem>();
                islemAdimlari = new LinkedList<Islem>();
                randomSayilar = randomSayilar();

                this.wait();
                int newCount = solveCounter(0, 0, sudokuTahtasi, 0);
                count = newCount;
                System.out.println("1.Wait durumundan cıktı " + this.getName() + " count: " + count);
                System.out.println("----------------------");

                if (newSolveMethod()) {
                    System.out.println("DEGER: " + deger);
                    isSolved = true;
                    System.out.println("ÇÖZÜLDÜ " + this.getName());
                    this.stop();
                } else {
                    System.out.println("2.Adımda çözülemedi. " + this.getName());
                    deger = 0;
                    cozum = new LinkedList<Islem>();
                    islemAdimlari = new LinkedList<Islem>();
                    randomSayilar = randomSayilar();
                    this.wait();
                    System.out.println("2.Wait durumundan çıktı" + this.getName() + " count: " + count);
                    if (newSolveMethod()) {
                        System.out.println("Tekrar denendi ve çözüm bulundu. " + this.getName());
                        isSolved = true;
                        this.stop();
                    } else {
                        System.out.println("Çözüm bulmada başarısız olundu." + this.getName());
                        this.stop();
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public int[][] getSudokuTahtasi() {
        return sudokuTahtasi;
    }

    public void setSudokuTahtasi(int[][] sudokuTahtasi) {
        this.sudokuTahtasi = sudokuTahtasi;
    }

    public boolean kosedeMi(int row, int col) {
        row += boardIndexRow;
        col += boardIndexCol;
        int[][] kose = {{6, 6}, {6, 7}, {6, 8}, {6, 12}, {6, 13}, {6, 14}, {7, 6}, {7, 7}, {7, 8}, {7, 12}, {7, 13}, {7, 14}, {8, 6}, {8, 7}, {8, 8}, {8, 12}, {8, 13}, {8, 14},
        {12, 6}, {12, 7}, {12, 8}, {12, 12}, {12, 13}, {12, 14}, {13, 6}, {13, 7}, {13, 8}, {13, 12}, {13, 13}, {13, 14}, {14, 6}, {14, 7}, {14, 8}, {14, 12}, {14, 13}, {14, 14}};

        for (int i = 0; i < kose.length; i++) {
            for (int j = 0; j < kose[i].length - 1; j++) {
                int kontrolRow = kose[i][j];
                int kontrolCol = kose[i][j + 1];
                if (row == kontrolRow && col == kontrolCol) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dogrulama2(int row, int col, int number) {

        for (int i = 0; i < boyut; i++) {
            if (sudokuTahtasi[row][i] == number) {
                return false;
            }
        }
        for (int i = 0; i < boyut; i++) {
            if (sudokuTahtasi[i][col] == number) {
                return false;
            }
        }
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (sudokuTahtasi[i][j] == number) {
                    return false;
                }
            }
        }
        if (kosedeMi(row, col)) {
            if (!koyulabilirMi(row, col, number)) {
                return false;
            }

        }
        return true;
    }

    public boolean koyulabilirMi(int row2, int col2, int number) {
        int q = 14;
        int row = row2 + boardIndexRow;
        int col = col2 + boardIndexCol;
        if ((row >= 6 && row <= 8) && (col >= 6 && col <= 8)) {

            for (int i = 6; i <= q; i++) {
                if (deneme[i][col] == number) {
                    return false;
                } else if (deneme[row][i] == number) {
                    return false;
                }

            }
        } else if ((row >= 6 && row <= 8) && (col >= 12 && col <= 14)) {
            for (int i = 6; i <= q; i++) {
                if (deneme[i][col] == number) {
                    return false;
                } else if (deneme[row][i] == number) {
                    return false;
                }

            }
        } else if ((row >= 12 && row <= 14) && (col >= 6 && col <= 8)) {
            for (int i = 6; i <= q; i++) {
                if (deneme[i][col] == number) {
                    return false;
                } else if (deneme[row][i] == number) {
                    return false;
                }

            }
        } else if ((row >= 12 && row <= 14) && (col >= 12 && col <= 14)) {
            for (int i = 6; i <= q; i++) {
                if (deneme[i][col] == number) {
                    return false;
                } else if (deneme[row][i] == number) {
                    return false;
                }

            }
        }

        return true;
    }

    public boolean newSolveMethod() {
        for (int row = 0; row < boyut; row++) {
            for (int col = 0; col < boyut; col++) {
                if (sudokuTahtasi[row][col] == bosluk) {
                    for (int number = 0; number <= boyut - 1; number++) {
                        int number2 = randomSayilar.get(number);
                        if (dogrulama2(row, col, number2)) {
                            sudokuTahtasi[row][col] = number2;
                            cozum.addLast(new Islem(row, col, number2, boardIndexRow, boardIndexCol));
                            islemAdimlari.addLast(new Islem(row, col, number2, boardIndexRow, boardIndexCol));
                            if (newSolveMethod()) {
                                deger++;
                                return true;
                            } else {
                                cozum.removeLast();
                                sudokuTahtasi[row][col] = bosluk;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public ArrayList<Integer> randomSayilar() {
        ArrayList<Integer> x = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {

            x.add(i);
        }
        Collections.shuffle(x);
        return x;

    }

    public boolean solve() {
        for (int row = 0; row < boyut; row++) {
            for (int col = 0; col < boyut; col++) {
                if (sudokuTahtasi[row][col] == bosluk) {
                    for (int number = 0; number <= boyut - 1; number++) {
                        int number2 = randomSayilar.get(number);
                        if (dogrulama(row, col, number2)) {
                            sudokuTahtasi[row][col] = number2;
                            cozum.addLast(new Islem(row, col, number2, boardIndexRow, boardIndexCol));
                            deger++;
                            if (solve()) {
                                return true;
                            } else {
                                cozum.removeLast();
                                sudokuTahtasi[row][col] = bosluk;

                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    int solveCounter(int i, int j, int[][] cells, int count) {
        if (i == 9) {
            i = 0;
            if (++j == 9) {
                return (int) (1 + count);
            }
        }
        if (cells[i][j] != 0) {
            return solveCounter(i + 1, j, cells, count);
        }

        for (int val = 1; val <= 9 && count < 100; ++val) {
            if (dogrulama(i, j, val)) {
                cells[i][j] = val;
                count = solveCounter(i + 1, j, cells, count);
            }
        }
        cells[i][j] = 0;
        return count;
    }

    private boolean dogrulama(int row, int col, int number) {
        for (int i = 0; i < boyut; i++) {
            if (sudokuTahtasi[row][i] == number) {
                return false;
            }
        }
        for (int i = 0; i < boyut; i++) {
            if (sudokuTahtasi[i][col] == number) {
                return false;
            }
        }
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (sudokuTahtasi[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

}

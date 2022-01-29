/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samuraiSudoku;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Emre
 */
public class besThread {

    BufferedWriter bw = null;
    static int cozulenSudokuSayisi = 0;
    static int board1IndexRow = 0;
    static int board1IndexCol = 0;
    static int board2IndexRow = 0;
    static int board2IndexCol = 12;
    static int board3IndexRow = 12;
    static int board3IndexCol = 0;
    static int board4IndexRow = 12;
    static int board4IndexCol = 12;
    static int board5IndexRow = 6;
    static int board5IndexCol = 6;
    static int[][] board1 = new int[9][9];
    static int[][] board2 = new int[9][9];
    static int[][] board3 = new int[9][9];
    static int[][] board4 = new int[9][9];
    static int[][] board5 = new int[9][9];
    static ArrayList<Integer> countListesi = new ArrayList<Integer>();
    static int[][] deneme;
    int[][] deneme2;
    public boolean flag = false;
    double seconds = 0;
    public ArrayList<Islem> tumCozumler = new ArrayList<Islem>();
    public int dToplam = 0;

    public besThread() throws InterruptedException, FileNotFoundException {
        SudokuyuOku sOku = new SudokuyuOku();
        SudokuyuOku sOku2 = new SudokuyuOku();

        sOku.calistir();
        sOku2.calistir();

        deneme = sOku.getBoard();
        deneme2 = sOku2.getBoard();
    }

    public int[][] getDeneme() {
        return deneme;
    }

    public int[][] getDeneme2() {
        return deneme2;
    }

    public void setDeneme(int[][] deneme) {
        besThread.deneme = deneme;
    }

    public void man() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ilkSudokulariYukle();
        sThread1 deneme1 = new sThread1(board1, board1IndexRow, board1IndexCol);
        sThread1 deneme2 = new sThread1(board2, board2IndexRow, board2IndexCol);
        sThread1 deneme3 = new sThread1(board3, board3IndexRow, board3IndexCol);
        sThread1 deneme4 = new sThread1(board4, board4IndexRow, board4IndexCol);
        sThread1 deneme5 = new sThread1(board5, board5IndexRow, board5IndexRow);
        ArrayList<sThread1> threadListesi = new ArrayList<sThread1>();
        threadListesi.add(deneme1);
        threadListesi.add(deneme2);
        threadListesi.add(deneme3);
        threadListesi.add(deneme4);
        threadListesi.add(deneme5);
        Collections.sort(
                threadListesi,
                (player1, player2) -> player1.count
                - player2.count);
        for (int i = 0; i < threadListesi.size(); i++) {
            threadListesi.get(i).start();
        }
        Collections.sort(
                threadListesi,
                (player1, player2) -> player1.count
                - player2.count);
        Thread.sleep(100);

        ikinciSudokulariYukle();

        for (int i = 0; i < threadListesi.size(); i++) {
            siraylaUyandirma(threadListesi.get(i));
            Thread.sleep(100);
            ikinciSudokulariYukle();
            if (threadListesi.get(i) == deneme5) {
                board5 = deneme5.getSudokuTahtasi();
                for (int q = 0; q < 9; q++) {
                    for (int j = 0; j < 9; j++) {
                        deneme[q + 6][j + 6] = board5[q][j];
                    }
                }
                break;
            }
            ortadakiGuncelle();
        }

        for (int i = 0; i < threadListesi.size(); i++) {
            siraylaUyandirma(threadListesi.get(i));
            Thread.sleep(100);
            ikinciSudokulariYukle();
            if (threadListesi.get(i) == deneme5) {
                board5 = deneme5.getSudokuTahtasi();
                for (int q = 0; q < 9; q++) {
                    for (int j = 0; j < 9; j++) {
                        deneme[q + 6][j + 6] = board5[q][j];
                    }
                }
                break;
            }
            ortadakiGuncelle();
        }
        Thread.sleep(50);
        int q = 0;
        for (int i = 0; i < threadListesi.size(); i++) {
            if (threadListesi.get(i).isSolved) {
                q++;
            }
        }
        if (q != 5) {
            flag = false;
            try {
                deneme1.interrupt();
                deneme2.interrupt();
                deneme3.interrupt();
                deneme4.interrupt();
                deneme5.interrupt();

            } catch (Exception ex) {
                System.out.println("Exception has "
                        + "been caught" + ex);
            }
            long endTime = System.currentTimeMillis();
            long estimatedTime = endTime - startTime;
            seconds = (double) estimatedTime / 1000;
        } else {
            try {
                //
                dToplam = 0;
                for (int i = 0; i < threadListesi.size(); i++) {
                    dToplam += threadListesi.get(i).deger;

                    for (int j = 0; j < threadListesi.get(i).cozum.size(); j++) {
                        Islem x = threadListesi.get(i).cozum.get(j);
                        tumCozumler.add(x);
                    }
                }

                Collections.shuffle(tumCozumler);
                deneme1.interrupt();
                deneme2.interrupt();
                deneme3.interrupt();
                deneme4.interrupt();
                deneme5.interrupt();

            } catch (Exception ex) {
                System.out.println("Exception has "
                        + "been caught" + ex);
            }
            flag = true;
            System.out.println("++ " + threadListesi.size());

            samuraiSudokuYazdir(deneme);

            long endTime = System.currentTimeMillis();
            long estimatedTime = endTime - startTime;
            seconds = (double) estimatedTime / 1000;
            System.out.println("5 Thread'in çözümü bulmak için aldığı zaman : " + seconds);
            ilkSudokulariYukle();

            if (sudokuKontrol(board1)) {
                System.out.println("1.Sudoku Doğru");
            }
            if (sudokuKontrol(board2)) {
                System.out.println("2.Sudoku Doğru");
            }
            if (sudokuKontrol(board3)) {
                System.out.println("3.Sudoku Doğru");
            }
            if (sudokuKontrol(board4)) {
                System.out.println("4.Sudoku doğru");
            }
            if (sudokuKontrol(board5)) {
                System.out.println("5.Sudoku doğru");
            }
            System.out.println("5 Thread islem adimlari yaziliyor.");
            dosyayaYaz(threadListesi);
            System.out.println("5 Thread İslem adimlari Yazildi.");
        }
    }

    public ArrayList<Islem> getTumCozumler() {
        return tumCozumler;
    }

    public void setTumCozumler(ArrayList<Islem> tumCozumler) {
        this.tumCozumler = tumCozumler;
    }

    static int N = 9;

    static boolean isinRange(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] <= 0
                        || board[i][j] > 9) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean sudokuKontrol(int board[][]) {
        if (isinRange(board) == false) {
            return false;
        }
        boolean[] unique = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(unique, false);
            for (int j = 0; j < N; j++) {
                int Z = board[i][j];
                if (unique[Z]) {
                    return false;
                }
                unique[Z] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(unique, false);
            for (int j = 0; j < N; j++) {
                int Z = board[j][i];
                if (unique[Z]) {
                    return false;
                }
                unique[Z] = true;
            }
        }

        for (int i = 0; i < N - 2; i += 3) {
            for (int j = 0; j < N - 2; j += 3) {
                Arrays.fill(unique, false);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int X = i + k;
                        int Y = j + l;
                        int Z = board[X][Y];
                        if (unique[Z]) {
                            return false;
                        }
                        unique[Z] = true;
                    }
                }
            }
        }
        return true;
    }

    public static boolean kontrol(ArrayList<sThread1> tL) {
        int q = 0;
        for (int i = 0; i < tL.size(); i++) {
            if (!tL.get(i).isSolved) {
                return false;
            }
        }
        return true;
    }

    public static void ortadakiGuncelle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board5[i][j] = deneme[i + 6][j + 6];
            }
        }
    }

    public static void siraylaUyandirma(sThread1 t) {
        synchronized (t) {
            t.notify();
        }
    }

    public static int indexOfSmallest(ArrayList<Integer> array) {

        if (array.size() == 0) {
            return -1;
        }

        int index = 0;
        int min = array.get(index);

        for (int i = 1; i < array.size(); i++) {
            if (array.get(i) <= min) {
                min = array.get(i);
                index = i;
            }
        }
        return index;
    }

    public static void objeUyandirma(Thread t) {
        synchronized (t) {
            t.notify();
        }

    }

    public static void ikinciSudokulariYukle() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme[i][j] = board1[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme[i][j + 12] = board2[i][j];

            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme[i + 12][j] = board3[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme[i + 12][j + 12] = board4[i][j];
            }
        }
    }

    public static void ilkSudokulariYukle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1[i][j] = deneme[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board2[i][j] = deneme[i][j + 12];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board3[i][j] = deneme[i + 12][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board4[i][j] = deneme[i + 12][j + 12];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board5[i][j] = deneme[i + 6][j + 6];
            }
        }
    }

    public static void samuraiSudokuYazdir(int[][] deneme) {
        for (int r = 0; r < deneme.length; r++) {
            for (int c = 0; c < deneme.length; c++) {
                if (deneme[r][c] > 0) {
                    System.out.print(deneme[r][c] + " ");
                } else {
                    System.out.print(". ");
                }
            }

            System.out.println();
        }
    }

    public void dosyayaYaz(ArrayList<sThread1> thListesi) {
        List<String> records = new ArrayList<String>();

        try (FileWriter writer = new FileWriter("5ThreadIslemAdimlari.txt", true);) {
            for (int i = 0; i < thListesi.size(); i++) {
                for (int j = 0; j < thListesi.get(i).islemAdimlari.size(); j++) {
                    int satir = thListesi.get(i).islemAdimlari.get(j).sat;
                    int sutun = thListesi.get(i).islemAdimlari.get(j).sut;
                    int sayi = thListesi.get(i).islemAdimlari.get(j).say;
                    writer.write(thListesi.get(i).getName() + " Satır: " + satir + " Sutun: " + sutun + " Sayi: " + sayi + "\n");
                }

            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
}

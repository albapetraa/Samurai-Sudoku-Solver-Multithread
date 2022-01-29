/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package samuraiSudoku;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emre
 */
public class onThread {

    /**
     * @param args the command line arguments
     */
    BufferedWriter bw = null;

    static int[][] deneme3;
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
    public double seconds = 0;
    public static boolean flag = false;
    public int dToplam2 = 0;
    public ArrayList<Islem> tumCozumler = new ArrayList<Islem>();

    public onThread() {
    }

    public void man() throws InterruptedException {
        try {
            long startTime = System.currentTimeMillis();

            SudokuyuOku sOku = new SudokuyuOku();
            sOku.calistir();
            deneme3 = sOku.getBoard();
            ilkSudokulariYukle();
            sThread2 deneme12 = new sThread2(board1, board1IndexRow, board1IndexCol);
            sThread2 deneme22 = new sThread2(board2, board2IndexRow, board2IndexCol);
            sThread2 deneme32 = new sThread2(board3, board3IndexRow, board3IndexCol);
            sThread2 deneme42 = new sThread2(board4, board4IndexRow, board4IndexCol);
            sThread2 deneme52 = new sThread2(board5, board5IndexRow, board5IndexRow);
            ArrayList<sThread2> threadListesi = new ArrayList<sThread2>();
            threadListesi.add(deneme12);
            threadListesi.add(deneme22);
            threadListesi.add(deneme32);
            threadListesi.add(deneme42);
            threadListesi.add(deneme52);
            //
            sThread11 deneme11 = new sThread11(board1, board1IndexRow, board1IndexCol);
            sThread11 deneme21 = new sThread11(board2, board2IndexRow, board2IndexCol);
            sThread11 deneme31 = new sThread11(board3, board3IndexRow, board3IndexCol);
            sThread11 deneme41 = new sThread11(board4, board4IndexRow, board4IndexCol);
            sThread11 deneme51 = new sThread11(board5, board5IndexRow, board5IndexRow);
            ArrayList<sThread11> threadListesi2 = new ArrayList<sThread11>();
            threadListesi2.add(deneme11);
            threadListesi2.add(deneme21);
            threadListesi2.add(deneme31);
            threadListesi2.add(deneme41);
            threadListesi2.add(deneme51);

            Collections.sort(
                    threadListesi2,
                    (player1, player2) -> player1.count
                    - player2.count);

            Collections.sort(
                    threadListesi,
                    (player1, player2) -> player1.count
                    - player2.count);
            for (int i = 0; i < threadListesi.size(); i++) {
                threadListesi.get(i).start();
                threadListesi2.get(i).start();
            }

            Collections.sort(
                    threadListesi2,
                    (player1, player2) -> player1.count
                    - player2.count);

            Collections.sort(
                    threadListesi,
                    (player1, player2) -> player1.count
                    - player2.count);

            Thread.sleep(100);

            ikinciSudokulariYukle();

            for (int i = 0; i < threadListesi.size(); i++) {
                siraylaUyandirma(threadListesi.get(i));
                siraylaUyandirma2(threadListesi2.get(i));

                Thread.sleep(100);
                ikinciSudokulariYukle();
                if (threadListesi.get(i) == deneme52 || threadListesi2.get(i) == deneme51) {
                    for (int q = 0; q < 9; q++) {
                        for (int j = 0; j < 9; j++) {
                            deneme3[q + 6][j + 6] = board5[q][j];
                        }
                    }
                    break;
                }
                ortadakiGuncelle();
            }

            for (int i = 0; i < threadListesi.size(); i++) {
                siraylaUyandirma(threadListesi.get(i));
                siraylaUyandirma2(threadListesi2.get(i));
                Thread.sleep(100);
                ikinciSudokulariYukle();
                if (threadListesi.get(i) == deneme52 || threadListesi2.get(i) == deneme51) {
                    for (int q = 0; q < 9; q++) {
                        for (int j = 0; j < 9; j++) {
                            deneme3[q + 6][j + 6] = board5[q][j];
                        }
                    }
                    break;
                }
                ortadakiGuncelle();
            }
            Thread.sleep(50);

            int q = 0;
            int q2 = 0;
            dToplam2 = 0;
            for (int i = 0; i < threadListesi.size(); i++) {
                dToplam2 += threadListesi.get(i).deger;
                dToplam2 += threadListesi2.get(i).deger;
                for (int j = 0; j < threadListesi.get(i).cozum.size(); j++) {
                    Islem x = threadListesi.get(i).cozum.get(j);
                    tumCozumler.add(x);
                }
            }
            for (int i = 0; i < threadListesi2.size(); i++) {
                for (int j = 0; j < threadListesi2.get(i).cozum.size(); j++) {
                    Islem x = threadListesi2.get(i).cozum.get(j);
                    tumCozumler.add(x);
                }
            }
            Collections.shuffle(tumCozumler);

            for (int i = 0; i < threadListesi.size(); i++) {
                if (threadListesi.get(i).isSolved) {
                    q++;
                }
                if (threadListesi2.get(i).isSolved) {
                    q2++;
                }
            }
            if (q == 5 || q2 == 5) {
                try {
                    deneme12.interrupt();
                    deneme22.interrupt();
                    deneme32.interrupt();
                    deneme42.interrupt();
                    deneme52.interrupt();
                    //
                    deneme11.interrupt();
                    deneme21.interrupt();
                    deneme31.interrupt();
                    deneme41.interrupt();
                    deneme51.interrupt();

                } catch (Exception ex) {
                    System.out.println("Exception has "
                            + "been caught" + ex);
                }
                flag = true;
                samuraiSudokuYazdir(deneme3);

                long endTime = System.currentTimeMillis();
                long estimatedTime = endTime - startTime;
                seconds = (double) estimatedTime / 1000;
                System.out.println("10 Thread'in çözümü bulmak için aldığı zaman : " + seconds);
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
                System.out.println("10 Thread-1 islem adimlari yaziliyor.");
                dosyayaYaz2(threadListesi2);
                System.out.println("10 Thread-1 İslem adimlari Yazildi.");
                System.out.println("10 Thread-2 islem adimlari yaziliyor.");
                dosyayaYaz(threadListesi);
                System.out.println("10 Thread-2 İslem adimlari Yazildi.");
            } else {
                flag = false;
                try {
                    deneme12.interrupt();
                    deneme22.interrupt();
                    deneme32.interrupt();
                    deneme42.interrupt();
                    deneme52.interrupt();
                    //
                    deneme11.interrupt();
                    deneme21.interrupt();
                    deneme31.interrupt();
                    deneme41.interrupt();
                    deneme51.interrupt();

                } catch (Exception ex) {
                    System.out.println("Exception has "
                            + "been caught" + ex);
                }
                long endTime = System.currentTimeMillis();
                long estimatedTime = endTime - startTime;
                seconds = (double) estimatedTime / 1000;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(onThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ortadakiGuncelle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board5[i][j] = deneme3[i + 6][j + 6];
            }
        }
    }

    public ArrayList<Islem> getTumCozumler() {
        return tumCozumler;
    }

    public void setTumCozumler(ArrayList<Islem> tumCozumler) {
        this.tumCozumler = tumCozumler;
    }

    public void siraylaUyandirma2(sThread11 t) {
        synchronized (t) {
            t.notify();
        }
    }

    public void siraylaUyandirma(sThread2 t) {
        synchronized (t) {
            t.notify();
        }
    }
    static int N = 9;

    boolean isinRange(int[][] board) {
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

    boolean sudokuKontrol(int board[][]) {
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

    public void ikinciSudokulariYukle() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme3[i][j] = board1[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme3[i][j + 12] = board2[i][j];

            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme3[i + 12][j] = board3[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                deneme3[i + 12][j + 12] = board4[i][j];
            }
        }
    }

    public void samuraiSudokuYazdir(int[][] deneme3) {
        for (int r = 0; r < deneme3.length; r++) {
            for (int c = 0; c < deneme3.length; c++) {
                if (deneme3[r][c] > 0) {
                    System.out.print(deneme3[r][c] + " ");
                } else {
                    System.out.print(". ");
                }
            }

            System.out.println();
        }
    }

    public void ilkSudokulariYukle() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1[i][j] = deneme3[i][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board2[i][j] = deneme3[i][j + 12];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board3[i][j] = deneme3[i + 12][j];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board4[i][j] = deneme3[i + 12][j + 12];
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board5[i][j] = deneme3[i + 6][j + 6];
            }
        }
    }

    public void dosyayaYaz(ArrayList<sThread2> thListesi) {
        try (FileWriter writer = new FileWriter("10ThreadIslemAdimlari.txt", true);) {
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

    public void dosyayaYaz2(ArrayList<sThread11> thListesi) {
        try (FileWriter writer = new FileWriter("10ThreadIslemAdimlari.txt", true);) {
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samuraiSudoku;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import javax.swing.JPanel;

/**
 *
 * @author Emre
 */
public class View extends JPanel {

    public int[][] maze;
    private BufferedImage image1;
    private BufferedImage image2;
    private BufferedImage image3;
    private BufferedImage image4;
    private BufferedImage image5;
    private BufferedImage image6;
    private BufferedImage image7;
    private BufferedImage image8;
    private BufferedImage image9;
    int[][] yasak = {{0, 9}, {0, 10}, {0, 11}, {1, 9}, {1, 10}, {1, 11}, {2, 9}, {2, 10}, {2, 11}, {3, 9}, {3, 10}, {3, 11}, {4, 9}, {4, 10}, {4, 11}, {5, 9}, {5, 10}, {5, 11},
    {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}, {9, 5}, {10, 0}, {10, 1}, {10, 2}, {10, 3}, {10, 4}, {10, 5}, {11, 0}, {11, 1}, {11, 2}, {11, 3}, {11, 4}, {11, 5},
    {9, 15}, {9, 16}, {9, 17}, {9, 18}, {9, 19}, {9, 20}, {10, 15}, {10, 16}, {10, 17}, {10, 18}, {10, 19}, {10, 20}, {11, 15}, {11, 16}, {11, 17}, {11, 18}, {11, 19}, {11, 20},
    {15, 9}, {15, 10}, {15, 11}, {16, 9}, {16, 10}, {16, 11}, {17, 9}, {17, 10}, {17, 11}, {18, 9}, {18, 10}, {18, 11}, {19, 9}, {19, 10}, {19, 11}, {20, 9}, {20, 10}, {20, 11}};

    public View(int[][] m) {
        this.maze = m;
        setSize(800, 800);

        try {

            image1 = ImageIO.read(new FileImageInputStream(new File("bir.png")));
            image2 = ImageIO.read(new FileImageInputStream(new File("iki.png")));
            image3 = ImageIO.read(new FileImageInputStream(new File("uc.png")));
            image4 = ImageIO.read(new FileImageInputStream(new File("dort.png")));
            image5 = ImageIO.read(new FileImageInputStream(new File("bes.png")));
            image6 = ImageIO.read(new FileImageInputStream(new File("alti.png")));
            image7 = ImageIO.read(new FileImageInputStream(new File("yedi.png")));
            image8 = ImageIO.read(new FileImageInputStream(new File("sekiz.png")));
            image9 = ImageIO.read(new FileImageInputStream(new File("dokuz.png")));

        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paint(Graphics g) {
//        g.translate(50, 50);
        super.paint(g);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                int q = 0;
                Color color;
                switch (maze[row][col]) {
                    case 1:
                        color = Color.WHITE;
                        g.drawImage(image1, 30 * col, 30 * row, this);
                        break;
                    case 2:
                        color = Color.WHITE;
                        g.drawImage(image2, 30 * col, 30 * row, this);
                        break;
                    case 3:
                        color = Color.WHITE;
                        g.drawImage(image3, 30 * col, 30 * row, this);
                        break;
                    case 4:
                        color = Color.WHITE;
                        g.drawImage(image4, 30 * col, 30 * row, this);
                        break;
                    case 5:
                        color = Color.WHITE;
                        g.drawImage(image5, 30 * col, 30 * row, this);
                        break;
                    case 6:
                        color = Color.WHITE;
                        g.drawImage(image6, 30 * col, 30 * row, this);
                        break;
                    case 7:
                        color = Color.WHITE;
                        g.drawImage(image8, 30 * col, 30 * row, this);
                        break;
                    case 8:
                        color = Color.WHITE;
                        g.drawImage(image9, 30 * col, 30 * row, this);
                        break;
                    case 9:
                        color = Color.WHITE;
                        g.drawImage(image9, 30 * col, 30 * row, this);
                        break;
                    default:
                        color = Color.GRAY;
                        break;
                }
                if (yasakMi(row, col)) {
                    color = Color.WHITE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);

                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
                if (maze[row][col] == 1) {
                    g.drawImage(image1, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 2) {
                    g.drawImage(image2, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 3) {
                    g.drawImage(image3, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 4) {
                    g.drawImage(image4, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 5) {
                    g.drawImage(image5, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 6) {
                    g.drawImage(image6, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 7) {
                    g.drawImage(image7, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 8) {
                    g.drawImage(image8, 30 * col, 30 * row, this);
                } else if (maze[row][col] == 9) {
                    g.drawImage(image9, 30 * col, 30 * row, this);
                }
//                g.drawString(String.valueOf(maze[row][col]), 30 * col + 25, 30 * row + 25);

            }
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    public void cagir() {
        repaint();
    }

    public boolean yasakMi(int row, int col) {
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
}

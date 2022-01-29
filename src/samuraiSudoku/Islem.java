/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package samuraiSudoku;

/**
 *
 * @author Emre
 */
public class Islem {

    public int sat;
    public int sut;
    public int say;

    public Islem() {

    }

    public Islem(int sat, int sut, int say, int bRow, int bCol) {
        this.sat = sat + bRow;
        this.sut = sut + bCol;
        this.say = say;
    }

}

package codedisplay;

import processing.core.PGraphics;

/**
 * Class ColoredChar pour projet CodeDisplay
 * @author Xavier
 */
public class ColoredChar {
  /** Caractere a afficher */
  private char c;
  /** Couleur du caractere */
  private int col;

  /**
   * Constructeur de caractere colore
   * @param c Caractere
   * @param col Couleur
   */
  public ColoredChar(char c, int col) {
    this.c = c;
    this.col = col;
  }

  /**
   * Renvoie le caractere c
   * @return c
   */
  public char getC() {
    return c;
  }

  /** Affiche le caractere dans la console */
  public void print() {
    System.out.print(c);
  }

  /**
   * Affiche le caractere a la bonne couleur dans le PGraphics 
   * @param bg PGraphics dans lequel on affiche le caractere
   * @param x Position x
   * @param y Position y
   */
  public void show(PGraphics bg, float x, float y) {
    bg.fill(col);
    bg.text("" + c, x, y);
  }
}

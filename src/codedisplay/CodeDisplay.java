package codedisplay;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PFont;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Classe CodeDisplay pour projet CodeDisplay. Affiche un bout de code contenu
 * dans le fichier code.txt. L'affichage est fait de maniere stylisee : les mots
 * clefs sont colores selon le code couleur Processing PDE. Le code est mis en
 * avant sur un support gris, lui-meme reposant sur un fond colore degrade.
 * @author Xavier
 */
public class CodeDisplay extends PApplet {
  /** Background */
  PGraphics bg;
  /** Police mono */
  PFont cmuMono;
  /** Taille de la police */
  int fontSize;
  /** Taille de unitaire d'un caractere */
  float charSpace;
  /** Lignes de codes lues dans le fichier */
  String[] code;
  /** Tableau de caracteres colores */
  ArrayList<ColoredChar> letters;
  /** Titre de code */
  final String title = "FlamePixels.java";
  /** Code a afficher */
  final String fileName = "code.txt";
  /** Scaling du texte */
  final float scaleText = 0.75f;
  /** Marge du support */
  final int padding = 150;
  /** Taille du bandeau */
  final int bandHeight = 80;
  /** Taille de la colonnes numero de lignes */
  final int marginWidth = 60;

  /** Point d'entree de l'application */
  public static void main(String[] args) {
    PApplet.main(new String[]{CodeDisplay.class.getName()});
  }

  /** Setup du PApplet */
  @Override
  public void settings() {
    size(960, 1013);
  }

  /** Setup de la fenetre. Cree le graphique qui affiche le code, gere la police */
  @Override
  public void setup() {
    // Code a afficher
    code = loadLines(fileName);
    
    // Font
    fontSize = (height - bandHeight - 2 * padding) / (code.length + 1);
    charSpace = fontSize * 0.5f * scaleText;
    cmuMono = createFont("cmunbtl.ttf", fontSize, true);
    
    // La ligne la plus longue va definir 
    // le scaling horizontal de l'image
    String longest = code[0];
    for (int i = 1; i < code.length; i++) {
      if (code[i].length() > longest.length()) {
        longest = code[i];
      }
    }
    letters = new ArrayList<>();
    
    // Scaling horizontal
    float totalWidth = 2 * padding + 2 * marginWidth + longest.length() * charSpace;
    if (width != ceil(totalWidth)) {
      surface.setSize(ceil(totalWidth), height);
      System.out.println("Width adjusted (" + ceil(totalWidth) + " px) ");
    }
    surface.setLocation(-2, 0);

    // Affichage
    //bg = backgroundGradient(90, 56, 116, 38, 44, 86);
    bg = backgroundGradient(38, 44, 86, 43, 92, 150);

    try {
      bg.textFont(cmuMono);
    } catch (RuntimeException re) {
      System.err.println("Erreur lors de la creation de la police");
      System.err.println("La police doit se trouver dans le dossier /data/");
    }

    // Affiche le titre, les numeros de lignes et le code
    bg.beginDraw();
    displayWindow();
    displayTitle();
    displayLineNumbers();
    displayCode();
    bg.endDraw();
  }

  /**
   * Gere uniquement le chargement du graphique, et l'affichage du bouton
   * fermer, qui change de couleur au survol
   */
  @Override
  public void draw() {
    image(bg, 0, 0);
    int x = width - padding - bandHeight / 2;
    int y = padding + bandHeight / 2;
    float rectW = 30;
    float rectH = 6;

    // Si la souris survole le bouton fermer, le bouton
    // se colore en rose. Sinon s'affiche en gris clair
    if (mouseX > x - rectW && mouseX < x + rectW 
      && mouseY > y - rectW && mouseY < y + rectW) {
      fill(255, 150, 255);
    } else {
      fill(200);
    }

    // Vrai bouton fermer
    noStroke();
    rectMode(CENTER);
    pushMatrix();
    translate(width - padding - bandHeight / 2, padding + bandHeight / 2);
    rotate(QUARTER_PI);
    rect(0, 0, rectW * 1.25f, rectH);
    rotate(HALF_PI);
    rect(0, 0, rectW * 1.25f, rectH);
    popMatrix();
  }

  /**
   * Affiche la fenetre. Cree le fond, cree le support pour le texte. Affiche 
   * des faux boutons (reduire, agrandir, fermer)
   */
  public void displayWindow() {
    // Contexte graphique
    bg.noStroke();
    
    // En-tete pour titre et boutons
    bg.fill(39, 39, 34, 225);
    bg.rect(padding, padding,
      width - 2 * padding, bandHeight,
      (height - 2 * padding) * 0.03f, (height - 2 * padding) * 0.03f, 0, 0);
    
    // Colonnes des numeros de lignes
    bg.fill(39, 39, 34, 191);
    bg.rect(padding, padding + bandHeight,
      marginWidth, height - bandHeight - 2 * padding,
      0, 0, 0, (height - 2 * padding) * 0.03f);
    
    // Zone du code
    bg.fill(39, 39, 34, 191);
    bg.rect(padding + marginWidth, padding + bandHeight,
      width - 2 * padding - marginWidth, height - bandHeight - 2 * padding,
      0, 0, (height - 2 * padding) * 0.03f, 0);

    // Ajouts graphiques (reduire, agrandir, fermer)
    bg.rectMode(CENTER);
    bg.pushMatrix();
    float rectW = 30;
    float rectH = 6;
    bg.fill(200);
    bg.translate(width - padding - bandHeight / 2, padding + bandHeight / 2);

    // Faux bouton fermer
    bg.pushMatrix();
    bg.rotate(QUARTER_PI);
    bg.rect(0, 0, rectW * 1.25f, rectH);
    bg.rotate(HALF_PI);
    bg.rect(0, 0, rectW * 1.25f, rectH);
    bg.popMatrix();

    // Faux bouton agrandir
    bg.translate(-bandHeight, 0);
    int adj = 8;
    bg.rect(0, bandHeight * 0.25f - adj, rectW, rectH);
    bg.rect(0, -bandHeight * 0.25f + adj, rectW, rectH);
    bg.rect(bandHeight * 0.25f - adj, 0, rectH, rectW);
    bg.rect(-bandHeight * 0.25f + adj, 0, rectH, rectW);

    // Faux bouton reduire
    bg.translate(-bandHeight, 0);
    bg.rect(0, 0, rectW, rectH);
    bg.popMatrix();

//    bg.stroke(39, 39, 34, 225);
//    bg.line(padding + marginWidth, padding + bandHeight, padding + marginWidth, height - padding);
  }

  /** Affiche les numero de lignes */
  public void displayLineNumbers() {
    bg.pushMatrix();
    bg.fill(102);
    bg.textSize(fontSize * scaleText);
    bg.translate(padding + marginWidth * 0.5f - charSpace, padding + bandHeight + fontSize * 0.25f);
    for (int i = 0; i < code.length; i++) {
      bg.translate(0, fontSize);
      int lineNumber = i + 1;
      if (lineNumber < 10) {
        // Alignement a droite
        bg.text(" " + lineNumber, 0, 0);
      } else {
        bg.text("" + lineNumber, 0, 0);
      }
    }
    bg.popMatrix();
  }

  /** Affiche le titre dans le bandeau */
  public void displayTitle() {
    // Numero lignes
    bg.pushMatrix();
    bg.fill(200);
    bg.textSize(bandHeight * 0.3f);
    bg.translate(padding + bandHeight / 2, padding + bandHeight * 0.6f);
    bg.text(title, 0, 0);
    bg.popMatrix();
  }

  /** Affiche le code */
  public void displayCode() {
    loadLetters();
    int i = 0, j = 0;
    bg.pushMatrix();
    bg.translate(padding + marginWidth * 1.5f, padding + bandHeight + fontSize * 0.25f);
    bg.translate(0, fontSize);
    bg.textSize(fontSize * scaleText);

    for (ColoredChar c : letters) {
      if (c.getC() == '\n') {
        i = 0;
        j += fontSize;
      } else {
        i += charSpace;
        c.show(bg, i, j);
      }
    }
    bg.popMatrix();
  }

  /**
   * Decompose toutes les lignes du code. Dans chaque ligne, verifie la presence
   * de blocs javadoc, de commentaires, de chaines de caracteres et de
   * caracteres. Pour le reste, cherche les mots clefs pour trouver la bonne
   * couleur a associer. Les mots sont ensuite decomposes en ColoredChar et
   * charges dans la liste letters.
   */
  public void loadLetters() {
    // Texte
    for (int i = 0; i < code.length; i++) {
      String s = code[i];

      // Verification des blocs javadoc
      // A ne pas confondre avec les blocs commentaires
      if (s.contains("/**") && !s.contains("/**/")) {
        int start = s.indexOf("/**");
        int end;
        String wStar, wSlash;
        do {
          String[] w = loadWords(s);
          end = w.length;
          wStar = w[end - 2];
          wSlash = w[end - 1];

          // Si on ne detecte pas la fin d'un bloc javadoc dans
          // cette ligne, on affiche la ligne actuelle, et on passe
          // a la ligne suivante
          if (!wStar.equals("*") || !wSlash.equals("/")) {
            String comm = s.substring(start, s.length());
            colorCommentLineOfCode(comm);
            // Ligne suivante
            i++;
            s = code[i];
            start = 0;
          } //else end = s.length();
        } while (!wStar.equals("*") || !wSlash.equals("/"));
        
        // Fin du bloc detecte, on finit l'affichage
        String comm = s.substring(start);
        colorCommentLineOfCode(comm);
      } 

      // Verification des commentaires in-line
      else if (s.contains("//")) {
        int start = s.indexOf("//");
        int end = s.length();

        // Dicerne la partie code de la partie commentaire
        String line = s.substring(0, start);
        String comm = s.substring(start, end);
        
        // La partie code a colorer
        // Le commentaire est affiche en gris
        colorRegularLineOfCode(line);
        colorCommentLineOfCode(comm);
      } 

      // Si la ligne ne contient pas de commentaire
      // on cherche les mots clefs a colorer
      else {
        colorRegularLineOfCode(s);
      }
      letters.add(new ColoredChar('\n', color(0, 0)));
    }
  }

  /**
   * Colore une ligne de code qui n'est pas du commentaire. Si une chaine de
   * caractere est detectee, la colore selon la couleur correspondante. Sinon,
   * cherche les mots clefs
   * @param s Ligne de code a analyser
   */
  public void colorRegularLineOfCode(final String s) {
    String[] words = loadWords(s);
    for (int i = 0; i < words.length; i++) {
      String w = words[i];
      if (i < words.length - 1 && w.equals("/") && words[i + 1].equals("*")) {
        // Si on detecte un nouveau bloc commentaire
        String comm = w + words[i + 1];
        i++;
        
        // On cherche la fin du bloc commentaire (*/)
        // en stockant tous les mots au passage
        do {
          i++;
          w = words[i];
          comm = comm.concat(w);
        } while (!w.equals("*") || !words[i + 1].equals("/"));
        
        // On complete et on affiche le bloc
        i++;
        w = words[i];
        comm = comm.concat(w);
        colorCommentLineOfCode(comm);
      } 

      // Si on detecte une chaine de caractere (")
      else if (w.equals("\"")) {
        String line = w;
        
        // On cherche la fin de la chaine (")
        // en stockant tous les mots au passage
        do {
          i++;
          w = words[i];
          line = line.concat(w);
        } while (!w.equals("\""));
        
        // On affiche la chaine (")
        toColoredChar(line, color(125, 71, 147));
      } 

      // Si on detecte un caractere entre cotes
      else if (w.equals("\'")) {
        String line = w;
        
        // On cherche la fin du caractere (')
        do {
          i++;
          w = words[i];
          line = line.concat(w);
        } while (!w.equals("\'"));
        
        // On affiche le caractere
        toColoredChar(line, color(125, 71, 147));
      } 
      
      // Sinon on cherche les mots clefs
      else {
        toColoredChar(w, selectWordColor(w));
      }
    }
  }
  
  /**
   * Affiche la chaine de caractere en tant que commentaire
   * @param s Commentaire
   */
  public void colorCommentLineOfCode(final String s) {
    toColoredChar(s, color(102));
  }

  /**
   * Cherche si le mot fait partie d'une liste de mots clef. Selon la liste,
   * attribut une couleur d'affichage. Si le mot ne fait partie d'aucune liste,
   * affiche en blanc
   * @param w Mot a analyser
   * @return Couleur du mot
   */
  public int selectWordColor(final String w) {
    if (Keywords.isDatatype(w)) {
      return color(226, 102, 26);
    } else if (Keywords.isControl(w)) {
      return color(102, 153, 0);
    } else if (Keywords.isJava(w)) {
      return color(51, 153, 126);
    } else if (Keywords.isGlobal(w)) {
      return color(217, 74, 122);
    } else if (Keywords.isFunction(w)) {
      return color(0, 102, 153);
    } else if (Keywords.isConstant(w)) {
      return color(113, 138, 98);
    } else {
      return color(255);
    }
  }
  
  /**
   * Transforme une chaine de caracteres en caracteres colores, stockes dans une
   * liste
   * @param s Chaine a decomposer
   * @param col Couleur des caracteres dans cette chaine
   */
  public void toColoredChar(String s, final int col) {
    for (int i = 0; i < s.length(); i++) {
      letters.add(new ColoredChar(s.charAt(i), col));
    }
  }
  
  /**
   * Cherche les mots independants dans une chaine de caractere. Les stock dans
   * un tableau de String
   * @param lineOfCode Chaine de caractere a analyser
   * @return Tableau de mots
   */
  public static String[] loadWords(final String lineOfCode) {
    ArrayList<String> words = new ArrayList<>();
    int i = 0, start, end;
    char c;
    while (i < lineOfCode.length()) {
      c = lineOfCode.charAt(i);
      if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
        start = i;
        do {
          i++;
          c = lineOfCode.charAt(i);
        } while ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        end = i;
        words.add(lineOfCode.substring(start, end));
        i--;
      } else {
        words.add("" + c);
      }
      i++;
    }
    return words.toArray(new String[0]);
  }

  /**
   * Lit le fichier. Decompose chaque ligne en chaines de caracteres
   * independantes. Toutes les lignes sont stockees dans un tableau de String
   * @param fileName Nom du fichier a lire
   * @return Tableau de lignes du fichier
   */
  public static String[] loadLines(final String fileName) {
    ArrayList<String> lines = new ArrayList<>();
    Scanner scan = null;
    try {
      scan = new Scanner(new File("data\\" + fileName));
    } catch (FileNotFoundException fnfe) {
      System.err.println("Erreur lors de la recuperation du fichier");
      System.err.println("Le fichier doit se trouver dans le dossier /data");
      fnfe.printStackTrace(System.out);
    }

    if (scan != null) {
      while (scan.hasNextLine()) {
        lines.add(scan.nextLine());
      }
      scan.close();
    }
    return lines.toArray(new String[0]);
  }

  /**
   * Dessine un degrade vertical sur tout le canvas entre les deux couleurs
   * passees en parametres
   * @param r1 Valeur rouge de la premiere couleur
   * @param g1 Valeur verte de la premiere couleur
   * @param b1 Valeur bleue de la premiere couleur
   * @param r2 Valeur rouge de la deuxieme couleur
   * @param g2 Valeur verte de la deuxieme couleur
   * @param b2 Valeur bleue de la deuxieme couleur
   * @return PGraphics pour fond
   */
  public PGraphics backgroundGradient(int r1, int g1, int b1, int r2, int g2, int b2) {
    PGraphics G = createGraphics(width, height);
    G.beginDraw();
    for (int i = 0; i < height; i++) {
      float r = map(i, 0, height, r1, r2);
      float g = map(i, 0, height, g1, g2);
      float b = map(i, 0, height, b1, b2);
      G.stroke(r, g, b);
      G.line(0, i, width, i);
    }
    G.endDraw();
    return G;
  }

  /**
   * Gestion du clic de souris. Le programme se ferme si le bouton fermer est
   * clique
   */
  @Override
  public void mouseClicked() {
    int x = width - padding - bandHeight / 2;
    int y = padding + bandHeight / 2;
    if (mouseX > x - 30 && mouseX < x + 30
      && mouseY > y - 30 && mouseY < y + 30) {
      System.exit(0);
    }
  }
}

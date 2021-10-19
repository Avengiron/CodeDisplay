# CodeDisplay
Affichage stylisé d'un bout de code, pour présentation

Le bout de code à afficher doit se trouver dans le fichier code.txt, dans le dossier data/.
La police doit également se trouver dans le dossier data/.

![Exemple](https://github.com/Avengiron/HostReadMeImages/blob/main/CodeDisplay/CodeDisplay.png)

La hauteur de la fenêtre est fixe. La taille de la police est définie par le nombre de lignes dans le code. 
La largeur s'adapte par rapport à la ligne de code la plus longue.
On peut jouer sur le padding, le bandHeight et la marginWidth de la manière suivante :

![Espaces](https://github.com/Avengiron/HostReadMeImages/blob/main/CodeDisplay/EmptyCanvas.png)

Les mots clef sont identifiés avec la classe Keywords, et la coloration se fait dans la classe CodeDisplay.
* La couleur des mots clef peut se personnaliser dans la méthode `selectWordColor`. 
* La couleur des caractères et chaines de caractères peut se personnaliser dans la méthode `colorRegularLineOfCode`.
* La couleur des commentaires peut se personnaliser dans la méthode `colorCommentLineOfCode`.

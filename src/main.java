import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class main {
	
	private static int nbCaseX = 20 ;
	private static int nbCaseY = 20 ;
	private static int tailleCase = 30;

	public static void main(String[] args) {
		String rep ;
		do {
			
			nbCaseX = Integer.parseInt(JOptionPane.showInputDialog("Nombre de cases en x ?")) ;
			nbCaseY = Integer.parseInt(JOptionPane.showInputDialog("Nombre de cases en y ?")) ;
			tailleCase = Integer.parseInt(JOptionPane.showInputDialog("Taille des cases ? (min 18)")) ;
			tailleCase = tailleCase < 18 ? 18 : tailleCase ;
			
			Labyrinthe l = new Labyrinthe(nbCaseX, nbCaseY, tailleCase) ;
			Fenetre f = new Fenetre(nbCaseX*tailleCase+20, nbCaseY*tailleCase+40, l) ;
			l.start();
			rep = JOptionPane.showInputDialog("Recommencer ?") ;
		}while(rep.equalsIgnoreCase("oui")) ;
				
		}
	}

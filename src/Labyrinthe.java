import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Labyrinthe extends JPanel{

	private int nbCaseX ;
	private int nbCaseY ;
	private int tailleCase ;
	private int caseCourX, caseCourY ;
	private int vitesse = 20 ;
	
	private Pile pile ;
	private Case[][] lesCases ; 
	
	public Labyrinthe(int nbCaseX, int nbCaseY, int tailleCase) {
		this.setPreferredSize(new Dimension(nbCaseX * tailleCase, nbCaseY * tailleCase));
		this.nbCaseX = nbCaseX;
		this.nbCaseY = nbCaseY;
		this.tailleCase = tailleCase;
		caseCourX = 0 ;
		caseCourY = 0 ;
		pile = new Pile() ;
		lesCases = new Case[nbCaseY][nbCaseX] ;
				
	}
	
	public void start() {
		remplirLesCases();
		boolean jeu = true ;
		while(jeu) {
			lesCases[caseCourY][caseCourX].setC(Color.green);
			if(!nextCase()) {
				boolean r = reculer() ;
				if(!r) {
					caseCourX = -1 ;
					caseCourY = -1 ;
					jeu = false ;
				}
			}
			
			
			this.repaint();
			try{
                Thread.sleep(vitesse);
            }catch(InterruptedException e){}
		}
	}
	
	public void remplirLesCases() {
		for(int i = 0 ; i < lesCases.length ; i++) {
			for(int j = 0 ; j < lesCases[i].length ; j++) {
				lesCases[i][j] = new Case(tailleCase, j*tailleCase, i*tailleCase, true, true,Color.white) ;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0,0,nbCaseX * tailleCase, nbCaseY * tailleCase) ;
		for(int i = 0 ; i < lesCases.length ; i++) {
			for(int j = 0 ; j < lesCases[i].length ; j++) {
				if(i != caseCourY && j != caseCourX) { 
					lesCases[i][j].setC(Color.white);
				}
				if(i == 0 && j == 0) {
					lesCases[i][j].setC(Color.blue);
				}
				if(i == nbCaseY-1 && j == nbCaseX-1) {
					lesCases[i][j].setC(Color.red);
				}
				
				lesCases[i][j].paint(g) ;
			}
		}
	}
	
	public boolean nextCase() {
		int[] posibilite = {1,1,1,1};//N,S,O,E
		
		//Nord
		if(caseCourY-1 < 0 || lesCases[caseCourY-1][caseCourX].isVisite()) {
			posibilite[0] = 0 ;
		}
		//Sud
		if(caseCourY+1 >= nbCaseY || lesCases[caseCourY+1][caseCourX].isVisite()) {
			posibilite[1] = 0 ;
		}
		//Ouest
		if(caseCourX-1 < 0 || lesCases[caseCourY][caseCourX-1].isVisite()) {
			posibilite[2] = 0 ;
		}
		//Est
		if(caseCourX+1 >= nbCaseX || lesCases[caseCourY][caseCourX+1].isVisite()) {
			posibilite[3] = 0 ;
		}
		
		//Si possibilité d'avancé
		int sommePos = 0 ;
		for(int nb : posibilite) {
			sommePos += nb ;
		}		
		if(sommePos == 0) {
			return false ;
		}
		
		Random random = new Random() ;
		int nbR = 0 + random.nextInt(sommePos) ;
		int cpt = 0 ;
		
		for(int i = 0 ; i < posibilite.length ; i++) {
			if(posibilite[i] == 1) {
				if(nbR == cpt) {
					switch(i) {
					case 0 ://Nord
						lesCases[caseCourY-1][caseCourX].setSud(false) ;
						lesCases[caseCourY-1][caseCourX].setVisite(true);
						caseCourY-- ;
						pile.ajouter(Pile.NORD) ;
						break ;
						
					case 1 ://Sud
						lesCases[caseCourY][caseCourX].setSud(false) ;
						lesCases[caseCourY+1][caseCourX].setVisite(true);
						caseCourY++ ;
						pile.ajouter(Pile.SUD) ;
						break ;
					
					case 2 ://Ouest
						lesCases[caseCourY][caseCourX-1].setEst(false); ;
						lesCases[caseCourY][caseCourX-1].setVisite(true);
						caseCourX--;
						pile.ajouter(Pile.OUEST) ;
						break ;
						
					case 3 ://Est
						lesCases[caseCourY][caseCourX].setEst(false); ;
						lesCases[caseCourY][caseCourX+1].setVisite(true);
						caseCourX++;
						pile.ajouter(Pile.EST) ;
						break ;
					}
					
					return true ;
				}
				else {
					cpt++ ;
				}
			}
		}				
		return true ;
	}
	
	public boolean reculer() {
		char last = pile.getDernier() ;
		pile.retirer() ;
		if(last == 'f') {
			return false ;
		}
		
		switch(last) {
		case Pile.NORD ://Nord
			caseCourY++ ;
			break ;
			
		case Pile.SUD ://Sud
			caseCourY-- ;
			break ;
			
		case Pile.OUEST ://Ouest
			caseCourX++ ;
			break ;
		case Pile.EST ://Est
			caseCourX-- ;
			break ;
	}
		return true ;
	}
		
}

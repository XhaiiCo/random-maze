import java.awt.Color;
import java.awt.Graphics;

public class Case {

	private int taille, x, y ;
	private boolean sud, est ;
	private final static double TAILLE_MUR = 0.06 ;
	private boolean visite ;
	private Color c ;
	
	public Case(int taille, int x, int y, boolean sud, boolean est, Color c) {
		
		setTaille(taille) ;
		setX(x) ;
		setY(y) ;
		setSud(sud) ;
		setEst(est) ;
		this.c = c ;
		visite = false ;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		if(taille > 0) {
			this.taille = taille;
		}		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x > 0) {
			this.x = x;
		}		
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(y > 0) {
			this.y = y;
		}
	}

	public boolean isSud() {
		return sud;
	}

	public void setSud(boolean sud) {
		this.sud = sud;
	}

	public boolean isEst() {
		return est;
	}

	public void setEst(boolean est) {
		this.est = est;
	}	
	
	public boolean isVisite() {
		return visite;
	}
	
	public void setVisite(boolean visite) {
		this.visite = visite;
	}
	
	public void setC(Color c) {
		this.c = c;
	}
	
	public void paint(Graphics g) {
		int t =  (int) (taille*TAILLE_MUR) ;
		g.setColor(c) ;
		if(!visite) {
			g.setColor(c.black);
		}
		g.fillRect(x, y, taille, taille);
		g.setColor(Color.black);
		if(sud == true) {
			g.fillRect(x, (y+taille)-t, taille, t);
		}
		if(est == true) {
			g.fillRect((x+taille)-t, y, t, taille);
		}
	}

}

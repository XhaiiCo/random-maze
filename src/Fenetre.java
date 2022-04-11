import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	private int tailleX, tailleY ;
	
	public Fenetre(int tailleX, int tailleY, JPanel jp) {
		super("Labyrinthe") ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(new Dimension(tailleX, tailleY));
		this.setVisible(true);
		//this.setResizable(false);
		this.setContentPane(jp);
	}
	
	public void setTailleX(int tailleX) {
		if(tailleX > 0) {
			this.tailleX = tailleX;
		}	
	}
	
	public void setTailleY(int tailleY) {
		if(tailleY > 0) {
			this.tailleY = tailleY;
		}		
	}

}

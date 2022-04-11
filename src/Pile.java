import java.util.ArrayList;
import java.util.List;

public class Pile {

	private List<Character> pile ;
	public final static char NORD = 'n',
								SUD = 's',
								EST = 'e',
								OUEST = 'o' ;
	
	public Pile() {
		pile = new ArrayList<>() ;
	}
	
	public boolean ajouter(char direction) {
		
		if(direction != NORD &&
			direction != SUD &&
			direction != EST &&
			direction != OUEST) {
			return false ;			
		}
		
		return pile.add(direction) ;
	}
	
	public boolean retirer() {
		if(pile.size() ==  0) {
			return false ;
		}
		pile.remove(pile.size()-1) ;
		return true ;
	} 
	
	public char getDernier() {
		if(pile.size() == 0) {
			return 'f' ;
		}		
			return pile.get(pile.size()-1) ;
		}
	
	public List<Character> clone(){
		return pile ;
	}
	
	public int nbElement() {
		return pile.size() ;
	}
	
	@Override
	public String toString() {
		return pile.toString() ;
	}
	
}


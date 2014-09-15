package lsthatcreep;

public class Creep {
	public int hp;
	
	public Creep(int i) {
		hp = i;
	}
	public int HP(){
		return hp;
	}
	public void minus(int i) {
		hp -= i;
		
	}
	public void hit() {
		hp-=50;
		if(hp<=0){
			hp=0;
		}
		
	}
	public boolean isDead() {
		if(hp<=0){
			return true;
		} else {
			return false;
		}
	}
	
}

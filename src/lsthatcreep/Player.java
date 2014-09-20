package lsthatcreep;

public class Player {
	
		public int CurrentHp;
		public int CurrentDmg;
		public int CurrentDelay;
		public int Score;
		public int Coin;
		private int Delay;
		private int Hp;
		public int count;
	
	public Player(){
	
	}
	/*public void setPlayer(int hp, int dmg, int delay){
		this.Hp = hp;
		CurrentHp = hp;
		CurrentDmg = dmg;
		this.Delay = delay;
		CurrentDelay = delay;
		count = 0;
	}*/
	public void setPlayer(Hero hero){
		Hp = hero.hp;
		CurrentHp = Hp;
		CurrentDmg = hero.damage;
		Delay = hero.delay;
		CurrentDelay = Delay;
	}
	public void GOLD(int coin){
		Coin += coin;
	}
	public int HP(){
		return Hp;
	}
	public void Hit(int dmg){
		Hp -= dmg;
	}
	
	public void Heal(int heal){
		Hp += heal;
	}
	
	public void UpDMG(int up){
		CurrentDmg += up;
	}
	
	public void UpAS(int up){
		Delay -= up;
	}
	public void setDelay() {
		CurrentDelay = Delay;
	}
}

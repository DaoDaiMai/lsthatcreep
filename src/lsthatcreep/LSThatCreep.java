package lsthatcreep;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LSThatCreep extends BasicGame{

	public static int GAME_WIDTH = 1000;
	public static int GAME_HEIGHT = 700;
	public static int CREEP_HP = 300;
	private Creep[] creep;
	private Player[] player;
	private int CREEP_COUNT = 2;
	private int END;
	//private int count;
	private boolean isStarted;
	private Hero[] hero;
	private boolean haveset;
	
	

	
	public LSThatCreep(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
		      LSThatCreep game = new LSThatCreep("Lastshot that Creep");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
		      appgc.start();
		} catch (SlickException e) {
		      e.printStackTrace();
		    }
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if(isStarted) {
			if (END>0) {
				scoreRender(g);
			} else {
				hero[player[0].count].Draw(100,300);
				hero[player[1].count].Draw(700,300);
				FinalScoreRender(g);
			}
		} else {
			pickhero(g);
		}
		
	}

	

	@Override
	public void init(GameContainer container) throws SlickException {
		hero = new Hero[6];
		//count = 0;
		for (int i = 0; i<6; i++) {
			hero[i] = new Hero();
			hero[i].set(i);
		}
		isStarted = false;
		haveset = false;
		END = 10;
		player = new Player[2];
		player[0] = new Player();
		player[1] = new Player();
		creep = new Creep[CREEP_COUNT];
		for (int i = 0; i < CREEP_COUNT; i++) {
			creep[i] = new Creep(CREEP_HP);
		}
		
	}

	@Override
	public void update(GameContainer container, int g) throws SlickException {
		if(isStarted){
			if(!haveset){
				setPlayerHero();		
			}
			CreepUpdate();
			Delay();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(isStarted) {	
			startkey(key);
		} else {
			playkey(key);
		}
		if(key == Input.KEY_ENTER){
			isStarted = true;
		} 
	 }
	private void scoreRender(Graphics g){
		g.drawString("p1 score " + player[0].Score, GAME_WIDTH/2, 0);
		g.drawString("p2 score " + player[1].Score, GAME_WIDTH/2+120, 0);
		g.drawString("p1 dmg   " + player[0].CurrentDmg, GAME_WIDTH/2, 20);
		g.drawString("p2 dmg   " + player[1].CurrentDmg, GAME_WIDTH/2+120, 20);
		g.drawString("p1 delay " + player[0].delay, GAME_WIDTH/2, 40);
		g.drawString("p2 delay " + player[1].delay, GAME_WIDTH/2+120, 40);
		g.drawString("p1 delay : " + player[0].CurrentDelay, 200, 0);
		g.drawString("p2 delay : " + player[1].CurrentDelay, 200, 20);
		g.drawString(" A/K", GAME_WIDTH/2, 450);
		g.drawString(" S/L", GAME_WIDTH/2 + 45, 450);
		for (int i = 0; i < CREEP_COUNT ; i++){
			creep[i].render(i);
		}
		g.drawString("Creep Left "+END, 30, 40);
		hero[player[0].count].Draw(100,300);
		hero[player[1].count].Draw(700,300);
	}
	
	private void Delay() {
		if (player[0].CurrentDelay>0) { 
			player[0].CurrentDelay--;
		}
		if (player[1].CurrentDelay>0) {
			player[1].CurrentDelay--;
		}
		
	}

	private void CreepUpdate() {
		for (int i = 0 ; i < CREEP_COUNT ; i++){
			if (!creep[i].isDead())	{
				creep[i].minus(i+1);
			}	
			if (creep[i].isDead()){
				if (END>0){
					END--;
				}
				creep[i].Spawn();
			}
		}
		
	}

	private void setPlayerHero() {
		player[0].setPlayer(hero[player[0].count]);
		player[1].setPlayer(hero[player[1].count]);
		haveset = true;
		
	}

	private void playkey(int key) {
		if(key == Input.KEY_A){
			player[0].count++;
			if(player[0].count > 5){
				player[0].count = 0;
			}
		}
		if(key == Input.KEY_K){
			player[1].count++;
			if(player[1].count > 5){
				player[1].count = 0;
			}
		}
		
	}

	private void startkey(int key) {
		if ((key == Input.KEY_A || key == Input.KEY_S) && player[0].CurrentDelay == 0) {
			player[0].setDelay();
			if(key == Input.KEY_A) {
				creep[0].hit();
			}
			if(key == Input.KEY_S) {
				creep[1].hit();
			}
			if(creep[0].isDead() || creep[1].isDead()) {
				player[0].Score++;
			}
		}
		if ((key == Input.KEY_K || key == Input.KEY_L) && player[1].CurrentDelay == 0) {
			player[1].setDelay();
			if(key == Input.KEY_K) {
				creep[0].hit();
			}
			if(key == Input.KEY_L) {
				creep[1].hit();
			}
			if(creep[0].isDead() || creep[1].isDead()) {
				player[1].Score++;
			}
		} 
		
	}
	
	private void pickhero(Graphics g) throws SlickException {
		hero[player[0].count].Render(0,g);
		hero[player[1].count].Render(1,g);
		
	}

	private void FinalScoreRender(Graphics g) {
		g.drawString("p1 score " + player[0].Score, GAME_WIDTH/2-100, 400);
		g.drawString("p2 score " + player[1].Score, GAME_WIDTH/2+100, 400);
		if(player[0].Score > player[1].Score){
			g.drawString("player1 WIN", GAME_WIDTH/2-20, 200);
		} else if (player[0].Score < player[1].Score){
			g.drawString("player2 WIN", GAME_WIDTH/2-20, 200);
		} else {
			g.drawString("Draw", GAME_WIDTH/2-20, 200);
		}
	}	
}

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
		if(isStarted){
			if (END>0) {
				g.drawString("p1 score " + player[0].Score, GAME_WIDTH/2, 0);
				g.drawString("p2 score " + player[1].Score, GAME_WIDTH/2, 20);
				//g.drawString("p1 dmg " + player[0].CurrentDmg, GAME_WIDTH/2, 40);
				//g.drawString("p2 dmg " + player[1].CurrentDmg, GAME_WIDTH/2, 60);
				//g.drawString("p1 delay " + player[0].CurrentDelay, GAME_WIDTH/2, 80);
				//g.drawString("p2 delay " + player[1].CurrentDelay, GAME_WIDTH/2, 100);
				//g.drawString("p1 score " + player[0].Score, GAME_WIDTH/2, 0);
				//g.drawString("p2 score " + player[1].Score, GAME_WIDTH/2, 20);
				//g.drawString("p1 delay : " + player[0].CurrentDelay, 200, 0);
				//g.drawString("p2 delay : " + player[1].CurrentDelay, 200, 20);
				g.drawString(" A/K", GAME_WIDTH/2, 450);
				g.drawString(" S/L", GAME_WIDTH/2 + 45, 450);
				for (int i = 0; i < CREEP_COUNT ; i++){
					creep[i].render(i);
				}
				g.drawString("Creep Left "+END, 30, 40);
			} else {
				g.drawString("END", GAME_WIDTH/2, GAME_HEIGHT/2);
				g.drawString("p1 score " + player[0].Score, GAME_WIDTH/2, 0);
				g.drawString("p2 score " + player[1].Score, GAME_WIDTH/2, 20);
			}
		} else {
			hero[player[0].count].Render(0,g);
			hero[player[1].count].Render(1,g);
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
		//player[0].setPlayer(200, 50, 60);
		//player[1].setPlayer(200, 80, 90);
		creep = new Creep[CREEP_COUNT];
		for (int i = 0; i < CREEP_COUNT; i++) {
			creep[i] = new Creep(CREEP_HP);
		}
		
	}

	@Override
	public void update(GameContainer container, int g) throws SlickException {
		if(isStarted){
			if(!haveset){
				player[0].setPlayer(hero[player[0].count]);
				player[1].setPlayer(hero[player[1].count]);
				haveset = true;
			}
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
			if (player[0].CurrentDelay>0) { 
				player[0].CurrentDelay--;
			}
			if (player[1].CurrentDelay>0) {
				player[1].CurrentDelay--;
			}
		} 
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(isStarted) {	
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
		} else {
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
		if(key == Input.KEY_ENTER){
			isStarted = true;
		} 
	 }
}

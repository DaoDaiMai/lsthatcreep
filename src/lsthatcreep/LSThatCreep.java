package lsthatcreep;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class LSThatCreep extends BasicGame{

	public static int GAME_WIDTH = 700;
	public static int GAME_HEIGHT = 500;
	private Creep[] creep;
	private int[] score;
	private int[] HIT_DELAY;
	private int CREEP_COUNT = 2;
	private int CREEP_HP = 300;

	
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
		g.drawString("p1 score " + score[0], GAME_WIDTH/2, 0);
		g.drawString("p2 score " + score[1], GAME_WIDTH/2, 20);
		//g.drawString("p1 delay : " + HIT_DELAY[0], 200, 0);
		//g.drawString("p2 delay : " + HIT_DELAY[1], 200, 20);
		for (int i = 0; i < CREEP_COUNT ; i++){
			creep[i].render(i);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		creep = new Creep[CREEP_COUNT];
		score = new int[2];
		HIT_DELAY = new int[2];
		for (int i = 0; i < CREEP_COUNT; i++) {
		      creep[i] = new Creep(CREEP_HP);
		}
	}

	@Override
	public void update(GameContainer container, int g) throws SlickException {
		for (int i = 0 ; i < CREEP_COUNT ; i++){
			if (!creep[i].isDead())	{
				creep[i].minus(i+1);
			}
				
			if (creep[i].isDead()){
				creep[i].hp=CREEP_HP;
			}
		}
		if (HIT_DELAY[0]>0) { 
			HIT_DELAY[0]--;
		}
		if (HIT_DELAY[1]>0) {
			HIT_DELAY[1]--;
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_A || key == Input.KEY_S && HIT_DELAY[0] == 0) {
			HIT_DELAY[0] = 40;
			if(key == Input.KEY_A) {
				creep[0].hit();
			}
			if(key == Input.KEY_S) {
				creep[1].hit();
			}
			if(creep[0].isDead() || creep[1].isDead()) {
					score[0]++;
			}
		}
		if (key == Input.KEY_K || key == Input.KEY_L && HIT_DELAY[1] == 0) {
			HIT_DELAY[1] = 40;
			if(key == Input.KEY_K) {
				creep[0].hit();
			}
			if(key == Input.KEY_L) {
				creep[1].hit();
			}
			if(creep[0].isDead() || creep[1].isDead()) {
				score[1]++;
			}
		}
	 }
}

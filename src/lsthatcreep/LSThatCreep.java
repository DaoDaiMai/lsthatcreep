package lsthatcreep;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import flappydot.PillarPair;

public class LSThatCreep extends BasicGame{

	public static int GAME_WIDTH = 700;
	public static int GAME_HEIGHT = 500;
	private Creep[] creep;
	private int score;
	private int hitdelay;
	private boolean re;
	private Creep creepB;

	
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
		g.drawString(""+score, GAME_WIDTH/2, 0);
		g.drawString("delay : " + hitdelay, 200, 0);
		g.drawString("A", 390, GAME_HEIGHT/2);
		for(int i=0 ; i < creep.hp/10 ; i++){
			g.drawString("|", 400+i*2, GAME_HEIGHT/2);
		}
		g.drawString("S", 390, GAME_HEIGHT/2+20);
		for(int i=0 ; i < creepB.hp/10 ; i++){
			g.drawString("|", 400+i*2, GAME_HEIGHT/2+20);
		}
		/*if(!creep.isDead()){
			g.drawString(""+creep.hp, GAME_WIDTH/2, GAME_HEIGHT/2);
		} */
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		creep = new Creep[2];
		for (int i = 0; i < 2; i++) {
		      creep[i] = new Creep(300);
		}
	}

	@Override
	public void update(GameContainer container, int g) throws SlickException {
		if (!creep.isDead())	{
			creep.minus(1);
		}
		if (hitdelay>0) { 
			hitdelay--;
		}
		if (creep.isDead()){
			creep.hp=300;
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_A && !creep.isDead() && hitdelay == 0) {
			creep.hit();
			hitdelay=20;
			if(creep.isDead()){
				score++;
			}
		}
	 }
}

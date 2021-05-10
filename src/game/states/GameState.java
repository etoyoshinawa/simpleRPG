package game.states;

import java.awt.Graphics2D;

import game.utils.KeyHandler;
import game.utils.MouseHandler;

public abstract class GameState {
	
	protected GameStateManager gameStateManager;
	protected boolean done;
	
	public GameState (GameStateManager gameStateManager) {
		
		this.gameStateManager = gameStateManager;
		
	}
	public abstract void update(long time);
	public abstract void input(MouseHandler mouseHandler, KeyHandler keyHandler);
	public abstract void render(Graphics2D g);


}

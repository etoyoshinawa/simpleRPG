package game.states;

import java.awt.Color;
import java.awt.Graphics2D;

import game.utils.KeyHandler;
import game.utils.MouseHandler;

public class MenuState extends GameState {
	
	private boolean start=false;
	private boolean hard=false;
	private boolean easy=false;
	
	public MenuState (GameStateManager gameStateManager)
	{
		super(gameStateManager);
	}
    
	
	@Override
	public void update(long time) {
		if(easy)
			gameStateManager.setCurrentState(gameStateManager.MAP);
		
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
			if(key.enter.down) {
				start= true;
			}else {start= false;}
			if(key.easy.down) {
				easy = true;
			}else {easy = false;}
			if(key.hard.down) {
		        hard= true;
			}else {hard = false;}
			}


	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.drawString("Start", 220, 220);
		g.drawString("EASY", 100, 300);
		g.drawString("HARD", 340, 300);
		
		if(easy)
		{
			g.fillRect(100, 290, 30, 10);
			g.setColor(Color.BLACK);
			g.drawString("EASY", 100, 300);
		}
		
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public boolean isHard() {
		return hard;
	}

	public void setHard(boolean hard) {
		this.hard = hard;
	}

	public boolean isEasy() {
		return easy;
	}

	public void setEasy(boolean easy) {
		this.easy = easy;
	}
	

}

package game.states;

import java.awt.Color;
import java.awt.Graphics2D;

import game.utils.KeyHandler;
import game.utils.MouseHandler;

public class GameOverState extends GameState{
   public GameOverState(GameStateManager gameStateManager)
   {
	   super(gameStateManager);
   }

@Override
public void update(long time) {
	// TODO Auto-generated method stub
	
}

@Override
public void input(MouseHandler mouseHandler, KeyHandler keyHandler) {
	// TODO Auto-generated method stub
	
}

@Override
public void render(Graphics2D g) {
	// TODO Auto-generated method stub
	g.setColor(Color.WHITE);
	g.drawString("GameOver", 220, 220);
	
}
}

package game.states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import game.entities.Enemy;
import game.entities.Player;
import game.graphics.Sprite;
import game.utils.Assets;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;
import game.world.World;

public abstract class PlayState extends GameState 
{
    public PlayState (GameStateManager gameStateManager) 
    {
    	super(gameStateManager);

    }
    
    protected Player player;
    
    protected ArrayList<Enemy> enemies;
    
    protected double playtime;
    
    protected static World world;
    
    
    public static World getWorld() {
		return world;
	}

    
    public void update(long time)
    {   
    	
    	for(int i=0;i<enemies.size();i++)
    	{
    		enemies.get(i).update(player,time);	
    		if (enemies.get(i).die) enemies.remove(i);
    	}
    	player.update(time,enemies);
    	world.update();
    	if (player.die)
    	{
    		gameStateManager.setCurrentState(gameStateManager.GAMEOVER);
    	}
    }
    
    public void input(MouseHandler mouseHandler, KeyHandler keyHandler)
    {
    	player.input(keyHandler, mouseHandler);
    }
    
    public void render(Graphics2D g)
    {
    	g.setColor(Color.BLACK);
  
    	world.render(g);
    	player.render(g);
    	for(int i=0;i<enemies.size();i++)
    	{
    		enemies.get(i).render(g);
    	}
    }
}

package game.states;

import java.awt.Graphics2D;
import java.util.ArrayList;


import game.GamePanel;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;

public class GameStateManager {
	
	public static final int MAP = 1;  
	public static final int MENU = 0;
	public static final int PAUSE = 3;
	public static final int GAMEOVER = 2;
	
	private int currentState;
	
	
	private ArrayList<GameState> states ;
	 
	public static Vector map;
	
    public GameStateManager ()
     {
    	 map= new Vector(GamePanel.width,GamePanel.height);
    	 Vector.setWorldVar(map.x, map.y);
    	 states = new ArrayList<GameState>();
    	 states.add(new MenuState(this));
    	 states.add(new Map(this)); 
    	 states.add(new GameOverState(this));
    	 currentState = MENU;
     }
    
    public int getCurrentState()
    {
    	return currentState;
    }
    public void setCurrentState(int i)
    {
    	currentState=i;
    }
     
     public void pop (int state)
     {
    	 states.remove(state);
     }
     
     public void add (int state)
     {
    	 if (state == MAP) {states.add(new Map(this));}
    	 if (state == MENU) {states.add(new MenuState(this));}
    	 if (state == PAUSE) {states.add(new PauseState(this));}
    	 if (state == GAMEOVER) {states.add(new GameOverState(this));}
     }
     
     public void addandpop(int state)
     {
    	 states.remove(0);
    	 add(state);
     }
     public void controlState()
     {
    	 
     }
     
     public void update(long time)
     {
    	 Vector.setWorldVar(map.x, map.y);
    
    	
    	 // static
    	  //for(int i=0;i<states.size(); i++)
    	  //{
    		//  states.get(i).update(time);//@@
    	  //}
    	 states.get(currentState).update(time);
     }
     
     public void input( MouseHandler mouseHandler, KeyHandler keyHandler) {
    	/* for(int i=0;i<states.size(); i++)
   	  {
   		  states.get(i).input(mouseHandler, keyHandler);
   	  }*/
    	 
    	 states.get(currentState).input(mouseHandler, keyHandler);
	     }
     
     public void render(Graphics2D g)
     {
      	/* for(int i=0;i<states.size(); i++)
   	  {
   		  states.get(i).render(g);
   	  }*/
    	 states.get(currentState).render(g);
     }
}

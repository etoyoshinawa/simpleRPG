package game.utils;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


import game.GamePanel;





public class KeyHandler implements KeyListener {

	public static List<Key> keys = new ArrayList<Key>();
	
	public class Key
	{
		public int presses, absorbs;
		public boolean down, clicked;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed)
		{
			if(pressed != down) down= pressed;
			if (pressed) {
				presses ++;
			}
		}
		
		
		public void tick()
		{
			if(absorbs<presses) {
				absorbs++;
				clicked=true;
			} else {
				clicked =false;
			}
		}
	}

	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key menu = new Key();
	public Key enter = new Key();
	public Key esc = new Key();
	public Key start= new Key();
	public Key hard= new Key();
	public Key easy= new Key();
	
	public KeyHandler(GamePanel gamePanel) {
		gamePanel.addKeyListener(this);
		
		// TODO Auto-generated constructor stub
	}
	
	
	public void releaseAll() {
		for(int i=0;i<keys.size();i++)
		{
			keys.get(i).down=false;
		}

	}
	
	public void tick ()
	{
		for (int i=0; i<keys.size();i++)
		{
			keys.get(i).tick();
		}
	}
	
	public void Toggle(KeyEvent e, boolean pressed)
	{
		if (e.getKeyCode()==KeyEvent.VK_W) up.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_S) down.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_A) left.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_D) right.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_SPACE) attack.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_M) menu.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_F) enter.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) esc.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) esc.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_H) hard.toggle(pressed);
		if (e.getKeyCode()==KeyEvent.VK_E) easy.toggle(pressed);
		

		
			
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//nothing
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Toggle(e, false);
		// TODO Auto-generated method stub
		
	}
	
	

}

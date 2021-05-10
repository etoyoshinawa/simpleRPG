package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.graphics.Sprite;
import game.states.PlayState;
import game.utils.AABB;
import game.utils.Bound;
import game.utils.HealthBar;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;
import game.world.World;

public class Player extends Entity{
	
	private int rhitbound;
    
	public Player(Vector orgin, int size) {
		super(new Sprite("C:\\Users\\Latitude 7490\\eclipse-workspace\\RPG2\\res\\entity\\player1.png"),orgin, size);
		Vector boundVector = new Vector (orgin.x+5,orgin.y +10);
		bound= new AABB(boundVector,20,20);
		rhitbound = 60;
		hitBound =new AABB(rhitbound,this);
		maxHealth =500;
		health =500;
		damage =50;
		healthBar= new HealthBar(this);
	}
	
	
	public void move()
	{
		if(up) { 
			    if(upcollide()) dy=0;
		        else 
		            {
			         dy=-acc;
			         if (dy<maxSpeed) {dy= -maxSpeed;}}
		             }else {if (dy<0) { 
			              dy+=deacc;
			               if(dy>0)dy=0;} 
		            
		        }
		 
		if(down) { if(downcollide()) dy=0;else {
			dy=+acc;if (dy>maxSpeed) {dy= maxSpeed;}}
		}else {if (dy>0) { 
			              dy-=deacc;
			               if(dy<0)dy=0;} 
		}	
		if(left) { if(leftcollide())dx =0;else {
			dx=-acc;if (dx<maxSpeed) {dx= -maxSpeed;}}
		}else {if (dx<0) { 
			              dx+=deacc;
			               if(dx>0)dx=0;} 
		      }
		
		if(right) { if(rightcollide()) dx=0;else {
			dx=+acc;if (dx<maxSpeed) {dx= maxSpeed;}}
		}else {if (dx>0) { 
			             dx-=deacc;
			               if(dx<0) dx=0;} 
		      }
	}

	
	
	public void update (long time,ArrayList<? extends Enemy> enemy)
	{
		super.update();
		healthBar.update();
		canAttack(time);
		attacking(time);
		move();
		pos.x+=dx;
		pos.y+=dy;
		hitted(enemy);
		killed();
		
		bound.setVector(pos.x+5, pos.y+10);
		hitBound.setVector(pos.x+size/2-rhitbound/2, pos.y+size/2-rhitbound/2);
	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(ani.getImage(),(int)(pos.x), (int)(pos.y), size, size, null);
		g.drawRect((int)bound.getPos().x, (int)bound.getPos().y,(int) bound.getWidth(),(int) bound.getHeight());
		g.setColor(Color.red);
		g.drawRect((int)pos.x, (int)pos.y, 32, 32);
		g.setColor(Color.YELLOW);
		//if(attack)
		//{
		//g.drawOval((int)hitBound.getPos().x,(int)hitBound.getPos().y , rhitbound, rhitbound);
		//}
		healthBar.render(g);
	}
	
	public void input (KeyHandler key, MouseHandler mouse) {
		if(key.up.down) {
			up= true;
		}else {up= false;}
		if(key.down.down) {
			down = true;
		}else {down = false;}
		if(key.left.down) {
	        left= true;
		}else {left = false;}
		if(key.right.down) {
	        right = true;
		}else {right = false;}
        if(key.attack.down&&canAttack) {
			attack = true;
			attacktime = System.nanoTime();
		}else {attack = false;}
	}
	
	
	public AABB getBound()
	{
		return bound;
	}
	
	public void hitted(ArrayList<? extends Enemy> enemy)
	{
		for(Enemy e:enemy)
		{
		if(e.hitBound.cirColBox(bound)&&e.attack)
			{
			System.out.println("Hitted");
			health-=50;
			}
		}
	}
	
	
	

 
}

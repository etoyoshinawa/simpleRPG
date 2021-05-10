package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


import game.graphics.Sprite;
import game.utils.AABB;
import game.utils.Vector;

public class Enemy extends Entity {
	
	
	private AABB sense;
	private long randomtime;
	

	private int rsense;
	private int rhitbound;
	private Random random = new Random();
	private int r;
	private double distance=0 ;
	public Enemy(Sprite sprite, Vector orgin, int size) {
		 super(sprite, orgin, size);
		 acc = 0.5f;
		 maxSpeed = 0.5f;
		 rsense=128;
		 rhitbound =32;
		 Vector center = new Vector(pos.x+6,pos.y+7);
		 bound = new AABB(center,20,20);
		 sense = new AABB(rsense, this);
		 hitBound = new AABB(rhitbound,this);
		 Random random = new Random(); 
	}
	private void randomDirection()
	{   
		randomtime = System.nanoTime();
		int i=random.nextInt(5);
		if (i==0) up =true; else up=false;
		if (i==1) down =true; else down=false;
		if (i==2) left =true; else left=false;
		if (i==3) right =true; else right=false;
		if (i==4)  {up=false;down=false;left=false;right=false;}
	}
	private boolean canRandomMove(long time)
	{ 
		if((time-randomtime>=2000000000)||upcollide()||downcollide()||leftcollide()||rightcollide()) return true;
		else return false;
	}
	
	private void RandomMove(long time)
	{
		distance = Math.sqrt((getPos().x-spawnPos.x)*(getPos().x-spawnPos.x)+(getPos().y-spawnPos.y)*(getPos().y-spawnPos.y));
		if(distance<=100) {
			canRandomMove(time);
			if(canRandomMove(time)) randomDirection();
			move();
		}
		else {
			up=false;
			down=false;
			left=false;
			right=false;
			input(spawnPos);
	
			move();
		}
	}
	private void move() {
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
	 
		if(down) { 
			if(downcollide()) dy=0;
			else {
				dy=+acc;
				if (dy>maxSpeed) {dy= maxSpeed;}
				}
				}
		/*else {if (dy>0) { 
		              dy-=deacc;
		               if(dy<0)dy=0;} 
		}	*/
		
		if(left) {  if(leftcollide())dx =0;else {
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
	
	public void input(Vector goal)
	{
		if(pos.y>goal.y+5) {
			up= true;down =false; 
		}else {up= false;}
		if(pos.y<goal.y-5) {
			down = true;up=false;
		}else {down = false;}
		if(pos.x>goal.x+5) {
	        left= true;right=false;
		}else {left = false;}
		if(pos.x<goal.x-5) {
	        right = true;left=false;
		}else {right = false;}
	}
	
	public void hitted(Player player)
	{
		if(player.hitBound.cirColBox(bound)&&player.attack)
			{
			System.out.println("Hitted");
			health-=damage;
			}
	}
	public void hit(Player player)
	{
		if(hitBound.cirColBox(player.bound)&&canAttack)
			{System.out.println("fuck player !!");
			attack = true;attacktime= System.nanoTime();}else attack =false;
	}

	public void update(Player player, long time) {
	   super.update();
	   
	   if (sense.colCircleBox(player.getBound())) {
		   //System.out.println("fuck player!!!");
		   input(player.pos);
		   move();
	   }
	   else {
		   RandomMove(time);
		   }
	   hitted(player);
	   hit(player);
	   canAttack(time);
	   attacking(time);
	   killed();
	  
	   pos.x+=dx;
	   pos.y+=dy;
	   bound.setVector(pos.x+6, pos.y+10);
	   hitBound.setVector(pos.x+size/2-rhitbound/2, pos.y+size/2-rhitbound/2);
	   sense.setVector(pos.x+size/2-rsense/2, pos.y+size/2-rsense/2);
	   
	  
	}
	
	@Override
	public void render(Graphics2D g) {
		 g.drawImage(ani.getImage(),(int)pos.x,(int)pos.y,size,size,null);
		 g.setColor(Color.RED);
		 //g.drawRect((int)pos.getWorldVar().x,(int)pos.getWorldVar().y,32,32);
		// g.drawOval((int)sense.getPos().x,(int)sense.getPos().y , rsense, rsense);
		 g.setColor(Color.YELLOW);
		// g.drawOval((int)hitBound.getPos().x,(int)hitBound.getPos().y , rhitbound, rhitbound);
		 g.setColor(Color.BLACK);
		// g.drawRect((int)bound.getPos().x,(int)bound.getPos().y, (int)bound.getHeight(),(int)bound.getWidth());
		 healthBar.render(g);
	}
	
}

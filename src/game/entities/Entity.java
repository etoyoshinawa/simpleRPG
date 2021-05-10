package game.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



import game.graphics.Animation;
import game.graphics.Sprite;
import game.states.PlayState;
import game.utils.AABB;
import game.utils.HealthBar;
import game.utils.KeyHandler;
import game.utils.MouseHandler;
import game.utils.Vector;
import game.world.World;


public abstract class Entity {
	
	protected final int ATTACK =5;
	protected final int DIE = 4;	
	protected final int UP=3;
	protected final int DOWN =2;
	protected final int LEFT = 1;
	protected final int RIGHT =0;	
	protected Animation ani;
	protected Sprite sprite;
	protected Vector pos;
	protected Vector spawnPos;
	protected int size;
	protected int currentAnimation;
	
	protected boolean up;
	protected boolean down;
	protected boolean left;
	protected boolean right;
	protected boolean attack;
	protected boolean attacking;
	public boolean die;
	protected long attacktime;
	protected boolean canAttack =true;
	protected int attackSpeed=1050;
	protected int attackAni=650;
	protected long attackDuration = 1000000000;
	protected float health;
	protected float maxHealth;
	protected float damage;

	
	protected double now;
	protected double lasttime;
	protected double deltatime=0;
	
	protected float dx;
	protected float dy;
	
	protected float maxSpeed=2;
	protected float acc=2;
	protected float deacc=1;
	

	protected AABB bound;
	protected AABB hitBound;
	protected HealthBar healthBar;
	
	private World world = PlayState.getWorld();
	
	
	
	public Entity(Sprite sprite, Vector orgin, int size)
	{
		this.sprite=sprite;
		spawnPos = orgin;
		pos=new Vector();
		pos.x=orgin.x;
		pos.y=orgin.y;
		this.size= size;
		
		healthBar= new HealthBar(this);
		
		ani= new Animation();
		
		setAnimation(UP, sprite.getSpriteArray(UP),10);
	}
	
	public int getSize()
	{
		return size;
	}
	
	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public long getAttacktime() {
		return attacktime;
	}

	public void setAttacktime(long attacktime) {
		this.attacktime = attacktime;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public long getAttackDuration() {
		return attackDuration;
	}

	public void setAttackDuration(long attackDuration) {
		this.attackDuration = attackDuration;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
    
	public float getMaxHealth()
	{
		return maxHealth;
	}
	public Vector getPos()
	{
		return pos;
	}
	
	public void setSprite(Sprite sprite)
	{
		this.sprite=sprite;
	}
	
	public void setSize(int i)
	{
		size = i;
	}
	
	public void setAcc(float f)
	{
		acc = f;
	}
    
	public void setDeacc(float f)
	{
		deacc =f ;
	}
	
	
	
	public void setAnimation(int i, BufferedImage[] frames, int delay)
	{
		currentAnimation =i; 
		ani.setFrame(frames);
		ani.setDelay(delay);
	}
	
	public Animation getAnimation() {
		return ani;
	}
	
	public void animate() {
		    if (attacking) {
		    	if(currentAnimation<5)
		    		setAnimation(currentAnimation+ATTACK, sprite.getSpriteArray(currentAnimation + ATTACK), attackAni/100);
		    }else if(up) {
			if(currentAnimation!=UP||ani.getDelay()==-1)
			{
				setAnimation(UP, sprite.getSpriteArray(UP), 10);
			}
		    }
			else if(down) {
				if(currentAnimation!=DOWN||ani.getDelay()==-1)
				{
					setAnimation(DOWN, sprite.getSpriteArray(DOWN), 10	);
				}
			}
			else if(left) {
				if(currentAnimation!=LEFT||ani.getDelay()==-1)
				{
					setAnimation(LEFT, sprite.getSpriteArray(LEFT), 10	);
				}
			}
			else if(right) {
				if(currentAnimation!=RIGHT||ani.getDelay()==-1)
				{
					setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10	);
				}
			} else if(die)
			{
				 if (currentAnimation != DIE || ani.getDelay() == -1) {
		                setAnimation(DIE, sprite.getSpriteArray(DIE), 15);
			}}
		    
			else {if(currentAnimation<=4)
				setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
			else setAnimation(currentAnimation-ATTACK, sprite.getSpriteArray(currentAnimation-ATTACK), -1);
			}
		
	}
	
	protected boolean isAttacking(double time) {

        if((attacktime / 1000000) > ((time / 1000000) - attackSpeed)) {
            canAttack = false;
        } else {
            canAttack = true;
        }

        if((attacktime / 1000000) + attackDuration > (time / 1000000)) {
            return true;
        }

        return false;
    }
	
	
	public boolean upcollide()
	{
		float toprightx = bound.getPos().x;
		float topleftx = bound.getPos().x+20;
		float toprighty = bound.getPos().y-maxSpeed;
		int tiley =(int)toprighty/32;
		int tile1x=(int)toprightx/32;
		int tile2x=(int)topleftx/32;
		if(tile1x==tile2x) {
			  return !world.getTile(tile1x, tiley).walkable();
		 }
		else {
			return !world.getTile(tile1x, tiley).walkable()||!world.getTile(tile2x, tiley).walkable();
		}
	}
	
	public boolean downcollide()
	{
		float downleftx = bound.getPos().x+20;
		float downrightx= bound.getPos().x;
		float downrighty = bound.getPos().y+20+maxSpeed;
		int tiley =(int)downrighty/32;
		int tile1x=(int)downrightx/32;
		int tile2x=(int)downleftx/32;
		if(tile1x==tile2x) {
			  return !world.getTile(tile1x, tiley).walkable();
		 }
		else {
			return !world.getTile(tile1x, tiley).walkable()||!world.getTile(tile1x, tiley).walkable();
		}
	}
	public boolean leftcollide()
	{
		float topleftx = bound.getPos().x-maxSpeed;
		float toplefty = bound.getPos().y;
		float downlefty = bound.getPos().y+20;
		int tilex =(int)topleftx/32;
		int tile1y=(int)toplefty/32;
		int tile2y=(int)downlefty/32;
		if(tile1y==tile2y) {
			  return !world.getTile(tilex, tile1y).walkable();
		 }
		else {
			return !world.getTile(tilex, tile1y).walkable()||!world.getTile(tilex, tile2y).walkable();
		}
	}
	public boolean rightcollide()
	{
		float toprightx = bound.getPos().x+20+maxSpeed;
		float toprighty = bound.getPos().y;
		float downrighty =bound.getPos().y+20;
		int tilex =(int)toprightx/32;
		int tile1y=(int)toprighty/32;
		int tile2y=(int)downrighty/32;
		if(tile1y==tile2y) {
			  return !world.getTile(tilex, tile1y).walkable();
		 }
		else {
			return !world.getTile(tilex, tile1y).walkable()||!world.getTile(tilex, tile2y).walkable();
		}
	}
	
	public void canAttack(long time)
	{
		if(time-attacktime>=attackDuration)
		{
			canAttack=true;
		}
		else canAttack =false;
	}
	public void attacking(long time)
	{
		if((time-attacktime)<=attackDuration)attacking = true;
		else attacking= false;
	}
	public void killed()
	{
		if (health<=0) die =true;
		else die=false;
	}
	
	
	
	public void update()
	{
		animate();
	    ani.update();
	    healthBar.update();
	}
	
	public abstract void render(Graphics2D g);
	public void input(KeyHandler key,MouseHandler mouse)
	{
		// not really need
	}
	
	public AABB getBound()
	{
		return bound;
	}
		
	
}

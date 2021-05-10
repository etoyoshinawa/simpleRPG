package game.entities;

import game.graphics.Sprite;
import game.utils.HealthBar;
import game.utils.Vector;

public class Skeleton extends Enemy {
	public Skeleton( Vector orgin, int size) {
		super(new Sprite("C:\\Users\\Latitude 7490\\eclipse-workspace\\RPG2\\res\\entity\\enemy.png",64,64), orgin, size);
		maxHealth = 100;
		health =100;
		damage =10;
		healthBar= new HealthBar(this);
		// TODO Auto-generated constructor stub
	}

}

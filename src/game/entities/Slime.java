package game.entities;

import game.graphics.Sprite;
import game.utils.HealthBar;
import game.utils.Vector;

public class Slime extends Enemy {

	public Slime( Vector orgin, int size) {
		super(new Sprite("C:\\Users\\Latitude 7490\\eclipse-workspace\\RPG2\\res\\entity\\slime.png",51,48), orgin, size);
		maxHealth = 50;
		health =50;
		damage =5;
		healthBar= new HealthBar(this);
		// TODO Auto-generated constructor stub
	}

	
}

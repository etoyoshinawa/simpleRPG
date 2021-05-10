package game.states;

import java.util.ArrayList;

import game.entities.Enemy;
import game.entities.Slime;
import game.entities.Player;
import game.entities.Skeleton;
import game.graphics.Sprite;
import game.utils.Vector;
import game.world.World;

public class Map extends PlayState {

	public Map(GameStateManager gameStateManager) {
		super(gameStateManager);
		world = new World("C:\\Users\\Latitude 7490\\eclipse-workspace\\RPG2\\res\\world\\world1.txt");
    	player = new Player(new Vector(100,100), 32);
    	enemies = new ArrayList<Enemy>();
    	enemies.add(new Slime(new Vector(200,200), 20));
    	enemies.add(new Skeleton( new Vector(250,100), 32));
	}
}

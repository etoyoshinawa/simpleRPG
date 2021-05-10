package game.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.utils.Bound;
import game.utils.Vector;

public class Tile {
	
	public static Tile[] tiles = new Tile[20];
	public static Tile  grassTile = new GrassTile(0);
	public static Tile  dirtTile = new DirtTile(1);
	public static Tile  rockTile = new RockTile(2);
	
	protected Vector pos;
	protected Bound bound;
	protected BufferedImage texture;
	protected int id;
	public int width=32, height=32;
	
   public Tile(BufferedImage texture,int id)
   {
	   this.texture=texture;
	   this.id=id; 
	   tiles[id]=this;
	   bound= new Bound(pos, width, height);// @@
   }
   
   public void setPos(int x, int y)
   {
	   pos.x=x;
	   pos.y=y;
	   bound.pos=pos;
   }
    public int getID()
    {
    	return id;
    }
    
    public void tick()
    {
    	
    }
    public void render(Graphics2D g,int x, int y )
    {
    	g.drawImage(texture, x, y, width,height,null);
    }
    
    public boolean walkable() {
    	return true;
    }
}

package game.utils;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bound {
	
	public Vector pos;
	public int width;
	public int height;
	
    public Bound(Vector pos, int width, int height)
    {
    	this.pos= pos;
    	this.width = width;
    	this.height = height;
    }
    
    public void drawBound(Graphics2D g)
    {
    	g.setColor(new Color(12,12,12));
    	g.drawRect((int)pos.x,(int) pos.y, (int)width,(int)height);
    }

}

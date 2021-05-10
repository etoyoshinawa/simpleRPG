package game.utils;

import java.awt.image.BufferedImage;

import game.graphics.ImageLoader;
import game.graphics.TileSprite;

import java.io.File;
import javax.imageio.ImageIO;

public class Assets {
	public static BufferedImage player, dirt, grass, rock;
	public static void init ()
	{
		TileSprite sheet = new TileSprite(ImageLoader.loadImage("C:\\Users\\Latitude 7490\\eclipse-workspace\\RPG2\\res\\tile\\master-tileset.png"));
		grass = sheet.Crop(0,128, 64, 64);
		dirt = sheet.Crop(0,192, 64, 64);
		rock = sheet.Crop(0,0,64,64);
	}
}

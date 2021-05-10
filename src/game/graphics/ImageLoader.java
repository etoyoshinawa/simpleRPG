package game.graphics;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) 
	{
		// TODO Auto-generated method stub
		try {
			return ImageIO.read(new File(path));		
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ko load dc");
		}
		return null;
	
	}
}
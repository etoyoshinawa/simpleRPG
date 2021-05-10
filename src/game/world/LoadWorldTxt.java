package game.world;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

public class LoadWorldTxt {
	
	public static int[] readFile (String path) 
	{ 
		int width, height,sX,sY;
		int[] tokens = new int[256];
		try {
		File file = new File(path);
		Scanner scan = new Scanner(file);
		width = scan.nextInt();
		height = scan.nextInt();
		sX=scan.nextInt();
		sY=scan.nextInt();
		int s=width*height+4;
		tokens[0]=width;
		tokens[1]=height;
		tokens[2]=sX;
		tokens[3]=sY;
		while(scan.hasNextInt())
		{
			for (int i=4;i<s;i++)
			{
				tokens[i]=scan.nextInt();
			}
		}		
		} catch (Exception e) {
			System.out.println("ko load dc txt .....");
		}
		return tokens;
	}	
}

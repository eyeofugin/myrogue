package rogue.framework.resources;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Sprite {
	private String path;
	private final int WIDTH,HEIGHT;
	private int[] pixels;
	
	public Sprite(String path, int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.path = path;
		this.pixels = new int[width*height];
		load();
	}
	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File(this.path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0,width,height,pixels,0,width);
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[] getPixels() {
		return this.pixels;
	}
}

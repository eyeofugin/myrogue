package util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet fonts = new SpriteSheet("res/textures/Fonts.png", 48,42);
	public static SpriteSheet fonts5x3 = new SpriteSheet("res/textures/Fonts5x3.png",33,32);
	public static SpriteSheet fonts5x8 = new SpriteSheet("res/textures/Fonts5x8.png",50,60);

	public SpriteSheet(String path, int width, int height) {
		this.HEIGHT = height;
		this.WIDTH = width;
		this.path = path;
		pixels = new int[HEIGHT * WIDTH];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File(path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0, width, height, pixels, 0, width);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] getSprite(int targetWidth, int targetHeight) {

		int[] preResult = new int[WIDTH*HEIGHT];
		int index = 0;
		
		for (int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
					preResult[index] = pixels[column + row * WIDTH];
					index++;
			}
		}
		
		
		
		double relation = (double) WIDTH / targetWidth;
		
		int[] result = new int[targetWidth * targetHeight];
		
		for(int row = 0; row < targetHeight; row++) {
			for(int column = 0; column < targetWidth; column++) {
				
				int ogRow = (int)(row*relation);
				int ogColumn = (int)(column*relation);
				
				result[column+row*targetWidth] = this.pixels[ogColumn+ogRow*WIDTH];
			}
		}
		
		return result;
	}

	public int[] resize(int[] pixels, int newWidth, int newHeight) {

		return null;
	}
}

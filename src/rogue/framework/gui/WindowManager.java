package rogue.framework.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Calendar;

import javax.swing.JFrame;

public class WindowManager extends Canvas{

	public static int width = 1920;
	public static int height= 1080;
	public static int tileSize = 32;
	
	private JFrame frame;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	private int[] tileIds;  
	public WindowManager(int x, int y) {
		
		this.width=x;
		this.height=y;
		Dimension size = new Dimension(this.width, this.height);
		setPreferredSize(size);
		tileIds = new int[(width/tileSize)*(height/tileSize)];
		
		this.frame = new JFrame("Rogue");
		this.frame.setBounds(0, 0, 0, 0);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
	}
	public void start() {
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setTitle("rogue");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setVisible(true);
	}
	
	public void render(int[] p){
		paintP(p);
		Graphics g = getGraphics();
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		g.dispose();
		paint(g);
	}
	
	private void paintP(int[] p) {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = p[i];
		}
	}
	
	public void addMouse(MouseListener mouse) {
		addMouseListener(mouse);
	}
	public void addKey(KeyListener key) {
		addKeyListener(key);
	}
	public void dispose() {
		this.frame.dispose();
	}
}

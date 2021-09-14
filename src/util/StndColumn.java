package util;

import java.util.Arrays;

public class StndColumn {
	private String[] entries;
	private MyColor[] colors;
	
	public StndColumn(int size) {
		this.entries = new String[size];
	}
	
	public StndColumn(String[] entries) {
		this.entries = entries;
		this.colors = new MyColor[entries.length];
		for(int i = 0; i<entries.length; i++) {
			colors[i] = MyColor.WHITE;
		}
	}
	public StndColumn(String[] entries, MyColor[] color) {
		this.entries = entries;
		this.colors = color;
	}
	
	public void addEntry(int index, String entry) {
		this.entries[index] = entry;
	}
	public void addEntries(String[] entries) {
		this.entries = entries;
	}

	public String[] getEntries() {
		return entries;
	}

	public void setEntries(String[] entries) {
		this.entries = entries;
	}

	
	
	public MyColor[] getColors() {
		return colors;
	}

	public void setColors(MyColor[] colors) {
		this.colors = colors;
	}

	@Override
	public String toString() {
		return "StndColumn [entries=" + Arrays.toString(entries) + "]";
	}
	
}

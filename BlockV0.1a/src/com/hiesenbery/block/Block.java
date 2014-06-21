package com.hiesenbery.block;

import org.newdawn.slick.Color;

import com.hiesenbery.world.World;

public abstract class Block {

	public static final int TOP = 0;
	public static final int RIGHT = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 3;
	
	public float blockSize = World.TILE_SIZE;
	
	protected Color texture;
	protected String id;
	
	public int x, y;

	public boolean connected[] = new boolean[4];
	private boolean discovered = false;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Color getTexture() {
		return texture;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean equals(Object obj) {
		Block block = (Block) obj;
		return block.getTexture() == this.texture;
	}

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

}

package com.hiesenbery.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.hiesenbery.entity.Player;

public abstract class World {

	public static final float WORLD_WIDTH = 20000f;
	public static final float WORLD_HEIGHT = 20000f;
	public static final float TILE_SIZE = 20.0f;

	public float width;
	public float height;
	
	public Player player;
	
	public static Rectangle worldBound;
	
	public World() {
	}
	
	public void add(Player player) {
		this.player = player;
	}

	public abstract void init(GameContainer container, StateBasedGame game);

	public abstract void render(GameContainer container, StateBasedGame game, Graphics g);

	public abstract void update(GameContainer container, StateBasedGame game, int delta);

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}

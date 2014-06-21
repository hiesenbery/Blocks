package com.hiesenbery.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {

	public Entity() {
	}
	
	public abstract void init(GameContainer container, StateBasedGame game);

	public abstract void render(GameContainer container, StateBasedGame game, Graphics g);

	public abstract void update(GameContainer container, StateBasedGame game, int delta);

}

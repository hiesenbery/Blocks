package com.hiesenbery.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final int FPS = 60;

	public Main(String name) {
		super(name);
		addState(new BlockGame());
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		enterState(0);
	}

	public static void main(String[] args) {

		try {
			AppGameContainer container = new AppGameContainer(new Main("Block v0.1a"), WIDTH,
					HEIGHT, false);
			container.setTargetFrameRate(FPS);
			container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

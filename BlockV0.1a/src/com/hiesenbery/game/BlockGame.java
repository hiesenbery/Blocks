package com.hiesenbery.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.hiesenbery.entity.Player;
import com.hiesenbery.renderer.Camera;
import com.hiesenbery.world.Overworld;
import com.hiesenbery.world.World;

public class BlockGame extends BasicGameState {
	
	public World world = new Overworld();
	public Camera cam;
	
	public BlockGame() {
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		world.init(container, game);
		cam = new Camera(Main.WIDTH, Main.HEIGHT, world.player, world);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.black);
		g.drawString(Player.playerBound.getMaxX() + "/" + Overworld.worldBound.getMaxX(), 0, 15);
		cam.render(container, game, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		world.update(container, game, delta);
		cam.update(world.player);
	}

	@Override
	public int getID() {
		return 0;
	}

}

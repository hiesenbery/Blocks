package com.hiesenbery.renderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.hiesenbery.entity.Player;
import com.hiesenbery.world.Overworld;
import com.hiesenbery.world.World;

public class Camera {

	public static Rectangle viewport;
	
	/** The maximum X and Y position where the camera is able to move to
	 * 
	 */
	private float offsetMaxX, offsetMaxY;
	private float offsetMinX, offsetMinY = 0;

	private Overworld world;

	public Camera(float width, float height, Player player, World world) {
		this.world = (Overworld) world;
		viewport = new Rectangle(0, 0, width, height);
		setOffsetMaxX(Overworld.WORLD_WIDTH);
		setOffsetMaxY(Overworld.WORLD_HEIGHT);
		viewport.setCenterX((player.getCenterX()));
		viewport.setCenterY((player.getY()));
	}

	public void update(Player player) {
		updateMovement(player);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.translate(-viewport.getX(), -viewport.getY());
		g.setWorldClip(viewport);
		world.render(container, game, g);
		g.clearWorldClip();
	}

	private void updateMovement(Player player) {
		viewport.setCenterX(player.getCenterX());
		viewport.setCenterY(player.getCenterY());

		if (viewport.getMaxX() > offsetMaxX) {
			viewport.setX(offsetMaxX - viewport.getWidth());
		} else if (viewport.getX() < offsetMinX) {
			viewport.setX(offsetMinX);
		}

		if (viewport.getMaxY() > offsetMaxY) {
			viewport.setY(offsetMaxY - viewport.getHeight());
		} else if (viewport.getY() < offsetMinY) {
			viewport.setY(offsetMinY);
		}
	}

	public Rectangle getCamera() {
		return viewport;
	}

	public float getOffsetMaxX() {
		return offsetMaxX;
	}

	public void setOffsetMaxX(float offsetMaxX) {
		this.offsetMaxX = offsetMaxX;
	}

	public float getOffsetMaxY() {
		return offsetMaxY;
	}

	public void setOffsetMaxY(float offsetMaxY) {
		this.offsetMaxY = offsetMaxY;
	}

	public float getOffsetMinX() {
		return offsetMinX;
	}

	public void setOffsetMinX(float offsetMinX) {
		this.offsetMinX = offsetMinX;
	}

	public float getOffsetMinY() {
		return offsetMinY;
	}

	public void setOffsetMinY(float offsetMinY) {
		this.offsetMinY = offsetMinY;
	}

}

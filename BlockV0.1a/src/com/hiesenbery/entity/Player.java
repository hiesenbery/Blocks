package com.hiesenbery.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.hiesenbery.block.BlockID;
import com.hiesenbery.world.Overworld;
import com.hiesenbery.world.World;

public class Player extends Entity {

	public static final float WIDTH = World.TILE_SIZE;
	public static final float HEIGHT = World.TILE_SIZE;
	public static final float MAX_SPEED = 50f;

	public static final int RIGHT = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int UP = 3;

	private float speed = 1f;

	private int targetX;
	private int targetY;
	private int fps = 0;
	private boolean move[] = new boolean[4];
	private boolean moveHorizontal = false;
	private boolean moveVertical = false;
	private boolean waypoint = false;

	private Color color = EntityTexture.PLAYER_TEXTURE;
	private Rectangle coverage;
	public static Rectangle playerBound;
	private Input input;

	private Random rand = new Random();
	private List<Vector> query = new ArrayList<Vector>();
	private List<Vector> scannedUnit = new ArrayList<Vector>();

	public Player(int x, int y) {
		playerBound = new Rectangle(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
		coverage = new Rectangle(0, 0, World.TILE_SIZE * 9, World.TILE_SIZE * 9);
		coverage.setCenterX(playerBound.getCenterX());
		coverage.setCenterY(playerBound.getCenterY());
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

		g.setColor(color);
		g.fill(playerBound);
		g.draw(coverage);

		g.setColor(Color.cyan);
		g.setLineWidth(2.5f);
		for (int i = 0; i < scannedUnit.size(); i++) {
			g.drawLine(getCenterX(), getCenterY(), scannedUnit.get(i).getX()
					* WIDTH + (WIDTH / 2), scannedUnit.get(i).getY() * HEIGHT
					+ (HEIGHT / 2));
		}

	}

	public Iterator<Vector> iQuery;
	private Iterator<Vector> is;

	public Vector vect;
	int mX, mY;

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {

		handleInput(container);

		if (fps < 1) {
			fps++;
		} else {
			if (speed < MAX_SPEED) {
				speed += 1;
			}
			scan();
			updateCoverage();
			updateMovement();
			updateScanner();
			fps = 0;
		}

		checkWorldBound();

	}

	private void handleInput(GameContainer container) {
		input = container.getInput();
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			moveHorizontal = true;
			moveVertical = true;
			waypoint = true;
			targetX = input.getMouseX();
			targetX /= WIDTH;
			targetY = input.getMouseY();
			targetY /= HEIGHT;
			query.add(new Vector(targetX, targetY));
		}

		if (input.isKeyDown(Input.KEY_RIGHT)) {
			move[RIGHT] = true;
			waypoint = false;
		} else {
			move[RIGHT] = false;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			move[DOWN] = true;
			waypoint = false;
		} else {
			move[DOWN] = false;
		}

		if (input.isKeyDown(Input.KEY_LEFT)) {
			move[LEFT] = true;
			waypoint = false;
		} else {
			move[LEFT] = false;
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			move[UP] = true;
			waypoint = false;
		} else {
			move[UP] = false;
		}

	}

	private void updateMovement() {

		if (move[RIGHT]) {
			setX(getX() + speed);
		} else if (move[UP]) {
			setY(getY() - speed);
		} else if (move[LEFT]) {
			setX(getX() - speed);
		} else if (move[DOWN]) {
			setY(getY() + speed);
		}

		if (!move[RIGHT] && !move[UP] && !move[LEFT] && !move[DOWN]) {
			speed = 0;
		}

		if (waypoint) {
			if (move() == "done") {
				if (iQuery.hasNext()) {
					iQuery.remove();
				}
				nextMove = rand.nextInt(2);
			}
		}

	}

	private void updateCoverage() {
		coverage.setCenterX(getCenterX());
		coverage.setCenterY(getCenterY());
	}

	private void updateScanner() {

		is = scannedUnit.iterator();

		while (is.hasNext()) {

			pos = is.next();

			if (pos.getX() > (coverage.getMaxX() / WIDTH) - 1
					|| pos.getX() < (coverage.getMinX() / WIDTH)
					|| pos.getY() > (coverage.getMaxY() / HEIGHT) - 1
					|| pos.getY() < (coverage.getMinY() / HEIGHT)) {
				is.remove();
			}

		}
	}

	int nextMove;
	String status;

	private String move() {
		status = "undone";

		switch (nextMove) {

		case 0:
			if (moveHorizontal() == "done") {
				if (moveVertical() == "done") {
					status = "done";
				}
			}
			break;

		case 1:
			if (moveVertical() == "done") {
				if (moveHorizontal() == "done") {
					status = "done";
				}
			}
			break;

		}

		return status;
	}

	private String moveHorizontal() {
		String status = "undone";
		if (moveHorizontal) {
			iQuery = query.iterator();
			if (iQuery.hasNext()) {
				vect = iQuery.next();
			}
			mX = vect.getX();

			if (mX > (playerBound.getX() / WIDTH)) {
				playerBound.setX(playerBound.getX() + (speed));
			} else if (mX < (playerBound.getX() / WIDTH)) {
				playerBound.setX(playerBound.getX() - (speed));
			}

			if ((playerBound.getX() / WIDTH) == mX) {
				status = "done";
			} else {
				status = "undone";
			}

		}
		return status;
	}

	private String moveVertical() {
		String status = "undone";
		if (moveVertical) {
			iQuery = query.iterator();
			if (iQuery.hasNext()) {
				vect = iQuery.next();
			}
			mY = vect.getY();

			if (mY > (playerBound.getY() / HEIGHT)) {
				playerBound.setY(playerBound.getY() + (speed));
			} else if (mY < (playerBound.getY() / HEIGHT)) {
				playerBound.setY(playerBound.getY() - (speed));
			}

			if ((playerBound.getY() / HEIGHT) == mY) {
				status = "done";
			} else {
				status = "undone";
			}

		}

		return status;
	}

	private Vector pos;

	private void scan() {

		if (Overworld.blocks != null) {

			for (int i = (int) (coverage.getMinX() / WIDTH); i < (coverage
					.getMaxX() / WIDTH); i++) {
				for (int j = (int) (coverage.getMinY() / HEIGHT); j < coverage
						.getMaxY() / HEIGHT; j++) {
					if (i >= 0
							&& i < (int) (Overworld.WORLD_WIDTH / Overworld.TILE_SIZE)
							&& j >= 0
							&& j < (int) (Overworld.WORLD_HEIGHT / Overworld.TILE_SIZE)) {
						Overworld.blocks[i][j].setDiscovered(true);
						if (Overworld.blocks[i][j].getId() == BlockID.WATER_ID) {
							scannedUnit.add(new Vector(i, j));
						}
					}
				}
			}

		}

	}

	private void checkWorldBound() {

		if (Overworld.worldBound != null) {

			if (playerBound.getMaxX() > Overworld.worldBound.getMaxX()) {
				playerBound.setX(Overworld.worldBound.getMaxX() - WIDTH);
			} else if (playerBound.getMinX() < Overworld.worldBound.getMinX()) {
				playerBound.setX(Overworld.worldBound.getMinX());
			}

			if (playerBound.getMaxY() > Overworld.worldBound.getMaxY()) {
				playerBound.setY(Overworld.worldBound.getMaxY() - HEIGHT);
			} else if (playerBound.getMinY() < Overworld.worldBound.getMinY()) {
				playerBound.setY(Overworld.worldBound.getMinY());
			}

		}

	}

	public float getX() {
		return playerBound.getX();
	}

	public void setX(float x) {
		playerBound.setX(x);
	}

	public float getY() {
		return playerBound.getY();
	}

	public void setY(float f) {
		playerBound.setY(f);
	}

	public float getCenterX() {
		return playerBound.getCenterX();
	}

	public float getCenterY() {
		return playerBound.getCenterY();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}

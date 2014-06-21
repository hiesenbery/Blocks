package com.hiesenbery.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.hiesenbery.block.Block;
import com.hiesenbery.block.DirtBlock;
import com.hiesenbery.block.GrassBlock;
import com.hiesenbery.block.WaterBlock;
import com.hiesenbery.entity.Player;
import com.hiesenbery.renderer.Camera;

public class Overworld extends World {

	public List<WorldChunk> chunks = new ArrayList<WorldChunk>();
	private Random rand = new Random();

	public static Block blocks[][] = new Block[(int) (WORLD_WIDTH / TILE_SIZE)][(int) (WORLD_HEIGHT / TILE_SIZE)];
	
	public Overworld() {
		worldBound = new Rectangle(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		generate();
		add(new Player(36, 36));
		player.init(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

		for (int horizontal = (int) (Camera.viewport.getMinX() / TILE_SIZE); horizontal <= (int) (Camera.viewport
				.getMaxX() / TILE_SIZE); horizontal++) {
			for (int vertical = (int) (Camera.viewport.getMinY() / TILE_SIZE); vertical <= (int) (Camera.viewport
					.getMaxY() / TILE_SIZE); vertical++) {
				if (horizontal < (WORLD_WIDTH / TILE_SIZE) && horizontal >= 0
						&& vertical < (WORLD_HEIGHT / TILE_SIZE)
						&& vertical >= 0) {
					
					if(blocks[horizontal][vertical].isDiscovered()) {
						g.setColor(blocks[horizontal][vertical].getTexture());
						g.fillRect((horizontal * TILE_SIZE),
								(vertical * TILE_SIZE), TILE_SIZE, TILE_SIZE);
					}
					
				}
			}
		}
		player.render(container, game, g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		player.update(container, game, delta);
	}

	private void generate() {
		fillDirt();
		fillGrass();
		for (int i = 0; i <= ((WORLD_WIDTH * WORLD_HEIGHT) / 1000) * 0.1; i++) {
			System.out.println("Generating terrain: water" + "(" + i + "/"
					+ ((WORLD_WIDTH * WORLD_HEIGHT) / 1000) * 0.1 + ")");
			generateWater();
		}
	}

	private void fillDirt() {

		for (int horizontal = 0; horizontal < blocks.length; horizontal++) {
			for (int vertical = 0; vertical < blocks[horizontal].length; vertical++) {
				blocks[horizontal][vertical] = new DirtBlock(horizontal,
						vertical);
			}
		}

	}

	private void fillGrass() {
		for (int i = 0; i <= ((WORLD_WIDTH * WORLD_HEIGHT) / 1000) * 0.9; i++) {
			System.out.println("Generating terrain: grass" + "(" + i + "/"
					+ ((WORLD_WIDTH * WORLD_HEIGHT) / 1000) * 0.9 + ")");
			generatePatches(new GrassBlock());
		}
	}

	int x, y;

	private void generatePatches(Block block) {

		x = rand.nextInt((int) (WORLD_WIDTH / TILE_SIZE));
		y = rand.nextInt((int) (WORLD_HEIGHT / TILE_SIZE));

		if (blocks[x][y] == block) {
			generatePatches(block);
		} else {
			blocks[x][y] = block;
			if ((x - 1) >= 0) {
				blocks[x - 1][y] = block;
			}

			if ((x + 1) < (int) (WORLD_WIDTH / TILE_SIZE)) {
				blocks[x + 1][y] = block;
			}

			if ((y - 1) >= 0) {
				blocks[x][y - 1] = block;
			}

			if ((y + 1) < (int) (WORLD_HEIGHT / TILE_SIZE)) {
				blocks[x][y + 1] = block;
			}
		}

	}

	private void generateWater() {

		x = rand.nextInt(128);
		y = rand.nextInt(72);

		generatePatches(new WaterBlock());

	}

}

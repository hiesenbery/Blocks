package com.hiesenbery.world;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.hiesenbery.block.Block;
import com.hiesenbery.block.DirtBlock;
import com.hiesenbery.block.GrassBlock;
import com.hiesenbery.block.WaterBlock;

public class WorldChunk extends World {

	public static final float CHUNK_SIZE = 240.0f;
	public float minX, maxX, minY, maxY;
	public Block block;

	public static Block blocks[][] = new Block[24][24];
	
	private Random rand;

	public WorldChunk(float x, float y) {
		this.minX = x * CHUNK_SIZE;
		this.minY = y * CHUNK_SIZE;
		this.maxX = minX + CHUNK_SIZE;
		this.maxY = minY + CHUNK_SIZE;
		rand = new Random();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

		

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if (block == null) {
			block = new GrassBlock();
		}
	}

	public void setBlock(Block block) {
		this.block = block;
	}
	
}

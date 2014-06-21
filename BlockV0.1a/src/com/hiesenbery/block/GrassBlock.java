package com.hiesenbery.block;

public class GrassBlock extends GroundBlock {

	public GrassBlock() {
		this(0, 0);
	}
	
	public GrassBlock(int x, int y) {
		super(x, y);
		super.texture = BlockTexture.GRASS_TEXTURE;
		super.id = BlockID.GRASS_ID;
	}

}

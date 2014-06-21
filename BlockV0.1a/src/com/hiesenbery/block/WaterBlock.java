package com.hiesenbery.block;

public class WaterBlock extends GroundBlock {

	public WaterBlock() {
		this(0, 0);
	}
	
	public WaterBlock(int x, int y) {
		super(x, y);
		super.texture = BlockTexture.WATER_TEXTURE;
		super.id = BlockID.WATER_ID;
	}

}

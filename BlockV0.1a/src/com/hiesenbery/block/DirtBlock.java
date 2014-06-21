package com.hiesenbery.block;

public class DirtBlock extends GroundBlock {

	public DirtBlock() {
		this(0, 0);
	}

	
	public DirtBlock(int x, int y) {
		super(x, y);
		super.texture = BlockTexture.DIRT_TEXTURE;
		super.id = BlockID.DIRT_ID;
	}

}

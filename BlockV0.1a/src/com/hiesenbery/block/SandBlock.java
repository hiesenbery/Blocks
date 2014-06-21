package com.hiesenbery.block;

public class SandBlock extends GroundBlock {

	public SandBlock(int x, int y) {
		super(x, y);
		super.texture = BlockTexture.SAND_TEXTURE;
		super.id = BlockID.SAND_ID;
	}

}

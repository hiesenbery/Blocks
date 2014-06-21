package com.hiesenbery.block;

public abstract class GroundBlock extends Block {

	public GroundBlock() {
		this(0, 0);
	}
	
	public GroundBlock(int tileX, int tileY) {
		super(tileX, tileY);
	}
}

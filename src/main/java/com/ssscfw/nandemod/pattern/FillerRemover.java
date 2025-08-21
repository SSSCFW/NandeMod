package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import net.minecraft.item.ItemStack;

public class FillerRemover extends FillerPatternCore{

	public FillerRemover() {
		super(2);
	}

	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return emptyDU(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		fillerEX.initRotationPosition();
		fillerEX.setPower(32, 2);
	}
}

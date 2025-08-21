package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import net.minecraft.item.ItemStack;

public class FillerFillAll extends FillerPatternCore{
	public FillerFillAll() {
		super(0);
	}

	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return fill(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		super.initialize(fillerEX);
		fillerEX.setPower(16, 512);
	}
}

package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import net.minecraft.item.ItemStack;

public class FillerRemover2 extends FillerPatternCore{

	public FillerRemover2() {
		super(3);
	}

	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return emptyUD(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		super.initialize(fillerEX);
		fillerEX.cy = fillerEX.ey;
		fillerEX.setPower(16, 8);
	}
}

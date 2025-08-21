package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import net.minecraft.item.ItemStack;

public class FillerUnderFill extends FillerPatternCore{

	public FillerUnderFill() {
		super(6);
	}

	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return fill(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		super.initialize(fillerEX);
		fillerEX.setPower(16, 256);
		fillerEX.ey = fillerEX.sy - 1;
		fillerEX.sy = 1;
		fillerEX.cy = 1;
	}
}

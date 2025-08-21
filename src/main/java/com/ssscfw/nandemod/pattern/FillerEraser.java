package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import net.minecraft.item.ItemStack;

public class FillerEraser extends FillerPatternCore{
	public FillerEraser() {
		super(1);
	}


	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return erase(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		super.initialize(fillerEX);
		fillerEX.cy = fillerEX.ey;
		fillerEX.setPower(24, 256);
	}
}

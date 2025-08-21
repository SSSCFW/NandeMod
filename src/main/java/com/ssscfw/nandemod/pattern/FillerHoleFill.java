package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import net.minecraft.item.ItemStack;

public class FillerHoleFill extends FillerPatternCore{

	public FillerHoleFill() {
		super(5);
	}

	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return fill2(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		super.initialize(fillerEX);
		fillerEX.setPower(24, 64);
	}
}

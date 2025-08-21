package com.ssscfw.nandemod.pattern;

import com.ssscfw.nandemod.tile.TileOldFiller;
import com.ssscfw.nandemod.utils.Alldata;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class FillerFlooring extends FillerPatternCore{
	public FillerFlooring() {
		super(9);
	}

	@Override
	public boolean iteratePattern(TileOldFiller fillerEX, ItemStack stack) {
		return flooring(fillerEX, stack);
	}

	@Override
	public void initialize(TileOldFiller fillerEX) {
		super.initialize(fillerEX);
		fillerEX.cy--;
		fillerEX.setPower(32, 128);
	}

	public boolean flooring(TileOldFiller fillerEX, ItemStack stack) {
		if(stack == null) {
			return false;
		}
		while(fillerEX.cz >= fillerEX.sz && fillerEX.cz <= fillerEX.ez) {
			while(fillerEX.cx >= fillerEX.sx && fillerEX.cx <= fillerEX.ex) {
				if(isChange(fillerEX, stack)) {
					setBlock(fillerEX, fillerEX.cx, fillerEX.cy, fillerEX.cz, stack);
					fillerEX.getWorldObj().markBlockForUpdate(fillerEX.cx, fillerEX.cy, fillerEX.cz);
					fillerEX.cx++;
					return false;
				}
				fillerEX.cx++;
			}
			fillerEX.cx = fillerEX.sx;
			fillerEX.cz++;
		}
		fillerEX.cz = fillerEX.sz;
		return true;
	}

	protected boolean isChange(TileOldFiller fillerEX, ItemStack checkStack) {
		Block changeBlock = fillerEX.getWorldObj().getBlock(fillerEX.cx, fillerEX.cy, fillerEX.cz);
		ItemStack stack = new ItemStack(changeBlock);
		return changeBlock != Blocks.air && changeBlock != Alldata.blocks.get("old_filler") && stack != checkStack;
	}
}

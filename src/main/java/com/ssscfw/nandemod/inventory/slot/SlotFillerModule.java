package com.ssscfw.nandemod.inventory.slot;

import com.ssscfw.nandemod.inventory.ContainerOldFiller;
import com.ssscfw.nandemod.tile.TileOldFiller;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFillerModule extends Slot {
    
	private ContainerOldFiller container;
	private TileOldFiller fillerEX;

	public SlotFillerModule(ContainerOldFiller container, int par2, int par3, int par4) {
		super(container.craftResult, par2, par3, par4);
		this.container = container;
		fillerEX = container.fillerEX;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}

	@Override
	public int getSlotStackLimit() {
		return 1;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack resultStack) {
		for(int slot = 0; slot < 9; ++slot) {
			ItemStack stack = fillerEX.getStackInSlot(slot);
			if(stack != null) {
				fillerEX.decrStackSize(slot, 1);
				if(stack.getItem().hasContainerItem()) {
					ItemStack container = new ItemStack(stack.getItem().getContainerItem());
					if(!stack.getItem().doesContainerItemLeaveCraftingGrid(stack) || !player.inventory.addItemStackToInventory(container)) {
						if(fillerEX.getStackInSlot(slot) == null) {
							fillerEX.setInventorySlotContents(slot, container);
						}
						else {
							player.dropItem(container.getItem(), 1);
						}
					}
				}
			}
		}
	}
}

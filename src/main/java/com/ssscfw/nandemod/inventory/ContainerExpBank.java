package com.ssscfw.nandemod.inventory;

import com.ssscfw.nandemod.tile.TileExpBank;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerExpBank extends Container {
    private TileExpBank tileEntity;

    public ContainerExpBank(TileExpBank tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }
    
    
}

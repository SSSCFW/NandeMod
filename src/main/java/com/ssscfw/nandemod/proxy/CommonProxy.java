package com.ssscfw.nandemod.proxy;

import com.ssscfw.nandemod.client.gui.EcGuiMaterializer2;
import com.ssscfw.nandemod.client.gui.GuiExpBank;
import com.ssscfw.nandemod.inventory.ContainerExpBank;
import com.ssscfw.nandemod.inventory.ContainerOldFiller;
import com.ssscfw.nandemod.inventory.EcContainerMaterializer2;
import com.ssscfw.nandemod.inventory.GuiOldFiller;
import com.ssscfw.nandemod.tile.EcTileEntityMaterializer2;
import com.ssscfw.nandemod.tile.TileExpBank;
import com.ssscfw.nandemod.tile.TileOldFiller;
import com.ssscfw.nandemod.utils.Constants;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

    public void registerTileEntitySpecialRenderer() {}

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == Constants.GUI_ID_MATERIALIZER2) {
            return new EcContainerMaterializer2(world, player.inventory);
        }
        if (id == Constants.GUI_ID_OLD_FILLER) {
            TileOldFiller tile = (TileOldFiller) world.getTileEntity(x, y, z);
            if (tile != null) {
                return new ContainerOldFiller(player.inventory, tile);
            }
        }
        if (id == Constants.GUI_ID_EXP_BANK) {
            TileExpBank tile = (TileExpBank) world.getTileEntity(x, y, z);
            if (tile != null) {
                //return new ContainerExpBank(tile);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == Constants.GUI_ID_MATERIALIZER2) {
            return new EcGuiMaterializer2(world, player.inventory);
        }
        if (id == Constants.GUI_ID_OLD_FILLER) {
            TileOldFiller tile = (TileOldFiller) world.getTileEntity(x, y, z);
            if (tile != null) {
                return new GuiOldFiller(player.inventory, tile);
            }
        }
        if (id == Constants.GUI_ID_EXP_BANK) {
            TileExpBank tile = (TileExpBank) world.getTileEntity(x, y, z);
            if (tile != null) {
                return new GuiExpBank(player, tile);
            }
        }
        
        return null;
    }


    
}

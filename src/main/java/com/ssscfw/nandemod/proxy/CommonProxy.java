package com.ssscfw.nandemod.proxy;

import com.ssscfw.nandemod.client.gui.EcGuiMaterializer2;
import com.ssscfw.nandemod.inventory.EcContainerMaterializer2;
import com.ssscfw.nandemod.tile.EcTileEntityMaterializer2;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (!world.blockExists(x, y, z))
			return null;
        if (world.getTileEntity(x, y, z) instanceof EcTileEntityMaterializer2) {
            return new EcContainerMaterializer2(world, player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (!world.blockExists(x, y, z))
			return null;
        if (world.getTileEntity(x, y, z) instanceof EcTileEntityMaterializer2) {
            return new EcGuiMaterializer2(world, player.inventory);
        }
        return null;
    }


    
}

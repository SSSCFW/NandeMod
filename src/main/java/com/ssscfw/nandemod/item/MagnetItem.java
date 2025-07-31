package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagnetItem extends Item {
    public MagnetItem() {
        String name = "magnet_item";
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(1);
        setCreativeTab(NandeMod.tabNandemod);
        GameRegistry.registerItem(this, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        if (!world.isRemote) {
            int radius = 5; // Define the radius for magnet effect
            List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(radius, radius, radius));
            for (EntityItem dropitem : items) {
                dropitem.delayBeforeCanPickup = 0;
                dropitem.setPosition(player.posX, player.posY, player.posZ);
                dropitem.onCollideWithPlayer(player);
            }
        }
        return item;
    }
}

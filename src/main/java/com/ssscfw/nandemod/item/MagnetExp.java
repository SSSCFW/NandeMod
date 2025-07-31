package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import ak.EnchantChanger.entity.EcEntityApOrb;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MagnetExp extends Item {
    public MagnetExp() {
        String name = "magnet_exp";
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
            List<EntityXPOrb> xpOrbs = world.getEntitiesWithinAABB(EntityXPOrb.class, player.boundingBox.expand(radius, radius, radius));
            for (EntityXPOrb xpOrb : xpOrbs) {
                xpOrb.setPosition(player.posX, player.posY, player.posZ);
                xpOrb.onCollideWithPlayer(player);
                player.xpCooldown = 0;
            }
            List<EcEntityApOrb> apOrbs = world.getEntitiesWithinAABB(EcEntityApOrb.class, player.boundingBox.expand(radius, radius, radius));
            for (EcEntityApOrb apOrb : apOrbs) {
                apOrb.setPosition(player.posX, player.posY, player.posZ);
                apOrb.func_70100_b_(player);
                player.xpCooldown = 0;
            }
        }
        return item;
    }
}

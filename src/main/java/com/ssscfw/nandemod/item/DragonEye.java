package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class DragonEye extends Item {
    public DragonEye() {
        String name = "dragon_eye";
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(64);
        setCreativeTab(NandeMod.tabNandemod);
        GameRegistry.registerItem(this, name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add("§7エンドで右クリックすると、");
        list.add("§7エンダードラゴンを召喚する。");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        if (!world.isRemote) {
            if (world.getBiomeGenForCoords((int) player.posX, (int) player.posZ).biomeName.equals("Sky")) {
                item.stackSize--;
                EntityDragon dragon = new EntityDragon(world);
                dragon.setPosition(0, Math.min(player.posY+40, 120), 0);
                world.spawnEntityInWorld(dragon);
                player.addChatMessage(new ChatComponentText("§dドラゴンを召喚しました。"));
            } else {
                player.addChatMessage(new ChatComponentText("§cエンドでのみ使用可能です。"));
            }
        }
        return item;
    }
}

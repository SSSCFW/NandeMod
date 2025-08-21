package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class CompactRottenFlesh extends ItemFood {
    public CompactRottenFlesh() {
        super(7, 0.4F, true);
        String name = "compact_rotten_flesh";
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(64);
        setCreativeTab(NandeMod.tabNandemod);
        setPotionEffect(Potion.hunger.id, 30, 0, 0.2F);
        GameRegistry.registerItem(this, name);
        GameRegistry.registerFuelHandler(new IFuelHandler() {
            @Override
            public int getBurnTime(ItemStack fuel) {
                if (fuel.getItem() == CompactRottenFlesh.this) {
                    return 20 * 20;
                }
                return 0;
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add("§7謎技術で圧縮した腐肉。");
        list.add("§7空腹になりにくい。");
        list.add("§7また、燃料にもなる。");
    }
}

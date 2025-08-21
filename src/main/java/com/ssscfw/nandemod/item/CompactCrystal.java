package com.ssscfw.nandemod.item;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class CompactCrystal extends Item {
    public CompactCrystal() {
        String name = "compact_crystal";
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(64);
        setCreativeTab(NandeMod.tabNandemod);
        GameRegistry.registerItem(this, name);
    }
}

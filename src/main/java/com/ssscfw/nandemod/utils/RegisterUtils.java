package com.ssscfw.nandemod.utils;

import com.ssscfw.nandemod.enchant.*;
import com.ssscfw.nandemod.item.*;
import com.ssscfw.nandemod.tile.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterUtils {
    public static void registerEnchantments() {
        // id, weight
        Alldata.enchantments.put("experience", new Experience(100, 5, "Experience"));
        Alldata.enchantments.put("repair", new Repair(101, 0, "Repair"));
        Alldata.enchantments.put("range_attack", new RangeAttack(102, 1, "RangeAttack"));
    }

    public static void registerItems() {
        Alldata.items.put("spawner_upgrader", new SpawnerUpgrader());
    }

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityUpgradeSpawner.class, "tileEntityUpgradeSpawner");
    }
}

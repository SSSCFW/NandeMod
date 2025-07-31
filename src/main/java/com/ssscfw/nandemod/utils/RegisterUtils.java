package com.ssscfw.nandemod.utils;

import com.ssscfw.nandemod.block.*;
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
        Alldata.enchantments.put("apdrop", new APDrop(103, 0, "APDrop"));
    }

    public static void registerItems() {
        Alldata.items.put("spawner_upgrader", new SpawnerUpgrader());
        Alldata.items.put("magnet_exp", new MagnetExp());
        Alldata.items.put("magnet_item", new MagnetItem());
        Alldata.items.put("magnet_all", new MagnetAll());
    }

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityUpgradeSpawner.class, "tileEntityUpgradeSpawner");
        GameRegistry.registerTileEntity(EcTileEntityMaterializer2.class, "container.materializer2");
    }

    public static void registerBlocks() {
        BlockMaterializer2 blockMaterializer2 = new BlockMaterializer2();
        Alldata.blocks.put("blockMaterializer2", blockMaterializer2);
        GameRegistry.registerBlock(blockMaterializer2, "blockMaterializer2");
    }
}

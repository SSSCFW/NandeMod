package com.ssscfw.nandemod.utils;

import com.ssscfw.nandemod.block.*;
import com.ssscfw.nandemod.enchant.*;
import com.ssscfw.nandemod.item.*;
import com.ssscfw.nandemod.pattern.FillerClearLiquid;
import com.ssscfw.nandemod.pattern.FillerEraser;
import com.ssscfw.nandemod.pattern.FillerFillAll;
import com.ssscfw.nandemod.pattern.FillerFillBox;
import com.ssscfw.nandemod.pattern.FillerFillWall;
import com.ssscfw.nandemod.pattern.FillerFlattener;
import com.ssscfw.nandemod.pattern.FillerFlooring;
import com.ssscfw.nandemod.pattern.FillerHoe;
import com.ssscfw.nandemod.pattern.FillerHoleFill;
import com.ssscfw.nandemod.pattern.FillerPatternCore;
import com.ssscfw.nandemod.pattern.FillerPatternRecipe;
import com.ssscfw.nandemod.pattern.FillerQuarry;
import com.ssscfw.nandemod.pattern.FillerRemover;
import com.ssscfw.nandemod.pattern.FillerRemover2;
import com.ssscfw.nandemod.pattern.FillerTorch;
import com.ssscfw.nandemod.pattern.FillerTower;
import com.ssscfw.nandemod.pattern.FillerUnderFill;
import com.ssscfw.nandemod.tile.*;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RegisterUtils {
    public static void registerEnchantments() {
        // id, weight
        Alldata.enchantments.put("experience", new Experience(100, 5, "Experience"));
        Alldata.enchantments.put("repair", new Repair(101, 1, "Repair"));
        Alldata.enchantments.put("range_attack", new RangeAttack(102, 1, "RangeAttack"));
        Alldata.enchantments.put("apdrop", new APDrop(103, 1, "APDrop"));
    }

    public static void registerItems() {
        Alldata.items.put("spawner_upgrader", new SpawnerUpgrader());
        Alldata.items.put("magnet_exp", new MagnetExp());
        Alldata.items.put("magnet_item", new MagnetItem());
        Alldata.items.put("magnet_all", new MagnetAll());
        Alldata.items.put("compact_crystal", new CompactCrystal());
        Alldata.items.put("dragon_eye", new DragonEye());
        Alldata.items.put("compact_rotten_flesh", new CompactRottenFlesh());
        Alldata.items.put("blue_apple", new BlueApple());
        Alldata.items.put("filler_module", new ItemFillerPattern());

    }

    public static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileEntityUpgradeSpawner.class, "tileEntityUpgradeSpawner");
        GameRegistry.registerTileEntity(EcTileEntityMaterializer2.class, "container.materializer2");
        GameRegistry.registerTileEntity(TileOldFiller.class, "tile.oldFiller");
        GameRegistry.registerTileEntity(TileExpBank.class, "tile.expBank");
    }

    public static void registerBlocks() {
        BlockMaterializer2 blockMaterializer2 = new BlockMaterializer2();
        Alldata.blocks.put("blockMaterializer2", blockMaterializer2);
        GameRegistry.registerBlock(blockMaterializer2, "blockMaterializer2");

        BlockOldFiller blockOldFiller = new BlockOldFiller();
        Alldata.blocks.put("old_filler", blockOldFiller);
        GameRegistry.registerBlock(blockOldFiller, "old_filler");

        BlockExpBank blockExpBank = new BlockExpBank();
        Alldata.blocks.put("exp_bank", blockExpBank);
        GameRegistry.registerBlock(blockExpBank, "exp_bank");
    }

    public static void registerFillerPatterns() {
        registerFiller(new FillerFillAll(), "bbb", "bbb", "bbb", 0);
		registerFiller(new FillerEraser(), "ggg", "g g", "ggg", 1);
		registerFiller(new FillerRemover(), "ggg", "ggg", "ggg", 2);
		registerFiller(new FillerRemover2(), "   ", "ggg", "ggg", 3);
		registerFiller(new FillerFlattener(), "   ", "ggg", "bbb", 4);
		registerFiller(new FillerHoleFill(), "   ", "   ", "bbb", 5);
		registerFiller(new FillerUnderFill(), "   ", "bbb", "bbb", 6);
		registerFiller(new FillerFillBox(), "bbb", "b b", "bbb", 7);
		registerFiller(new FillerFillWall(), "b b", "b b", "b b", 8);
		registerFiller(new FillerFlooring(), "   ", "bbb", "ggg", 9);
		registerFiller(new FillerTorch(), "b b", " b ", "b b", 10);
		registerFiller(new FillerTower(), " bb", " bb", " bb", 11);
		registerFiller(new FillerClearLiquid(), "   ", "ggg", "www", 12);
		registerFiller(new FillerHoe(), "bb ", " b ", " b ", 13);
		registerFiller(new FillerQuarry(), "g g", "g g", "bbb", 14);
    }

    public static void registerFiller(FillerPatternCore pattern, String s1, String s2, String s3, int meta) {
		FillerPatternRecipe.addRecipe(pattern, s1,s2,s3, 'b', Blocks.brick_block, 'g', Blocks.glass, 'w', Items.water_bucket);
		registerFiller(pattern, meta);
	}

    public static void registerFiller(FillerPatternCore pattern, int meta) {
		pattern.moduleItem = new ItemStack(Alldata.items.get("filler_module"), 1, meta);
		FillerPatternRecipe.addRecipe(pattern, pattern.moduleItem);
		((ItemFillerPattern)Alldata.items.get("filler_module")).maxItem = meta + 1;
	}
}

package com.ssscfw.nandemod.client;

import com.ssscfw.nandemod.NandeMod;
import com.ssscfw.nandemod.proxy.CommonProxy;
import com.ssscfw.nandemod.renderer.RenderOldFiller;
import com.ssscfw.nandemod.tile.TileOldFiller;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {/* 
    public static Document filler;
	@Override
	public void setupManuals() {
		initManualRecipes();
	}

	private void initManualRecipes() {
		ItemStack brick = new ItemStack(Blocks.brick_block);
		ItemStack glass = new ItemStack(Blocks.glass);
		ItemStack water = new ItemStack(Items.water_bucket);
		registerManualCraftingRecipe("fillall", new ItemStack(AkutoEngine.fillerModule, 1, 0), brick, brick, brick, brick, brick, brick, brick, brick, brick);
		registerManualCraftingRecipe("erase", new ItemStack(AkutoEngine.fillerModule, 1, 1), glass, glass, glass, glass, null, glass, glass, glass, glass);
		registerManualCraftingRecipe("removeUp", new ItemStack(AkutoEngine.fillerModule, 1, 2), glass, glass, glass, glass, glass, glass, glass, glass, glass);
		registerManualCraftingRecipe("removeDown", new ItemStack(AkutoEngine.fillerModule, 1, 3), null, null, null, glass, glass, glass, glass, glass, glass);
		registerManualCraftingRecipe("flattener", new ItemStack(AkutoEngine.fillerModule, 1, 4), null, null, null, glass, glass, glass, brick, brick, brick);
		registerManualCraftingRecipe("holefill", new ItemStack(AkutoEngine.fillerModule, 1, 5), null, null, null, null, null, null, brick, brick, brick);
		registerManualCraftingRecipe("underfill", new ItemStack(AkutoEngine.fillerModule, 1, 6), null, null, null, brick, brick, brick, brick, brick, brick);
		registerManualCraftingRecipe("fillbox", new ItemStack(AkutoEngine.fillerModule, 1, 7), brick, brick, brick, brick, null, brick, brick, brick, brick);
		registerManualCraftingRecipe("fillwall", new ItemStack(AkutoEngine.fillerModule, 1, 8), brick, null, brick, brick, null, brick, brick, null, brick);
		registerManualCraftingRecipe("flooring", new ItemStack(AkutoEngine.fillerModule, 1, 9), null, null, null, brick, brick, brick, glass, glass, glass);
		registerManualCraftingRecipe("filltorch", new ItemStack(AkutoEngine.fillerModule, 1, 10), brick, null, brick, null, brick, null, brick, null, brick);
		registerManualCraftingRecipe("tower", new ItemStack(AkutoEngine.fillerModule, 1, 11), null, brick, brick, null, brick, brick, null, brick, brick);
		registerManualCraftingRecipe("clearliquid", new ItemStack(AkutoEngine.fillerModule, 1, 12), null, null, null, glass, glass, glass, water, water, water);
		registerManualCraftingRecipe("fillhoe", new ItemStack(AkutoEngine.fillerModule, 1, 13), brick, brick, null, null, brick, null, null, brick, null);
		registerManualCraftingRecipe("quarry", new ItemStack(AkutoEngine.fillerModule, 1, 14), glass, null, glass, glass, null, glass, brick, brick, brick);
	}*/

    @Override
	public void registerTileEntitySpecialRenderer() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileOldFiller.class, new RenderOldFiller());
	}

    
}

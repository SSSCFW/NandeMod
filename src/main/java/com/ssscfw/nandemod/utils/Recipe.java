package com.ssscfw.nandemod.utils;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipe {
    public static void init() {
        ItemStack repairBook = new ItemStack(Items.enchanted_book);
        Alldata.addStoredEnchantment(repairBook, Alldata.enchantments.get("repair"), 1);
        GameRegistry.addRecipe(repairBook,
      "IDI",
                "DND",
                "IDI",
                'I', new ItemStack(Items.iron_ingot),
                'N', new ItemStack(Items.nether_star),
                'D', new ItemStack(Items.diamond));

        GameRegistry.addRecipe(new ItemStack(Alldata.items.get("spawner_upgrader")),
      "IDI",
                "DGD",
                "IDI",
                'I', new ItemStack(Items.iron_sword),
                'G', new ItemStack(Items.golden_apple, 1, 1),
                'D', new ItemStack(Items.diamond));
    }
}

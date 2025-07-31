package com.ssscfw.nandemod.utils;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
    GameRegistry.addRecipe(new ItemStack(Alldata.blocks.get("blockMaterializer2")),
        "NDN",
        "OOO",
        "   ",
        'O', new ItemStack(Blocks.obsidian),
        'N', new ItemStack(Items.nether_star),
        'D', new ItemStack(Blocks.diamond_block));
    GameRegistry.addRecipe(new ItemStack(Alldata.items.get("magnet_exp")),
        "R L",
        "R L",
        " D ",
        'R', new ItemStack(Blocks.redstone_block),
        'L', new ItemStack(Blocks.lapis_block),
        'D', new ItemStack(Blocks.diamond_block));
    GameRegistry.addRecipe(new ItemStack(Alldata.items.get("magnet_item")),
        "L E",
        "L E",
        " D ",
        'E', new ItemStack(Blocks.emerald_block),
        'L', new ItemStack(Blocks.lapis_block),
        'D', new ItemStack(Blocks.diamond_block));
    GameRegistry.addRecipe(new ItemStack(Alldata.items.get("magnet_all")),
        "   ",
        "IDE",
        "   ",
        'I', new ItemStack(Alldata.items.get("magnet_item")),
        'E', new ItemStack(Alldata.items.get("magnet_exp")),
        'D', new ItemStack(Blocks.diamond_block));

    String nameTrue = "flammpfeil.slashblade.named.yamato";
    ItemStack yamato = GameRegistry.findItemStack(SlashBlade.modid, nameTrue, 1);
    {
      ItemStack requiredBlade = yamato.copy();
      requiredBlade.setItemDamage(OreDictionary.WILDCARD_VALUE);
      String nameRequired = nameTrue + ".required";
      GameRegistry.registerCustomItemStack(nameRequired, requiredBlade);
      ItemSlashBladeNamed.NamedBlades.add(nameRequired);
      ItemStack yamato2 = SlashBlade.getCustomBlade(NandeMod.MODID, "slashblade_yamato2");
      {
        SlashBlade.addRecipe(yamato2.getUnlocalizedName(),
          new RecipeAwakeBlade(yamato2, requiredBlade, 
            "NNN",
            "NYN",
            "NNN",
            'N', new ItemStack(Items.nether_star),
            'Y', requiredBlade)
          );
      }
    }
  }
}

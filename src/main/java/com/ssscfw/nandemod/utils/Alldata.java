package com.ssscfw.nandemod.utils;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.HashMap;
import java.util.Map;

public class Alldata {
    public static Map<String, Enchantment> enchantments = new HashMap<String, Enchantment>();
    public static Map<String, Item> items = new HashMap<String, Item>();
    public static Map<String, Block> blocks = new HashMap<String, Block>();

    public static int intervalTorch = 4;

    public static void addStoredEnchantment(ItemStack itemStack, Enchantment ench, int level) {
        if (itemStack.getTagCompound() == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        if (!itemStack.getTagCompound().hasKey("StoredEnchantments", 9)) {
            itemStack.getTagCompound().setTag("StoredEnchantments", new NBTTagList());
        }

        NBTTagList nbttaglist = itemStack.getTagCompound().getTagList("StoredEnchantments", 10);
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", (short) ench.effectId);
        nbttagcompound.setShort("lvl", (short) ((byte) level));
        nbttaglist.appendTag(nbttagcompound);
    }

    public static boolean setBlockToAir(World world, int x, int y, int z) {
        if (x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000) {
            if (y < 0) {
                return false;
            } else if (y >= 256) {
                return false;
            } else {
                Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
                boolean flag = chunk.func_150807_a(x & 15, y, z & 15, Blocks.air, 0);
                world.theProfiler.startSection("checkLight");
                world.func_147451_t(x, y, z);
                world.theProfiler.endSection();
                return flag;
            }
        } else {
            return false;
        }
    }
}

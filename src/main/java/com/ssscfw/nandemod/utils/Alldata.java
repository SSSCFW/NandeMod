package com.ssscfw.nandemod.utils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;
import java.util.Map;

public class Alldata {
    public static Map<String, Enchantment> enchantments = new HashMap<String, Enchantment>();
    public static Map<String, Item> items = new HashMap<String, Item>();

    public static void addStoredEnchantment(ItemStack itemStack, Enchantment ench, int level)
    {
        if (itemStack.getTagCompound() == null)
        {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        if (!itemStack.getTagCompound().hasKey("StoredEnchantments", 9))
        {
            itemStack.getTagCompound().setTag("StoredEnchantments", new NBTTagList());
        }

        NBTTagList nbttaglist = itemStack.getTagCompound().getTagList("StoredEnchantments", 10);
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", (short)ench.effectId);
        nbttagcompound.setShort("lvl", (short)((byte)level));
        nbttaglist.appendTag(nbttagcompound);
    }
}

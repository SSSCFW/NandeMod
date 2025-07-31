package com.ssscfw.nandemod.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class APDrop extends Enchantment {
    public APDrop(int id, int weight, String name) {
        super(id, weight, EnumEnchantmentType.weapon);
        setName(name);
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}

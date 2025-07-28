package com.ssscfw.nandemod.enchant;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class Repair extends Enchantment {
    public Repair(int id, int weight, String name) {
        super(id, weight, EnumEnchantmentType.breakable);
        setName(name);
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }
}

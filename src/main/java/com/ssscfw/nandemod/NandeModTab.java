package com.ssscfw.nandemod;

import com.ssscfw.nandemod.utils.Alldata;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class NandeModTab extends CreativeTabs {
    public NandeModTab(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Alldata.items.get("spawner_upgrader");
    }

    @Override
    public String getTranslatedTabLabel() {
        return "NandeMod";
    }
    
}

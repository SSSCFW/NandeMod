package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemFillerPattern extends Item {
    
	public int maxItem;
	@SideOnly(Side.CLIENT)
	private IIcon[] icon;

	public ItemFillerPattern() {
		super();
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(NandeMod.tabNandemod);
		maxItem = 10;
        GameRegistry.registerItem(this, "filler_module");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		icon = new IIcon[maxItem];

		for(int i = 0; i < maxItem; i++) {
			icon[i] = iconRegister.registerIcon(NandeMod.MODID + ":pattern/fillerpattern_" + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icon[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		for(int i = 0; i < maxItem; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "fillerPattern." + stack.getItemDamage();
	}
}

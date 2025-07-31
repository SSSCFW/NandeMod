package com.ssscfw.nandemod.proxy;

import com.ssscfw.nandemod.utils.Alldata;

import mods.flammpfeil.slashblade.ItemRendererBaseWeapon;
import net.minecraftforge.client.MinecraftForgeClient;

public class InitProxyClient extends InitProxy{
	@Override
	public void initializeItemRenderer() {
		ItemRendererBaseWeapon renderer = new ItemRendererBaseWeapon();
		MinecraftForgeClient.registerItemRenderer(Alldata.items.get("slashblade_venus"), renderer);
		MinecraftForgeClient.registerItemRenderer(Alldata.items.get("slashblade_yamato2"), renderer);
	}
}
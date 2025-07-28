package com.ssscfw.nandemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ssscfw.nandemod.event.*;
import com.ssscfw.nandemod.utils.Recipe;
import com.ssscfw.nandemod.utils.RegisterUtils;

@Mod(modid = NandeMod.MODID, version = NandeMod.VERSION)
public class NandeMod
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "nandemod";
    public static final String VERSION = "1.0";
    public static CreativeTabs tabNandemod = new NandeModTab("NandeMod");
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        RegisterUtils.registerEnchantments();
        RegisterUtils.registerItems();
        RegisterUtils.registerTileEntity();
        Recipe.init();
        MinecraftForge.EVENT_BUS.register(new PlayerEvent());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		  FMLCommonHandler.instance().bus().register(new TickEvent());
    }
}

package com.ssscfw.nandemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ssscfw.nandemod.event.*;
import com.ssscfw.nandemod.proxy.CommonProxy;
import com.ssscfw.nandemod.proxy.InitProxy;
import com.ssscfw.nandemod.utils.Recipe;
import com.ssscfw.nandemod.utils.RegisterSlashBlade;
import com.ssscfw.nandemod.utils.RegisterUtils;

@Mod(modid = NandeMod.MODID, version = NandeMod.VERSION, dependencies = "required-after:flammpfeil.slashblade;required-after:EnchantChanger")
public class NandeMod
{
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "nandemod";
    public static final String VERSION = "1.0";
    public static CreativeTabs tabNandemod = new NandeModTab("NandeMod");
    @Mod.Instance(NandeMod.MODID)
    public static NandeMod instance;
    @SidedProxy(clientSide = "com.ssscfw.nandemod.client.ClientProxy", serverSide = "com.ssscfw.nandemod.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new PlayerEvent());
        InitProxy.proxy.initializeItemRenderer();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(proxy);
        FMLCommonHandler.instance().bus().register(new TickEvent());
        RegisterUtils.registerEnchantments();
        RegisterUtils.registerItems();
        RegisterUtils.registerTileEntity();
        RegisterUtils.registerBlocks();
        RegisterSlashBlade.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(NandeMod.instance, proxy);
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
        
        
    }

    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt)
    {
        Recipe.init();
    }
}

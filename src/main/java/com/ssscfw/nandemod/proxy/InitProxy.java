package com.ssscfw.nandemod.proxy;

import cpw.mods.fml.common.SidedProxy;

public class InitProxy {
	@SidedProxy(clientSide = "com.ssscfw.nandemod.proxy.InitProxyClient", serverSide = "com.ssscfw.nandemod.proxy.InitProxy")
	public static InitProxy proxy;


	public void initializeItemRenderer() {}

}
package com.ssscfw.nandemod.block;

import com.ssscfw.nandemod.NandeMod;
import com.ssscfw.nandemod.tile.EcTileEntityMaterializer2;
import com.ssscfw.nandemod.utils.Constants;

import ak.EnchantChanger.ExtendedPlayerData;
import ak.EnchantChanger.block.EcBlockMaterializer;
import ak.EnchantChanger.utils.ConfigurationUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMaterializer2 extends EcBlockMaterializer {
    @SideOnly(Side.CLIENT)
    private IIcon top;
    @SideOnly(Side.CLIENT)
    private IIcon side;
    @SideOnly(Side.CLIENT)
    private IIcon bottom;

    public BlockMaterializer2() {
        super();
        setCreativeTab(NandeMod.tabNandemod);
        setBlockName("blockMaterializer2");
        setBlockTextureName("nandemod:EnchantChanger2-top");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        String domain = "nandemod:";
        this.top = par1IconRegister.registerIcon(domain + "EnchantChanger2-top");
        this.side = par1IconRegister.registerIcon(domain + "EnchantChanger2-side");
        this.bottom = par1IconRegister.registerIcon(domain + "EnchantChanger2-bottom");
    }

    @Override
    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (ConfigurationUtils.difficulty < 2 || this.checkCost(par5EntityPlayer)) {
            par5EntityPlayer.openGui(NandeMod.instance, Constants.GUI_ID_MATERIALIZER2, par1World, par2, par3, par4);
        }

        ExtendedPlayerData.get(par5EntityPlayer).setLimitGaugeValue(0);
        return true;
   }

    private boolean checkCost(EntityPlayer player) {
        int expLv = player.experienceLevel;
        if (expLv >= ConfigurationUtils.enchantChangerCost) {
            player.addExperienceLevel(-ConfigurationUtils.enchantChangerCost);
            return true;
        }
        player.addChatComponentMessage(new ChatComponentText(String.format("Need %dLevel to open EnchantChanger", ConfigurationUtils.enchantChangerCost)));
        return false;
    }

    @Override
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == 0 ? this.bottom : (par1 == 1 ? this.top : this.side);
    }

    @Override
    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
        par1World.removeTileEntity(par2, par3, par4);
    }

    @Override
    public TileEntity func_149915_a(World var1, int var2) {
        return new EcTileEntityMaterializer2();
    }
    
}

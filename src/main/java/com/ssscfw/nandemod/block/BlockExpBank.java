package com.ssscfw.nandemod.block;

import java.util.Random;

import javax.swing.text.html.parser.Entity;

import com.ssscfw.nandemod.NandeMod;
import com.ssscfw.nandemod.tile.TileExpBank;
import com.ssscfw.nandemod.utils.Constants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExpBank extends BlockContainer {

    public BlockExpBank() {
        super(Material.iron);
        setBlockName("expBank");
        setBlockTextureName("nandemod:exp_bank");
        setHardness(2.0F);
        setResistance(1000.0F);
        setStepSound(soundTypeMetal);
        setCreativeTab(NandeMod.tabNandemod);
    }

    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			player.openGui(NandeMod.instance, Constants.GUI_ID_EXP_BANK, world, x, y, z);
		}
		return true;
	}

    @Override
	public void breakBlock(World world, int x, int y, int z, Block block, int notice) {
		TileExpBank expbank = (TileExpBank)world.getTileEntity(x, y, z);
		if (expbank != null) {
			int level = expbank.getLevel();
            if (level > 0) {
                EntityPlayer player = world.getClosestPlayer(x, y, z, 64.0D);
                player.addExperience(level);
            }
		}
        super.breakBlock(world, x, y, z, block, notice);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileExpBank();
    }


}

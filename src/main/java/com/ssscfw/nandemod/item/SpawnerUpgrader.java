package com.ssscfw.nandemod.item;

import com.ssscfw.nandemod.NandeMod;
import com.ssscfw.nandemod.tile.TileEntityUpgradeSpawner;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class SpawnerUpgrader extends Item {
    public SpawnerUpgrader() {
        String name = "spawner_upgrader";
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(64);
        setCreativeTab(NandeMod.tabNandemod);
        GameRegistry.registerItem(this, name);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);
        if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            int x = mop.blockX;
            int y = mop.blockY;
            int z = mop.blockZ;
            if (world.getBlock(x, y, z) instanceof net.minecraft.block.BlockMobSpawner)
            {
                Block block = world.getBlock(x, y, z);
                if (block.hasTileEntity(world.getBlockMetadata(x, y, z)))
                {
                    TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
                    NBTTagCompound tag = new NBTTagCompound();
                    spawner.writeToNBT(tag);
                    TileEntityUpgradeSpawner upgradeSpawner = new TileEntityUpgradeSpawner();
                    tag.setShort("Delay", (short) 1);
                    tag.setShort("MinSpawnDelay", (short) Math.max(1, tag.getShort("MinSpawnDelay") / 3 - 40));
                    tag.setShort("MaxSpawnDelay", (short) Math.max(1, tag.getShort("MaxSpawnDelay") / 3 - 100));
                    tag.setShort("SpawnCount", (short) Math.max(1, tag.getShort("SpawnCount") * 2));
                    tag.setShort("RequiredPlayerRange", (short) Math.max(1, tag.getShort("RequiredPlayerRange") * 2));
                    tag.setShort("MaxNearbyEntities", (short) Math.max(1, tag.getShort("MaxNearbyEntities") * 2));
                    tag.setShort("Grade", (short) (tag.getShort("Grade") + 1));
                    upgradeSpawner.readFromNBT(tag);
                    if (upgradeSpawner.getGrade() > 1) {
                        if (!world.isRemote) {
                            player.addChatMessage(new net.minecraft.util.ChatComponentText("§cこのスポナーはアップグレード済みです。"));
                        }
                        return item;
                    }
                    world.setTileEntity(x, y, z, upgradeSpawner);
                    if (!world.isRemote) {
                        player.addChatMessage(new net.minecraft.util.ChatComponentText("§aスポナーがアップグレードされました！"));
                    }
                    item.stackSize--;
                }
                else
                {
                    if (!world.isRemote) {
                        player.addChatMessage(new net.minecraft.util.ChatComponentText("§cこのブロックは有効なスポナーではありません！"));
                    }
                }
            }
            else
            {
                if (!world.isRemote) {
                    player.addChatMessage(new net.minecraft.util.ChatComponentText("§cスポナーを右クリックしてください。"));
                }
            }
        }
        return item;
    }


    
    
}

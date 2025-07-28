package com.ssscfw.nandemod.tile;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class TileEntityUpgradeSpawner extends TileEntityMobSpawner {
    // This class can be used to add additional functionality to the upgraded spawner
    public TileEntityUpgradeSpawner() {
        super();
        
    }

    private final UpgradeSpawnerBaseLogic field_145882_a = new UpgradeSpawnerBaseLogic() 
    {
        public void func_98267_a(int p_98267_1_)
        {
            TileEntityUpgradeSpawner.this.worldObj.addBlockEvent(TileEntityUpgradeSpawner.this.xCoord, TileEntityUpgradeSpawner.this.yCoord, TileEntityUpgradeSpawner.this.zCoord, Blocks.mob_spawner, p_98267_1_, 0);
        }
        public World getSpawnerWorld()
        {
            return TileEntityUpgradeSpawner.this.worldObj;
        }
        public int getSpawnerX()
        {
            return TileEntityUpgradeSpawner.this.xCoord;
        }
        public int getSpawnerY()
        {
            return TileEntityUpgradeSpawner.this.yCoord;
        }
        public int getSpawnerZ()
        {
            return TileEntityUpgradeSpawner.this.zCoord;
        }
        public void setRandomEntity(MobSpawnerBaseLogic.WeightedRandomMinecart p_98277_1_)
        {
            super.setRandomEntity(p_98277_1_);

            if (this.getSpawnerWorld() != null)
            {
                this.getSpawnerWorld().markBlockForUpdate(TileEntityUpgradeSpawner.this.xCoord, TileEntityUpgradeSpawner.this.yCoord, TileEntityUpgradeSpawner.this.zCoord);
            }
        }
    };
    public short getGrade() {
        return this.field_145882_a.getGrade();
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        this.field_145882_a.readFromNBT(p_145839_1_);
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);
        this.field_145882_a.writeToNBT(p_145841_1_);
    }

    @Override
    public void updateEntity()
    {
        this.field_145882_a.updateSpawner();
        super.updateEntity();
    }

    @Override
    public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_)
    {
        return this.field_145882_a.setDelayToMin(p_145842_1_) ? true : super.receiveClientEvent(p_145842_1_, p_145842_2_);
    }

    @Override
    public MobSpawnerBaseLogic func_145881_a()
    {
        return this.field_145882_a;
    }
}

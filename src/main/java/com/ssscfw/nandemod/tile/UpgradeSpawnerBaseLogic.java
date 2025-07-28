package com.ssscfw.nandemod.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;

public abstract class UpgradeSpawnerBaseLogic extends MobSpawnerBaseLogic {
    private short grade = 0;

    @Override
    public void updateSpawner() {
        // Custom logic for the upgraded spawner can be added here
        super.updateSpawner();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        grade = nbt.getShort("Grade");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("Grade", grade);
    }

    public short getGrade() {
        return grade;
    }
    
}

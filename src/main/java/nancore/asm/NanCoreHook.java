package nancore.asm;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import ic2.core.block.machine.tileentity.TileEntityElecMachine;
import ic2.core.block.machine.tileentity.TileEntityMatter;
import ic2.core.energy.EnergyNetLocal;
import ic2.core.ExplosionIC2;

public class NanCoreHook {
    public static void NanFurnaceHook(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityFurnace) {
            TileEntityFurnace furnace = ((TileEntityFurnace) tileEntity);
            if (furnace.furnaceBurnTime > 0) {
                System.out.println("Furnace is burning!");
            }
        }
    }

    public static void NanInjectEnergyHook(TileEntityElecMachine machine) {
        machine.maxInput = 2147483647; // Set max input to a very high value
    }

    public static void NanGetPowerFromTierHook(TileEntityElecMachine machine) {
        machine.maxInput = 2147483647; // Set max input to a very high value
    }
}

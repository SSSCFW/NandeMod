package com.ssscfw.nandemod.event;

import com.ssscfw.nandemod.utils.Alldata;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TickEvent {
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        if (event.phase != PlayerTickEvent.Phase.END || event.side.isClient()) {
            return;
        }
        EntityPlayer player = event.player;
        if (player.ticksExisted % 20 == 0) {
            for (ItemStack itemStack : player.inventory.mainInventory) {
                if (itemStack != null && itemStack.isItemEnchanted()) {
                    int level = EnchantmentHelper.getEnchantmentLevel(Alldata.enchantments.get("repair").effectId, itemStack);
                    if (level > 0 && itemStack.getMaxItemUseDuration() > 0 && itemStack.getItemDamage() > 0) {
                        itemStack.setItemDamage(itemStack.getItemDamage() - level);
                    }
                }
            }
        }
    }
}

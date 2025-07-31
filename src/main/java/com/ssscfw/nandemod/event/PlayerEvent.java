package com.ssscfw.nandemod.event;

import java.util.List;
import java.util.Random;

import com.ssscfw.nandemod.utils.Alldata;

import ak.EnchantChanger.entity.EcEntityApOrb;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerEvent {
    private Random rand = new Random();

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            ItemStack heldItem = player.getHeldItem();
            EntityLivingBase livingEntity = event.entityLiving;
            int level = EnchantmentHelper.getEnchantmentLevel(Alldata.enchantments.get("experience").effectId, heldItem);
            int apLevel = EnchantmentHelper.getEnchantmentLevel(Alldata.enchantments.get("apdrop").effectId, heldItem);
            if (heldItem != null) {
                if (level > 0) {
                    int exp = level * 5;
                    EntityXPOrb xpOrb = new EntityXPOrb(livingEntity.worldObj, livingEntity.posX, livingEntity.posY, livingEntity.posZ, exp);
                    livingEntity.worldObj.spawnEntityInWorld(xpOrb);
                }
                if (apLevel > 0) {
                    int ap = rand.nextInt(apLevel) + 1;
                    EcEntityApOrb apOrb = new EcEntityApOrb(livingEntity.worldObj, livingEntity.posX, livingEntity.posY, livingEntity.posZ, ap);
                    livingEntity.worldObj.spawnEntityInWorld(apOrb);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public void onPlayerAttack(LivingAttackEvent event) {
        if (event.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.source.getEntity();
            ItemStack heldItem = player.getHeldItem();
            EntityLivingBase livingEntity = event.entityLiving;
            int level = EnchantmentHelper.getEnchantmentLevel(Alldata.enchantments.get("range_attack").effectId, heldItem);
            if (heldItem != null && level > 0 && !heldItem.getTagCompound().hasKey("RangeAttack", NBT.TAG_BYTE)) {
                heldItem.getTagCompound().setBoolean("RangeAttack", true);
                List<EntityLivingBase> entities = livingEntity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, livingEntity.boundingBox.expand(level*1.5, level+1, level*1.5));
                for (EntityLivingBase entity : entities) {
                    if (entity != livingEntity && entity != player && !(entity instanceof EntityPlayer)) {
                        entity.attackEntityFrom(event.source, event.ammount);
                    }
                }
                heldItem.getTagCompound().removeTag("RangeAttack");
            }
        }
    }


}

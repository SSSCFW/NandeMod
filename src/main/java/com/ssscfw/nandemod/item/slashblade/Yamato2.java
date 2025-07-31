package com.ssscfw.nandemod.item.slashblade;

import com.ssscfw.nandemod.NandeMod;
import com.ssscfw.nandemod.utils.Alldata;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.EntityDrive;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class Yamato2 extends ItemSlashBladeNamed {
    ToolMaterial material;

    public Yamato2() {
        this(ToolMaterial.IRON, 7.0F);
        String name = "slashblade_yamato2";
        GameRegistry.registerItem(this, name);
        {

            ItemStack customblade = new ItemStack(this,1,0);
            NBTTagCompound tag = new NBTTagCompound();
            customblade.setTagCompound(tag);

            customblade.addEnchantment(Enchantment.thorns, 5);
            customblade.addEnchantment(Enchantment.featherFalling, 5);
            customblade.addEnchantment(Enchantment.power, 15);
            customblade.addEnchantment(Enchantment.punch, 2);
            customblade.addEnchantment(Alldata.enchantments.get("repair"), 1);
            tag.setBoolean("Unbreakable", true);
            ItemSlashBladeNamed.CurrentItemName.set(tag, name);
            ItemSlashBladeNamed.IsDefaultBewitched.set(tag, true);
            ItemSlashBladeNamed.CustomMaxDamage.set(tag, 2);
            ItemSlashBlade.setBaseAttackModifier(tag, 6 + Item.ToolMaterial.EMERALD.getDamageVsEntity());
            ItemSlashBlade.TextureName.set(tag,"named/yamato");
            ItemSlashBlade.ModelName.set(tag,"named/yamato");
            ItemSlashBlade.SpecialAttackType.set(tag, 0);
            ItemSlashBlade.StandbyRenderType.set(tag, 1);
            ItemSlashBlade.KillCount.set(tag, 1000);
            ItemSlashBlade.ProudSoul.set(tag, 3000);

            GameRegistry.registerCustomItemStack(name, customblade);
            ItemSlashBladeNamed.NamedBlades.add(NandeMod.MODID + ":" + name);
        }
    }

    public Yamato2(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
        super(par2EnumToolMaterial, baseAttackModifiers);
        material = par2EnumToolMaterial;

    }

    @Override
    public ItemStack func_77659_a(ItemStack sitem, World par2World, EntityPlayer par3EntityPlayer) {
        ItemStack item = super.func_77659_a(sitem, par2World, par3EntityPlayer);
        if (!par2World.isRemote) {
            int rank = StylishRankManager.getStylishRank(par3EntityPlayer);
            PotionEffect potionEffect = par3EntityPlayer.getActivePotionEffect(Potion.damageBoost);
            if (potionEffect == null || potionEffect.getAmplifier() < 0)
                return item;
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(item);
            boolean multihit = true;
            ItemSlashBlade blade = (ItemSlashBlade)item.getItem();
            float baseModif = blade.getBaseAttackModifiers(tag);
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, item);
            float magicDamage = baseModif;
            
            if(5 <= rank)
                magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.7f + (level / 5.0f));

            float speed = 1.4f;
            int lifetime = 40;
            ComboSequence setCombo = getComboSequence(tag);
            EntityDrive entityDrive = new EntityDrive(par2World, par3EntityPlayer, magicDamage, multihit, 90.0f - setCombo.swingDirection);
            if (entityDrive != null) {
                entityDrive.setInitialSpeed(speed);
                entityDrive.setLifeTime(lifetime);
                par2World.spawnEntityInWorld(entityDrive);
                
            }
        }
        return item;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        if(getSwordType(par1ItemStack).contains(SwordType.Bewitched))
            return EnumRarity.epic;
        else
            return EnumRarity.rare;
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, damage);
        int maxDamage = this.getMaxDamage(stack);
        if (damage >= maxDamage && maxDamage > 0) {
            setDamage(stack, 0);
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return true;
    }
}

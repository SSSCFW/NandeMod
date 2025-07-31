package com.ssscfw.nandemod.item.slashblade;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.flammpfeil.slashblade.EntityDrive;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Venus extends ItemSlashBladeNamed {
    ToolMaterial material;

    public Venus() {
        this(ToolMaterial.IRON, 7.0F);
        String name = "slashblade_venus";
        GameRegistry.registerItem(this, name);
        {
            ItemStack customblade = new ItemStack(this, 1,0);
            NBTTagCompound tag = new NBTTagCompound();
            customblade.setTagCompound(tag);

            customblade.addEnchantment(Enchantment.power,5);
            customblade.addEnchantment(Enchantment.featherFalling,5);
            ItemSlashBladeNamed.CurrentItemName.set(tag, name);
            ItemSlashBladeNamed.IsDefaultBewitched.set(tag,true);
            ItemSlashBladeNamed.CustomMaxDamage.set(tag, 400);
            ItemSlashBlade.TextureName.set(tag,"venus/tex");
            ItemSlashBlade.ModelName.set(tag,"venus/model");
            ItemSlashBlade.SpecialAttackType.set(tag, 1);
            ItemSlashBlade.StandbyRenderType.set(tag, 3);
            ItemSlashBlade.KillCount.set(tag, 1000);
            ItemSlashBlade.ProudSoul.set(tag, 3000);

            GameRegistry.registerCustomItemStack(name, customblade);
            ItemSlashBladeNamed.NamedBlades.add(NandeMod.MODID + ":" + name);
        }
    }

    public Venus(ToolMaterial par2EnumToolMaterial, float baseAttackModifiers) {
        super(par2EnumToolMaterial, baseAttackModifiers);
        material = par2EnumToolMaterial;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        if(getSwordType(par1ItemStack).contains(SwordType.Bewitched))
            return EnumRarity.epic;
        else
            return EnumRarity.rare;
    }

    @Override
    public ItemStack func_77659_a(ItemStack sitem, World par2World, EntityPlayer par3EntityPlayer) {
        ItemStack item = super.func_77659_a(sitem, par2World, par3EntityPlayer);
        if (!par2World.isRemote) {
            int rank = StylishRankManager.getStylishRank(par3EntityPlayer);
            if (rank < 4) {
                return item;
            }
            NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(item);
            boolean multihit = true;
            ItemSlashBlade blade = (ItemSlashBlade)item.getItem();
            float baseModif = blade.getBaseAttackModifiers(tag);
            int level = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, item);
            float magicDamage = baseModif;
            
            if(5 <= rank)
                magicDamage += ItemSlashBlade.AttackAmplifier.get(tag) * (0.5f + (level / 5.0f));

            float speed = 1.5f;
            int lifetime = 20;
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

    @SuppressWarnings("rawtypes")
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs,List par3List) {}

    @Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return true;
    }
}

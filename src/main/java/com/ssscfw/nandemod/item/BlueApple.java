package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlueApple extends ItemFood {
    public BlueApple() {
        super(7, 1.4F, false);
        String name = "blue_apple";
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(64);
        setAlwaysEdible();
        setPotionEffect(Potion.regeneration.id, 5, 1, 1.0F);
        setCreativeTab(NandeMod.tabNandemod);
        GameRegistry.registerItem(this, name);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemstack)
    {
        return itemstack.getItemDamage() > 0;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (itemstack.getItemDamage() == 0) {
            list.add("§7一定時間の再生効果と攻撃力上昇効果を得る。");
        } else {
            list.add("§7強力な再生効果と攻撃力上昇効果を得る。");
            list.add("§7さらに、耐性と水中呼吸も付与される。");
        }
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack itemstack)
    {
        return itemstack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
    }

    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20*60, 0));
        }

        if (itemstack.getItemDamage() > 0)
        {
            if (!world.isRemote)
            {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 2));
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
                player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 6000, 0));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 6000, 2));
            }
        }
        else
        {
            super.onFoodEaten(itemstack, world, player);
        }
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list)
    {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }
}

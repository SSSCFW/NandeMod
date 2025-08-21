package com.ssscfw.nandemod.item;

import java.util.List;

import com.ssscfw.nandemod.NandeMod;

import ak.EnchantChanger.entity.EcEntityApOrb;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MagnetAll extends Item {
    protected IIcon[] iicon;
    
    String name = "magnet_all";

    public MagnetAll() {
        setUnlocalizedName(name);
        setTextureName("nandemod:" + name);
        setMaxStackSize(1);
        setCreativeTab(NandeMod.tabNandemod);
        GameRegistry.registerItem(this, name);
    }

    @Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.iicon = new IIcon[2];
		this.iicon[0] = register.registerIcon("nandemod:" + name);
        this.iicon[1] = register.registerIcon("nandemod:" + name + "_toggle");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return iicon[meta];
	}

    @Override
	public int getMetadata(int meta) {
		return meta;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean p_77624_4_) {
        NBTTagCompound tag = itemstack.getTagCompound();
        boolean toggle = false;
        int range = 5;
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        else {
            toggle = tag.getBoolean("toggle");
            range = tag.getInteger("range");
        }
        list.add("§eスニーク + 右クリック§7で自動回収の切り替え");
        list.add("§7自動回収: " + (toggle ? "§aOn" : "§cOff"));
        list.add("§7範囲(半径): " + range);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        NBTTagCompound tag = item.getTagCompound();
        boolean toggle = true;
        int range = 5;
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        else {
            toggle = !tag.getBoolean("toggle");
            range = tag.getInteger("range");
            if (range == 0) {
                tag.setInteger("range", 5);
                item.setTagCompound(tag);
            }
        }
        if (player.isSneaking()) {
            tag.setBoolean("toggle", toggle);
            item.setTagCompound(tag);
            item.setItemDamage(toggle ? 1 : 0);
            if (!world.isRemote) {
                player.addChatMessage(new net.minecraft.util.ChatComponentText("§e磁石自動回収 : " + (toggle ? "§a有効" : "§c無効")));
            }
            return item;
        }

        range++;
        if (range > 21) {
            range = 5;
        }
        tag.setInteger("range", range);
        item.setTagCompound(tag);
        if (!world.isRemote) {
            player.addChatMessage(new net.minecraft.util.ChatComponentText("§e磁石範囲(半径) : §a" + range));
        }

        pickup(player, world, range);
        return item;
    }

    @Override
    public void onUpdate(ItemStack item, World world, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof EntityPlayer) {
            NBTTagCompound tag = item.getTagCompound();
            boolean toggle = false;
            int range = 5;
            if (tag == null) {
                tag = new NBTTagCompound();
            }
            else {
                toggle = tag.getBoolean("toggle");
                range = tag.getInteger("range");
            }
            if (toggle) {
                EntityPlayer player = (EntityPlayer) entity;
                pickup(player, world, range);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void pickup(EntityPlayer player, World world, int range) {
        int radius = range;
        List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(radius, radius, radius));
        for (EntityItem dropitem : items) {
            dropitem.delayBeforeCanPickup = 0;
            dropitem.setPosition(player.posX, player.posY, player.posZ);
            dropitem.onCollideWithPlayer(player);
        }
        List<EntityXPOrb> xpOrbs = world.getEntitiesWithinAABB(EntityXPOrb.class, player.boundingBox.expand(radius, radius, radius));
        for (EntityXPOrb xpOrb : xpOrbs) {
            xpOrb.setPosition(player.posX, player.posY, player.posZ);
            xpOrb.onCollideWithPlayer(player);
            player.xpCooldown = 0;
        }
        List<EcEntityApOrb> apOrbs = world.getEntitiesWithinAABB(EcEntityApOrb.class, player.boundingBox.expand(radius, radius, radius));
        for (EcEntityApOrb apOrb : apOrbs) {
            apOrb.setPosition(player.posX, player.posY, player.posZ);
            apOrb.func_70100_b_(player);
            player.xpCooldown = 0;
        }
    }
}

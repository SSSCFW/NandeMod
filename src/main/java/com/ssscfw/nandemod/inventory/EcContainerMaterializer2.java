package com.ssscfw.nandemod.inventory;

import java.util.ArrayList;
import java.util.List;

import ak.EnchantChanger.EnchantChanger;
import net.minecraftforge.common.util.Constants;
import ak.EnchantChanger.api.EnchantmentLvPair;
import ak.EnchantChanger.inventory.EcContainerMaterializer;
import ak.EnchantChanger.item.EcItemMateria;
import ak.EnchantChanger.utils.ConfigurationUtils;
import ak.EnchantChanger.utils.EnchantmentUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class EcContainerMaterializer2 extends EcContainerMaterializer {
    private static ArrayList<Integer> magicDmg = new ArrayList<>();
    static {
        magicDmg.add(ConfigurationUtils.idEnchantmentMeteor);
        magicDmg.add(ConfigurationUtils.idEnchantmentHoly);
        magicDmg.add(ConfigurationUtils.idEnchantmentTelepo);
        magicDmg.add(ConfigurationUtils.idEnchantmentFloat);
        magicDmg.add(ConfigurationUtils.idEnchantmentThunder);
    }

    private ArrayList<EnchantmentLvPair> itemEnchantmentLvPair = new ArrayList<>();
    private ArrayList<EnchantmentLvPair> enchantmentRemoveData = new ArrayList<>();
    private ArrayList<Integer> enchantmentList = new ArrayList<>();
    private ArrayList<Integer> enchantmentLevelList = new ArrayList<>();
    private ArrayList<Byte> magicList = new ArrayList<>();
    private ArrayList<Byte> magicAddList = new ArrayList<>();

    public EcContainerMaterializer2(World par1world, InventoryPlayer inventoryPlayer) {
        super(par1world, inventoryPlayer);
    }

    @Override
    public void onCraftMatrixChanged(IInventory par1IInventory) {
        ItemStack baseItem = this.materializeSource.getStackInSlot(0);
        if (baseItem != null) {
            /*if (EnchantChanger.loadMTH && baseItem.getItem() instanceof ItemMultiToolHolder) {
                return;
            }*/
            String tagName = EnchantmentUtils.getTagName(baseItem);
            NBTTagList enchTagList = (baseItem.hasTagCompound()) ? baseItem.getTagCompound().getTagList(tagName, Constants.NBT.TAG_COMPOUND) : null;

            if (EnchantmentUtils.hasMagic(baseItem)) {
                for (byte b : EnchantmentUtils.getMagic(baseItem)) {
                    magicList.add(b);
                }
            }

            ItemStack result = baseItem.copy();
            if (result.hasTagCompound()) {
                result.getTagCompound().removeTag(tagName);
                result.getTagCompound().removeTag("ApList");
            }
            if (enchTagList != null && enchTagList.tagCount() > 0) {
                int var1, var2;
                for (int i = 0; i < enchTagList.tagCount(); ++i)
                    if (enchTagList.getCompoundTagAt(i).getShort("lvl") > 0) {
                        enchTagList.getCompoundTagAt(i).setInteger("ap", 0);
                        var1 = enchTagList.getCompoundTagAt(i).getShort("id");
                        var2 = enchTagList.getCompoundTagAt(i).getShort("lvl");
                        this.itemEnchantmentLvPair.add(new EnchantmentLvPair((var1 >= 0 && var1 < Enchantment.enchantmentsList.length) ? Enchantment.enchantmentsList[var1] : null, var2));
                        if (i >= 8) {
                            EnchantmentUtils.addEnchantmentToItem(result, itemEnchantmentLvPair.get(i).enchantment, itemEnchantmentLvPair.get(i).lv);
                        }
                    }
            }
            if (this.checkMateriafromSlot(materializeSource)) {
                for (int i = 1; i < this.materializeSource.getSizeInventory(); i++) {
                    ItemStack materiaitem = this.materializeSource.getStackInSlot(i);
                    if (materiaitem == null) {
                        continue;
                    }
                    if (materiaitem.getItemDamage() == 0 && materiaitem.isItemEnchanted()) {
                        int enchLv = EnchantmentUtils.enchLv(materiaitem);
                        Enchantment enchKind = EnchantmentUtils.enchKind(materiaitem);

                        /*if (!EnchantmentUtils.isEnchantmentValid(enchKind, baseItem) || !EnchantmentUtils.checkLvCap(materiaitem)) {
                            for (int i1 = 0; i1 < ResultSlotNum; i1++) {
                                this.materializeResult.setInventorySlotContents(i1, null);
                            }
                            this.enchantmentList.clear();
                            this.enchantmentLevelList.clear();
                            this.itemEnchantmentLvPair.clear();
                            return;
                        }*/

                        for (EnchantmentLvPair data : this.itemEnchantmentLvPair) {
                            if (!data.enchantment.canApplyTogether(enchKind)) {
                                this.enchantmentRemoveData.add(data);
                            }
                        }

                        this.itemEnchantmentLvPair.removeAll(this.enchantmentRemoveData);
                        this.enchantmentRemoveData.clear();

                        if (!this.enchantmentList.contains(enchKind.effectId)) {
                            this.enchantmentList.add(enchKind.effectId);
                            this.enchantmentLevelList.add(enchLv);
                        }
                    } else {
                        if (!magicList.contains((byte) materiaitem.getItemDamage())) {
                            this.magicAddList.add((byte) materiaitem.getItemDamage());
                        }
                    }
                }

                magicList.addAll(magicAddList);
                byte[] magic = new byte[magicList.size()];
                for (int i = 0; i < magicList.size(); i++) {
                    magic[i] = magicList.get(i);
                }
                EnchantmentUtils.setMagic(result, magic);

                for (EnchantmentLvPair data : itemEnchantmentLvPair) {
                    EnchantmentUtils.addEnchantmentToItem(result, data.enchantment, data.lv);
                }
                this.itemEnchantmentLvPair.clear();
                for (int i2 = 0; i2 < this.enchantmentList.size(); i2++) {
                    EnchantmentUtils.addEnchantmentToItem(result, (this.enchantmentList.get(i2) < Enchantment.enchantmentsList.length) ? Enchantment.enchantmentsList[this.enchantmentList.get(i2)] : null, this.enchantmentLevelList.get(i2));
                }

                result = EnchantmentUtils.getBookResult(result, enchantmentList);
                this.materializeResult.setInventorySlotContents(0, result);

                for (int i = 1; i < ResultSlotNum; i++) {
                    this.materializeResult.setInventorySlotContents(i, null);
                }
            } else if (enchTagList != null && enchTagList.tagCount() > 0) {//extract enchantment from Item
                int endIndex = itemEnchantmentLvPair.size() > 8 ? 8 : itemEnchantmentLvPair.size();
                List<EnchantmentLvPair> subList = itemEnchantmentLvPair.subList(0, endIndex);
                int slotIndex = 0;
                for (EnchantmentLvPair data : subList) {
                    if (data.enchantment == null) break;
                    int decreasedLv = EnchantmentUtils.getDecreasedLevel(baseItem, data.lv);
                    int damage = this.setMateriaDmgfromEnch(data.enchantment.effectId);
                    if (decreasedLv > 0) {
                        ItemStack materia = new ItemStack(EnchantChanger.itemMateria, 1, damage);
                        EnchantmentUtils.addEnchantmentToItem(materia, data.enchantment, decreasedLv);
                        this.materializeResult.setInventorySlotContents(slotIndex + 1, materia);
                    } else {
                        this.materializeResult.setInventorySlotContents(slotIndex + 1, null);
                    }
                    slotIndex++;
                }
                result = EnchantmentUtils.getBookResult(result, itemEnchantmentLvPair.subList(endIndex, itemEnchantmentLvPair.size()));
                this.materializeResult.setInventorySlotContents(0, result);
            } else if (!magicList.isEmpty()) {
                result.getTagCompound().removeTag("EnchantChanger|Magic");
                int slotIndex = 0;
                for (byte b : magicList) {
                    ItemStack materia = new ItemStack(EnchantChanger.itemMateria, 1, b);
                    this.materializeResult.setInventorySlotContents(slotIndex + 1, materia);
                    slotIndex++;
                    if (slotIndex > 8) break;
                }
                this.materializeResult.setInventorySlotContents(0, result);
            } else {
                for (int i = 0; i < ResultSlotNum; i++) {
                    this.materializeResult.setInventorySlotContents(i, null);
                }
            }
            this.enchantmentList.clear();
            this.enchantmentLevelList.clear();
            this.itemEnchantmentLvPair.clear();
            this.magicAddList.clear();
            this.magicList.clear();
        }
    }

    private boolean checkMateriafromSlot(IInventory source) {
        boolean ret = false;
        for (int i = 0; i < source.getSizeInventory(); i++) {
            if (source.getStackInSlot(i) != null && source.getStackInSlot(i).getItem() instanceof EcItemMateria)
                ret = true;
        }
        return ret;
    }

    @Deprecated
    private int setMateriaDmgfromEnch(int enchID) {
        if (magicDmg.contains(enchID))
            return magicDmg.indexOf(enchID) + 1;
        else
            return 0;
    }

}

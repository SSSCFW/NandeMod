package com.ssscfw.nandemod.client.gui;
import java.util.HashMap;
import java.util.jar.Pack200.Packer;

import com.ssscfw.nandemod.NandeMod;
import com.ssscfw.nandemod.network.ExpBankPacket;
import com.ssscfw.nandemod.network.PacketHandler;
import com.ssscfw.nandemod.tile.TileExpBank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiExpBank extends GuiScreen {
    private static final ResourceLocation gui = new ResourceLocation(NandeMod.MODID + ":" + "textures/gui/expbank.png");
    private int xSize = 176;
    private int ySize = 166;
    private int x;
    private int y;
    private EntityPlayer player;
    private TileExpBank tile;
    private static HashMap<Integer, Integer> buttonLevels = new HashMap<>();

    public GuiExpBank(EntityPlayer player, TileExpBank tile) {
        super();
        this.player = player;
        this.tile = tile;
        x = (width - xSize) / 2;
        y = (height - ySize) / 2;

        buttonLevels.put(0, 1000);
        buttonLevels.put(1, 100);
        buttonLevels.put(2, 10);
        buttonLevels.put(3, -10);
        buttonLevels.put(4, -100);
        buttonLevels.put(5, -1000);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        x = (width - xSize) / 2;
        y = (height - ySize) / 2;
        this.buttonList.add(new GuiButton(0, x, y+10, "+1000"));
        this.buttonList.add(new GuiButton(1, x, y+30, "+100"));
        this.buttonList.add(new GuiButton(2, x, y+50, "+10"));
        this.buttonList.add(new GuiButton(3, x, y+70, "-10"));
        this.buttonList.add(new GuiButton(4, x, y+90, "-100"));
        this.buttonList.add(new GuiButton(5, x, y+110, "-1000"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
      
        //Minecraft.getMinecraft().getTextureManager().bindTexture(gui);
        //x = (width - xSize) / 2;
        //y = (height - ySize) / 2;
        //drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

        fontRendererObj.drawString("§eExp: " + tile.getLevel(), x + 8, y + 24, 1);

    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (buttonLevels.containsKey(button.id)) {
            int levelChange = buttonLevels.get(button.id);
            // パケットをサーバーに送信
            PacketHandler.INSTANCE.sendToServer(new ExpBankPacket(tile.xCoord, tile.yCoord, tile.zCoord, levelChange));
            if (levelChange > 0) {
                levelChange = Math.min(levelChange, player.experienceLevel);
            } else {
                levelChange = Math.max(levelChange, -tile.getLevel());
            }
            
            tile.addLevel(levelChange);
            player.experienceTotal -= levelChange;
            
        }
    }

    @Override
    protected void keyTyped(char c, int keycode) {
        if (keycode == 1 || keycode == Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode()) {
            Minecraft.getMinecraft().thePlayer.closeScreen();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawDefaultBackground() {
        super.drawDefaultBackground();
    }
}

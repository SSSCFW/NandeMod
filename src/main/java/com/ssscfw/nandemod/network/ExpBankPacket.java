package com.ssscfw.nandemod.network;

import com.ssscfw.nandemod.tile.TileExpBank;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class ExpBankPacket implements IMessage {
    private int x, y, z;
    private int levelChange;

    public ExpBankPacket() {}

    public ExpBankPacket(int x, int y, int z, int levelChange) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.levelChange = levelChange;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        levelChange = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeInt(levelChange);
    }

    public static class Handler implements IMessageHandler<ExpBankPacket, IMessage> {
        @Override
        public IMessage onMessage(ExpBankPacket message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            TileEntity tileEntity = player.worldObj.getTileEntity(message.x, message.y, message.z);
            
            if (tileEntity instanceof TileExpBank) {
                TileExpBank tile = (TileExpBank) tileEntity;
                
                int levelChange = message.levelChange;
                if (levelChange > 0) {
                    levelChange = Math.min(levelChange, player.experienceLevel);
                } else {
                    levelChange = Math.max(levelChange, -tile.getLevel());
                }
                
                tile.addLevel(levelChange);
                //player.addExperience(-levelChange);
                player.experienceTotal -= levelChange;
            }
            
            return null;
        }
    }
}
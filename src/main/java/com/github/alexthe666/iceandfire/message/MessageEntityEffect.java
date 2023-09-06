package com.github.alexthe666.iceandfire.message;

import com.github.alexthe666.iceandfire.api.IEntityEffectCapability;
import com.github.alexthe666.iceandfire.api.InFCapabilities;
import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageEntityEffect extends AbstractMessage<MessageEntityEffect> {
    private NBTTagCompound nbt;
    private int entityId;

    public MessageEntityEffect() { }

    public MessageEntityEffect(NBTBase nbt, int entityId) {
        this.nbt = (NBTTagCompound) nbt;
        this.entityId = entityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.nbt = ByteBufUtils.readTag(buf);
        this.entityId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.nbt);
        buf.writeInt(this.entityId);
    }

    public NBTTagCompound getNBT() {
        return this.nbt;
    }

    public int getEntityId() {
        return this.entityId;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientReceived(Minecraft client, MessageEntityEffect message, EntityPlayer player, MessageContext messageContext) {
        Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.getEntityId());
        if (entity != null) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                Capability<IEntityEffectCapability> def = InFCapabilities.ENTITY_EFFECT;
                IEntityEffectCapability capability = entity.getCapability(def, null);
                if (capability != null) {
                    def.getStorage().readNBT(def, capability, null, message.getNBT());
                }
            });
        }
    }

    @Override
    public void onServerReceived(MinecraftServer server, MessageEntityEffect message, EntityPlayer player, MessageContext messageContext) {}
}
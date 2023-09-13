package com.github.alexthe666.iceandfire.message;

import com.github.alexthe666.iceandfire.entity.util.DragonUtils;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageDragonArmor extends AbstractMessage<MessageDragonArmor> {

	public int dragonId;
	public int slot;
	public int armor_type;

	public MessageDragonArmor(int dragonId, EntityEquipmentSlot slot, int armor_type) {
		this.dragonId = dragonId;
		this.slot = DragonUtils.getDragonInvSlotFromEquipmentSlot(slot);
		this.armor_type = armor_type;
	}

	public MessageDragonArmor() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dragonId = buf.readInt();
		slot = buf.readInt();
		armor_type = buf.readInt();

	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dragonId);
		buf.writeInt(slot);
		buf.writeInt(armor_type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientReceived(Minecraft client, MessageDragonArmor message, EntityPlayer player, MessageContext messageContext) {

	}

	@Override
	public void onServerReceived(MinecraftServer server, MessageDragonArmor message, EntityPlayer player, MessageContext messageContext) {
		Entity entity = player.world.getEntityByID(message.dragonId);
		if (entity instanceof EntityDragonBase) {
			EntityDragonBase dragon = (EntityDragonBase) entity;
			dragon.setArmorInSlot(DragonUtils.getEquipmentSlotFromDragonInvSlot(message.slot), message.armor_type);
		}
	}
}
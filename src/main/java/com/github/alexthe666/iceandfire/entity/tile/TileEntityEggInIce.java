package com.github.alexthe666.iceandfire.entity.tile;

import com.github.alexthe666.iceandfire.IceAndFireConfig;
import com.github.alexthe666.iceandfire.entity.EntityDragonEgg;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.UUID;

public class TileEntityEggInIce extends TileEntity implements ITickable {

	public EnumDragonEgg type;
	public int age;
	public int ticksExisted;
	public UUID ownerUUID;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tag.setByte("Color", type == null ? (byte)0 : (byte)type.ordinal());
		tag.setByte("Age", (byte) age);
		tag.setString("OwnerUUID", ownerUUID == null ? "" : ownerUUID.toString());
		return super.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		type = EnumDragonEgg.values()[tag.getByte("Color")];
		age = tag.getByte("Age");
		if(tag.hasKey("OwnerUUID", 8)) {
			String s = tag.getString("OwnerUUID");
			if(!s.isEmpty()) ownerUUID = UUID.fromString(s);
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new SPacketUpdateTileEntity(pos, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	public void spawnEgg() {
		if(type != null) {
			EntityDragonEgg egg = new EntityDragonEgg(world);
			egg.setType(type);
			egg.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			egg.setOwnerId(this.ownerUUID);
			if(!world.isRemote) world.spawnEntity(egg);
		}
	}

	@Override
	public void update() {
		age++;
		if(age >= IceAndFireConfig.DRAGON_SETTINGS.dragonEggTime && type != null) {
			world.destroyBlock(pos, false);
			world.setBlockState(pos, Blocks.WATER.getDefaultState());
			EntityIceDragon dragon = new EntityIceDragon(world);
			dragon.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
			dragon.setVariant(type.meta % 4);
			dragon.setGender(world.rand.nextBoolean());
			dragon.setTamed(true);
			dragon.setHunger(50);
			dragon.setOwnerId(ownerUUID);
			if(!world.isRemote) world.spawnEntity(dragon);
		}
		ticksExisted++;
	}
}
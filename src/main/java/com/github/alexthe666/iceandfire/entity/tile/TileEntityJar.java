package com.github.alexthe666.iceandfire.entity.tile;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.IceAndFireConfig;
import com.github.alexthe666.iceandfire.core.ModSounds;
import com.github.alexthe666.iceandfire.entity.EntityPixie;
import com.github.alexthe666.iceandfire.message.MessageUpdatePixieHouse;
import com.github.alexthe666.iceandfire.message.MessageUpdatePixieHouseModel;
import com.github.alexthe666.iceandfire.message.MessageUpdatePixieJar;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import java.util.UUID;

public class TileEntityJar extends TileEntity implements ITickable {

	private static final float PARTICLE_WIDTH = 0.3F;
	private static final float PARTICLE_HEIGHT = 0.6F;
	public boolean hasPixie;
	public boolean hasProduced;
	public boolean tamedPixie;
	public UUID pixieOwnerUUID;
	public int pixieType;
	public int ticksExisted;
	public NonNullList<ItemStack> pixieItems = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
	public float rotationYaw;
	public float prevRotationYaw;

	public TileEntityJar(boolean empty) {
		this.hasPixie = !empty;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("HasPixie", hasPixie);
		compound.setInteger("PixieType", pixieType);
		compound.setBoolean("HasProduced", hasProduced);
		compound.setBoolean("TamedPixie", tamedPixie);
		if(pixieOwnerUUID != null) compound.setUniqueId("PixieOwnerUUID", pixieOwnerUUID);
		compound.setInteger("TicksExisted", ticksExisted);
		ItemStackHelper.saveAllItems(compound, this.pixieItems);
		return compound;
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new SPacketUpdateTileEntity(pos, 1, tag);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
		if(!world.isRemote) IceAndFire.NETWORK_WRAPPER.sendToAll(
				new MessageUpdatePixieHouseModel(
						pos.toLong(),
						packet.getNbtCompound().getInteger("PixieType")));
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		hasPixie = compound.getBoolean("HasPixie");
		pixieType = compound.getInteger("PixieType");
		hasProduced = compound.getBoolean("HasProduced");
		ticksExisted = compound.getInteger("TicksExisted");
		tamedPixie = compound.getBoolean("TamedPixie");
		pixieOwnerUUID = compound.getUniqueId("PixieOwnerUUID");
		this.pixieItems = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, pixieItems);
		super.readFromNBT(compound);
	}

	@Override
	public void update() {
		ticksExisted++;
		if(this.hasPixie) IceAndFire.PROXY.spawnParticle(
				"if_pixie",
				this.world,
				this.pos.getX() + 0.5F + (double)(this.world.rand.nextFloat() * PARTICLE_WIDTH * 2F) - (double) PARTICLE_WIDTH,
				this.pos.getY() + (double)(this.world.rand.nextFloat() * PARTICLE_HEIGHT),
				this.pos.getZ() + 0.5F + (double)(this.world.rand.nextFloat() * PARTICLE_WIDTH * 2F) - (double) PARTICLE_WIDTH,
				EntityPixie.PARTICLE_RGB[this.pixieType][0],
				EntityPixie.PARTICLE_RGB[this.pixieType][1],
				EntityPixie.PARTICLE_RGB[this.pixieType][2]);
		if(this.hasPixie && !this.hasProduced && !this.getWorld().isRemote && ticksExisted % IceAndFireConfig.ENTITY_SETTINGS.pixieCooldown == 0) {
			this.hasProduced = true;
			IceAndFire.NETWORK_WRAPPER.sendToAll(new MessageUpdatePixieJar(pos.toLong(), true));
		}
		prevRotationYaw = rotationYaw;
		if(this.world.rand.nextInt(30) == 0) this.rotationYaw = (this.world.rand.nextFloat() * 360F) - 180F;
		if(ticksExisted % 40 == 0 && this.world.rand.nextInt(6) == 0) this.world.playSound(
				this.pos.getX() + 0.5D,
				this.pos.getY() + 0.5D,
				this.pos.getZ() + 0.5,
				ModSounds.PIXIE_IDLE,
				SoundCategory.NEUTRAL,
				1,
				1,
				false);
	}

	public void releasePixie() {
		if(this.hasPixie) {
			EntityPixie pixie = new EntityPixie(this.world);
			pixie.setPositionAndRotation(
					this.pos.getX() + 0.5F,
					this.pos.getY() + 1F,
					this.pos.getZ() + 0.5F,
					this.world.rand.nextInt(360),
					0);
			pixie.setHeldItem(EnumHand.MAIN_HAND, pixieItems.get(0));
			pixie.setColor(this.pixieType);
			if(!world.isRemote) world.spawnEntity(pixie);
			this.hasPixie = false;
			this.pixieType = 0;
			pixie.ticksUntilHouseAI = 500;
			pixie.setTamed(this.tamedPixie);
			pixie.setOwnerId(this.pixieOwnerUUID);
			if(!world.isRemote) IceAndFire.NETWORK_WRAPPER.sendToAll(new MessageUpdatePixieHouse(
					pos.toLong(),
					false,
					0));
		}
	}
}
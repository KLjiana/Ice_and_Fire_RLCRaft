package com.github.alexthe666.iceandfire.client.gui;

import com.github.alexthe666.iceandfire.inventory.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly (Side.CLIENT)
public class GuiPodium extends GuiContainer {

	private IInventory playerInventory;
	private IInventory podiumInventory;

	public GuiPodium (InventoryPlayer playerInv, IInventory podiumInv) {
		super (new ContainerPodium (playerInv, podiumInv, Minecraft.getMinecraft ().player));
		this.playerInventory = playerInv;
		this.podiumInventory = podiumInv;
		this.allowUserInput = false;
		this.ySize = 133;
	}

	@Override
	protected void drawGuiContainerForegroundLayer (int mouseX, int mouseY) {
		if (podiumInventory != null) {
			String s = this.podiumInventory.getDisplayName ().getUnformattedText ();
			this.fontRendererObj.drawString (s, this.xSize / 2 - this.fontRendererObj.getStringWidth (s) / 2, 6, 4210752);
		}
		this.fontRendererObj.drawString (this.playerInventory.getDisplayName ().getUnformattedText (), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color (1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager ().bindTexture (new ResourceLocation ("iceandfire:textures/gui/podium.png"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect (k, l, 0, 0, this.xSize, this.ySize);
	}
}
package com.github.alexthe666.iceandfire.client.render.tile;

import com.github.alexthe666.iceandfire.client.model.ModelDragonEgg;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.tile.TileEntityEggInIce;
import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderEggInIce extends TileEntitySpecialRenderer {

	@Override
	public void render(TileEntity entity, double x, double y, double z, float f, int f1, float alpha) {
		ModelDragonEgg model = new ModelDragonEgg();
		TileEntityEggInIce egg = (TileEntityEggInIce) entity;
		if (egg.type != null) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y - 0.75F, (float) z + 0.5F);
			GL11.glPushMatrix();
			EnumDragonEgg eggType = egg.type.dragonType != DragonType.ICE ? EnumDragonEgg.BLUE : egg.type;
			this.bindTexture(RenderPodium.getEggTexture(eggType));
			GL11.glPushMatrix();
			model.renderFrozen(egg);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
	}

}

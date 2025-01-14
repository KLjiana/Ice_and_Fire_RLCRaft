package com.github.alexthe666.iceandfire.client.render.entity;

import com.github.alexthe666.iceandfire.client.model.ModelTideTrident;
import com.github.alexthe666.iceandfire.entity.projectile.EntityTideTrident;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTideTrident extends Render<EntityTideTrident> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("iceandfire:textures/models/misc/tide_trident.png");
    private final ModelTideTrident modelTideTrident = new ModelTideTrident();

    public RenderTideTrident(RenderManager renderer) {
        super(renderer);
    }

    public void doRender(EntityTideTrident entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.bindEntityTexture(entity);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.disableLighting();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.prevRotationYaw - entity.rotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.prevRotationPitch - entity.rotationPitch) * partialTicks + 90.0F, 0.0F, 0.0F, 1.0F);
        this.modelTideTrident.render(entity, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
    }

    protected ResourceLocation getEntityTexture(EntityTideTrident entity) {
        return TEXTURE;
    }
}
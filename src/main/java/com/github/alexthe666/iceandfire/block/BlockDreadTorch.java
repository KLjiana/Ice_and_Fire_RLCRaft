package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.enums.EnumParticle;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockDreadTorch extends BlockTorch {

    public BlockDreadTorch() {
        super();
        this.setTranslationKey("iceandfire.dread_torch");
        this.setHardness(0F);
        this.setResistance(10000F);
        this.setLightLevel(0.35F);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(IceAndFire.TAB);
        setRegistryName(IceAndFire.MODID, "dread_torch");
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        EnumFacing enumfacing = stateIn.getValue(FACING);
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.6D;
        double d2 = (double) pos.getZ() + 0.5D;
        if (enumfacing.getAxis().isHorizontal()) {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            IceAndFire.PROXY.spawnParticle(EnumParticle.DREAD_TORCH, worldIn, d0 + 0.27D * (double) enumfacing1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double) enumfacing1.getZOffset(), 0.0D, 0.0D, 0.0D);

        } else {
            IceAndFire.PROXY.spawnParticle(EnumParticle.DREAD_TORCH, worldIn, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }
}
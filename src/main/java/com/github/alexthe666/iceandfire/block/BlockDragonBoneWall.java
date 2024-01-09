package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.core.ModBlocks;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockDragonBoneWall extends BlockWall {

    public BlockDragonBoneWall() {
        super(ModBlocks.dragon_bone_block);
        this.setHardness(20F);
        this.setResistance(500F);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(IceAndFire.TAB);
        this.setTranslationKey("iceandfire.dragon_bone_wall");
        this.setRegistryName(IceAndFire.MODID, "dragon_bone_wall");
    }

    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this));
    }

}

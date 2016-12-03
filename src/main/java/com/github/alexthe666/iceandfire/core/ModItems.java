package com.github.alexthe666.iceandfire.core;

import com.github.alexthe666.iceandfire.item.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import com.github.alexthe666.iceandfire.enums.EnumDragonArmor;
import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;

public class ModItems {

	public static Item bestiary;
	public static Item manuscript;
	public static Item sapphireGem;
	public static Item silverIngot;
	public static Item silverNugget;
	public static Item silver_helmet;
	public static Item silver_chestplate;
	public static Item silver_leggings;
	public static Item silver_boots;
	public static Item silver_sword;
	public static Item silver_shovel;
	public static Item silver_pickaxe;
	public static Item silver_axe;
	public static Item silver_hoe;
	public static Item fire_stew;
	public static Item frost_stew;
	public static Item dragonegg_red;
	public static Item dragonegg_green;
	public static Item dragonegg_bronze;
	public static Item dragonegg_gray;
	public static Item dragonegg_blue;
	public static Item dragonegg_white;
	public static Item dragonegg_sapphire;
	public static Item dragonegg_silver;
	public static Item dragonscales_red;
	public static Item dragonscales_green;
	public static Item dragonscales_bronze;
	public static Item dragonscales_gray;
	public static Item dragonscales_blue;
	public static Item dragonscales_white;
	public static Item dragonscales_sapphire;
	public static Item dragonscales_silver;
	public static Item dragonbone;
	public static Item witherbone;
	public static Item fishing_spear;
	public static Item wither_shard;
	public static Item dragonbone_sword;
	public static Item dragonbone_shovel;
	public static Item dragonbone_pickaxe;
	public static Item dragonbone_axe;
	public static Item dragonbone_hoe;
	public static Item dragonbone_arrow;
	public static Item dragonbone_bow;
	public static Item dragonbone_sword_fire;
	public static Item dragonbone_sword_ice;
	public static Item dragon_skull;
	public static Item dragon_armor_iron;
	public static Item dragon_armor_gold;
	public static Item dragon_armor_diamond;
	public static Item dragon_meal;
	public static Item fire_dragon_flesh;
	public static Item ice_dragon_flesh;
	public static Item fire_dragon_heart;
	public static Item ice_dragon_heart;
	public static Item fire_dragon_blood;
	public static Item ice_dragon_blood;
	public static Item dragon_stick;
	public static Item dragon_horn;
	public static Item dragon_horn_fire;
	public static Item dragon_horn_ice;

	public static ArmorMaterial silverMetal = EnumHelper.addArmorMaterial("Silver", "iceandfire:armor_silverMetal", 25, new int[] { 2, 6, 7, 2 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0);
	public static ArmorMaterial dragon = EnumHelper.addArmorMaterial("DragonScales", "iceandfire:armor_dragon_scales", 25, new int[] { 2, 6, 7, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 2);
	public static ToolMaterial silverTools = EnumHelper.addToolMaterial("Silver", 2, 460, 11.0F, 1.0F, 18);
	public static ToolMaterial boneTools = EnumHelper.addToolMaterial("Dragonbone", 4, 1660, 10.0F, 4.0F, 22);
	public static ToolMaterial fireBoneTools = EnumHelper.addToolMaterial("FireDragonbone", 4, 2000, 10.0F, 5.5F, 22);
	public static ToolMaterial iceBoneTools = EnumHelper.addToolMaterial("IceDragonbone", 4, 2000, 10.0F, 5.5F, 22);

	public static void init() {
		bestiary = new ItemBestiary();
		manuscript = new ItemGeneric("manuscript", "iceandfire.manuscript");
		sapphireGem = new ItemGeneric("sapphire_gem", "iceandfire.sapphireGem");
		silverIngot = new ItemGeneric("silver_ingot", "iceandfire.silverIngot");
		silverNugget = new ItemGeneric("silver_nugget", "iceandfire.silverNugget");
		silver_helmet = new ItemSilverArmor(silverMetal, 0, EntityEquipmentSlot.HEAD, "armor_silverMetal_helmet", "iceandfire.silver_helmet");
		silver_chestplate = new ItemSilverArmor(silverMetal, 1, EntityEquipmentSlot.CHEST, "armor_silverMetal_chestplate", "iceandfire.silver_chestplate");
		silver_leggings = new ItemSilverArmor(silverMetal, 2, EntityEquipmentSlot.LEGS, "armor_silverMetal_leggings", "iceandfire.silver_leggings");
		silver_boots = new ItemSilverArmor(silverMetal, 3, EntityEquipmentSlot.FEET, "armor_silverMetal_boots", "iceandfire.silver_boots");
		silver_sword = new ItemModSword(silverTools, "silver_sword", "iceandfire.silver_sword");
		silver_shovel = new ItemModShovel(silverTools, "silver_shovel", "iceandfire.silver_shovel");
		silver_pickaxe = new ItemModPickaxe(silverTools, "silver_pickaxe", "iceandfire.silver_pickaxe");
		silver_axe = new ItemModAxe(silverTools, "silver_axe", "iceandfire.silver_axe");
		silver_hoe = new ItemModHoe(silverTools, "silver_hoe", "iceandfire.silver_hoe");
		fire_stew = new ItemGeneric("fire_stew", "iceandfire.fire_stew");
		frost_stew = new ItemGeneric("frost_stew", "iceandfire.frost_stew");
		dragonegg_red = new ItemDragonEgg("dragonegg_red", EnumDragonEgg.RED);
		dragonegg_green = new ItemDragonEgg("dragonegg_green", EnumDragonEgg.GREEN);
		dragonegg_bronze = new ItemDragonEgg("dragonegg_bronze", EnumDragonEgg.BRONZE);
		dragonegg_gray = new ItemDragonEgg("dragonegg_gray", EnumDragonEgg.GRAY);
		dragonegg_blue = new ItemDragonEgg("dragonegg_blue", EnumDragonEgg.BLUE);
		dragonegg_white = new ItemDragonEgg("dragonegg_white", EnumDragonEgg.WHITE);
		dragonegg_sapphire = new ItemDragonEgg("dragonegg_sapphire", EnumDragonEgg.SAPPHIRE);
		dragonegg_silver = new ItemDragonEgg("dragonegg_silver", EnumDragonEgg.SILVER);
		dragonscales_red = new ItemDragonScales("dragonscales_red", EnumDragonEgg.RED);
		dragonscales_green = new ItemDragonScales("dragonscales_green", EnumDragonEgg.GREEN);
		dragonscales_bronze = new ItemDragonScales("dragonscales_bronze", EnumDragonEgg.BRONZE);
		dragonscales_gray = new ItemDragonScales("dragonscales_gray", EnumDragonEgg.GRAY);
		dragonscales_blue = new ItemDragonScales("dragonscales_blue", EnumDragonEgg.BLUE);
		dragonscales_white = new ItemDragonScales("dragonscales_white", EnumDragonEgg.WHITE);
		dragonscales_sapphire = new ItemDragonScales("dragonscales_sapphire", EnumDragonEgg.SAPPHIRE);
		dragonscales_silver = new ItemDragonScales("dragonscales_silver", EnumDragonEgg.SILVER);
		EnumDragonArmor.initArmors();
		dragonbone = new ItemDragonBone();
		witherbone = new ItemGeneric("witherbone", "iceandfire.witherbone");
		fishing_spear = new ItemFishingSpear();
		wither_shard = new ItemGeneric("wither_shard", "iceandfire.wither_shard");
		dragonbone_sword = new ItemModSword(boneTools, "dragonbone_sword", "iceandfire.dragonbone_sword");
		dragonbone_shovel = new ItemModShovel(boneTools, "dragonbone_shovel", "iceandfire.dragonbone_shovel");
		dragonbone_pickaxe = new ItemModPickaxe(boneTools, "dragonbone_pickaxe", "iceandfire.dragonbone_pickaxe");
		dragonbone_axe = new ItemModAxe(boneTools, "dragonbone_axe", "iceandfire.dragonbone_axe");
		dragonbone_hoe = new ItemModHoe(boneTools, "dragonbone_hoe", "iceandfire.dragonbone_hoe");
		dragonbone_sword_fire = new ItemAlchemySword(fireBoneTools, "dragonbone_sword_fire", "iceandfire.dragonbone_sword_fire");
		dragonbone_sword_ice = new ItemAlchemySword(iceBoneTools, "dragonbone_sword_ice", "iceandfire.dragonbone_sword_ice");
		dragonbone_arrow = new ItemGeneric("dragonbone_arrow", "iceandfire.dragonbone_arrow");
		dragonbone_bow = new ItemDragonBow();
		dragon_skull = new ItemDragonSkull();
		dragon_armor_iron = new ItemDragonArmor(0, "dragonarmor_iron");
		dragon_armor_gold = new ItemDragonArmor(1, "dragonarmor_gold");
		dragon_armor_diamond = new ItemDragonArmor(0, "dragonarmor_diamond");
		dragon_meal = new ItemGeneric("dragon_meal", "iceandfire.dragon_meal");
		fire_dragon_flesh = new ItemDragonFlesh(true);
		ice_dragon_flesh = new ItemDragonFlesh(false);
		fire_dragon_heart = new ItemGeneric("fire_dragon_heart", "iceandfire.fire_dragon_heart");
		ice_dragon_heart = new ItemGeneric("ice_dragon_heart", "iceandfire.ice_dragon_heart");
		fire_dragon_blood = new ItemGeneric("fire_dragon_blood", "iceandfire.fire_dragon_blood");
		ice_dragon_blood = new ItemGeneric("ice_dragon_blood", "iceandfire.ice_dragon_blood");
		dragon_stick = new ItemGeneric("dragon_stick", "iceandfire.dragon_stick");
		dragon_horn = new ItemDragonHornStatic();
		dragon_horn_fire = new ItemDragonHornActive("dragon_horn_fire");
		dragon_horn_ice = new ItemDragonHornActive("dragon_horn_ice");

	}
}

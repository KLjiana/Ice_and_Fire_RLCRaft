package com.github.alexthe666.iceandfire.core;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.enums.EnumHippogryphTypes;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.List;

public class ModEntities {

	public static void init() {
		if (IceAndFire.CONFIG.spawnHippogryphs) {
			for (EnumHippogryphTypes type : EnumHippogryphTypes.values()) {
				if (!type.developer) {
					for (Biome biome : Biome.REGISTRY) {
						if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.HILLS)) {
							List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.CREATURE);
							spawnList.add(new Biome.SpawnListEntry(EntityHippogryph.class, IceAndFire.CONFIG.hippogryphSpawnRate, 1, 1));
						}
					}
				}
			}
		}
		if (IceAndFire.CONFIG.spawnDeathWorm) {
			for (Biome biome : Biome.REGISTRY) {
				if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.DRY) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH) && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.MESA)) {
					List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.CREATURE);
					spawnList.add(new Biome.SpawnListEntry(EntityDeathWorm.class, IceAndFire.CONFIG.deathWormSpawnRate, 1, 3));
				}
			}
		}
		if (IceAndFire.CONFIG.spawnTrolls) {
			for (EnumTroll type : EnumTroll.values()) {
				for (Biome biome : Biome.REGISTRY) {
					if (biome != null && BiomeDictionary.hasType(biome, type.spawnBiome)) {
						List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.MONSTER);
						spawnList.add(new Biome.SpawnListEntry(EntityTroll.class, IceAndFire.CONFIG.trollSpawnRate, 1, 1));
					}
				}
			}
		}
		if (IceAndFire.CONFIG.spawnCockatrices) {
			for (Biome biome : Biome.REGISTRY) {
				if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SAVANNA) && BiomeDictionary.hasType(biome, BiomeDictionary.Type.SPARSE)) {
					List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.CREATURE);
					spawnList.add(new Biome.SpawnListEntry(EntityCockatrice.class, IceAndFire.CONFIG.cockatriceSpawnRate, 1, 2));
				}
			}
		}
		if (IceAndFire.CONFIG.spawnAmphitheres) {
			for (Biome biome : Biome.REGISTRY) {
				if (biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE)) {
					List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.CREATURE);
					spawnList.add(new Biome.SpawnListEntry(EntityAmphithere.class, IceAndFire.CONFIG.amphithereSpawnRate, 1, 3));
				}
			}
		}
	}
}

package net.vincent.rulemaster;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.vincent.rulemaster.datagen.*;
import net.vincent.rulemaster.datagen.damage.ModDamageTypes;
import net.vincent.rulemaster.datagen.enchantment.ModEnchantments;
import net.vincent.rulemaster.datagen.loot.ModBlockLootTableProvider;
import net.vincent.rulemaster.datagen.loot.ModEntityLootTableProvider;
import net.vincent.rulemaster.datagen.tags.*;
import net.vincent.rulemaster.datagen.villager.ModPOITags;
import net.vincent.rulemaster.datagen.villager.ModTradeSets;
import net.vincent.rulemaster.datagen.villager.ModVillagerTradeTags;
import net.vincent.rulemaster.datagen.villager.ModVillagerTrades;
import net.vincent.rulemaster.world.ModConfiguredFeatures;
import net.vincent.rulemaster.world.ModPlacedFeatures;

public class RuleMasterDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModEntityTypeTagProvider::new);
//		pack.addProvider(ModEquipmentAssetProvider::new);
		pack.addProvider(ModSoundsProvider::new);
		pack.addProvider(ModStructureRegistryProvider::new);
		pack.addProvider(ModBiomeTagProvider::new);

		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModVillagerTradeTags::new);
		pack.addProvider(ModPOITags::new);
		pack.addProvider(ModEnchantmentTagProvider::new);
		pack.addProvider(ModAdvancements::new);

//		pack.addProvider(ModFluidTags::new);
		pack.addProvider(ModEntityLootTableProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.JUKEBOX_SONG, ModJukeboxSongs::bootstrap);
		registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
		registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);
		registryBuilder.add(Registries.TRADE_SET, ModTradeSets::bootstrap);
		registryBuilder.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}

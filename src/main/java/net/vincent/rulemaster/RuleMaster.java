package net.vincent.rulemaster;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.vincent.rulemaster.block.ModBlocks;
import net.vincent.rulemaster.data.ModDataComponents;
import net.vincent.rulemaster.effect.ModEffects;
import net.vincent.rulemaster.item.ModCreativeModeTabs;
import net.vincent.rulemaster.item.ModItems;
import net.vincent.rulemaster.loot.ModLootTableModifiers;
import net.vincent.rulemaster.tag.ModTags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class RuleMaster implements ModInitializer {
	public static final String MOD_ID = "rulemaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerCreativeModeTabs();
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModEffects.registerEffects();
		ModDataComponents.registerDataComponents();
		ModLootTableModifiers.registerLootTableModifyEvents();
	}
}
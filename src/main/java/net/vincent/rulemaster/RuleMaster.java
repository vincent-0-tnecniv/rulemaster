package net.vincent.rulemaster;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.vincent.rulemaster.block.ModBlocks;
import net.vincent.rulemaster.client.CameraShakePayload;
import net.vincent.rulemaster.data.ModDataComponents;
import net.vincent.rulemaster.effect.ModEffects;
import net.vincent.rulemaster.entity.ModEntities;
import net.vincent.rulemaster.item.ModCreativeModeTabs;
import net.vincent.rulemaster.item.ModItems;
import net.vincent.rulemaster.loot.ModLootTableModifiers;
import net.vincent.rulemaster.world.structure.pieces.ModStructurePieces;
import net.vincent.rulemaster.world.structure.pieces.ModStructureTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleMaster implements ModInitializer {
	public static final String MOD_ID = "rulemaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.clientboundPlay().register(
				CameraShakePayload.TYPE,
				CameraShakePayload.STREAM_CODEC
		);

		ModCreativeModeTabs.registerCreativeModeTabs();
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModEffects.registerEffects();
		ModDataComponents.registerDataComponents();
		ModLootTableModifiers.registerLootTableModifyEvents();
		ModEntities.registerModEntities();
		ModStructurePieces.register();
		ModStructureTypes.register();
	}
}
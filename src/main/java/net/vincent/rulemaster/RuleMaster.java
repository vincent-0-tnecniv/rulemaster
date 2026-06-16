package net.vincent.rulemaster;

import net.fabricmc.api.ModInitializer;

import net.vincent.rulemaster.block.ModBlocks;
import net.vincent.rulemaster.effect.ModEffects;
import net.vincent.rulemaster.item.ModCreativeModeTabs;
import net.vincent.rulemaster.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleMaster implements ModInitializer {
	public static final String MOD_ID = "rulemaster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModCreativeModeTabs.registerCreativeModeTabs();
		ModEffects.registerEffects();
	}
}
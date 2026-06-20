package net.vincent.rulemaster.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.vincent.rulemaster.RuleMaster;

import java.util.Arrays;

public class ModTags {
    public static class Blocks {
//        public static final TagKey<Block> NEEDS_BLOOD_CRYSTAL_TOOL = createTag("needs_blood_crystal_tool");
        public static final TagKey<Block> INCORRECT_FOR_BLOOD_CRYSTAL_TOOL = createTag("incorrect_for_blood_crystal_tool");

//        public static final TagKey<Block> PAXEL_MINEABLE = createTag("mineable/paxel");
//        public static final TagKey<Block> BLOODWOOD_LOGS = createTag("bloodwood_logs");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name));
        }
    }

    public static class Items {
//        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> BLOOD_CRYSTAL_REPAIRABLE = createTag("blood_crystal_repairables");

//        public static final TagKey<Item> BLOODWOOD_LOGS = createTag("bloodwood_logs");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name));
        }
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> HUMANOID = createTag("humanoid");
        public static final TagKey<EntityType<?>> LIVING_HUMANOID = createTag("living_humanoid");
        public static final TagKey<EntityType<?>> BLOOD_HUMANOID = createTag("blood_humanoid");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name));
        }
        
        
    }

    public static class Trades {
//        public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_1 = createTag("kaupenger/level_1");
//        public static final TagKey<VillagerTrade> KAUPENGER_LEVEL_2 = createTag("kaupenger/level_2");

        private static TagKey<VillagerTrade> createTag(String name) {
            return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name));
        }
    }

    public static void registerTags() {
        RuleMaster.LOGGER.info("Registering Tags for " + RuleMaster.MOD_ID);
    }
}

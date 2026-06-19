package net.vincent.rulemaster.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.item.ModItems;
import net.vincent.rulemaster.tag.ModTags;

import java.util.List;

public class ModLootTableModifiers {

    public static void registerLootTableModifyEvents() {
        LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);
    }

    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder, LootTableSource source, HolderLookup.Provider provider) {
        // WARNING!!
        // This is very inapplicable with tags!
        // If tags are to be used, move over to:
        // entity -> mixin.EntityLootTableModifierMixin.java
        // block -> BlockBreakHandler.java
        // loot table tags are not supported yet - not like there are a lot of loot table tags lol

//        if(key.identifier().equals(Identifier.withDefaultNamespace("entities/villager"))) {
//            LootPool.Builder poolBuilder = LootPool.lootPool()
//                    .setRolls(ConstantValue.exactly(1))
//                    .when(LootItemRandomChanceCondition.randomChance(0.2f)) // Drops 45% of the time
//                    .add(LootItem.lootTableItem(ModItems.BLOOD_CRYSTAL))
//                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());
//
//            builder.pool(poolBuilder.build());
//        }
    }
}
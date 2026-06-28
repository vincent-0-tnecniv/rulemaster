package net.vincent.rulemaster.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModEntityLootTableProvider extends FabricEntityLootSubProvider {
    public ModEntityLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate() {

//        this.add(ModEntities.GIRAFFE,
//                LootTable.lootTable().withPool(LootPool.lootPool()
//                        .setRolls(ConstantValue.exactly(1.0F))
//                        .add(LootItem.lootTableItem(Items.ACACIA_LEAVES)
//                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
//                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
//                        ).when(LootItemKilledByPlayerCondition.killedByPlayer())));
    }
}
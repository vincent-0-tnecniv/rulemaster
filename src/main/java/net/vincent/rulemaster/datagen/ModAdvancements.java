package net.vincent.rulemaster.datagen;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancements extends AdvancementProvider {
    public ModAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new RuleMasterAdvancements()));
    }

    public static class RuleMasterAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);
            var blocks = registries.lookupOrThrow(Registries.BLOCK);

//            AdvancementHolder useChisel = Advancement.Builder.advancement()
//                    .parent(root)
//                    .display(
//                            ModItems.CHISEL,
//                            Component.translatable("advancements.RuleMaster.chisel_stone.title"),
//                            Component.translatable("advancements.RuleMaster.chisel_stone.description"),
//                            null,
//                            AdvancementType.TASK,
//                            true,
//                            true,
//                            false)
//                    .addCriterion("chisel_stone", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location()
//                            .setBlock(BlockPredicate.Builder.block().of(blocks, Blocks.STONE)), ItemPredicate.Builder.item().of(items, ModItems.CHISEL.asItem())))
//                    .save(output, RuleMaster.MOD_ID + ":RuleMaster/chisel_stone");
//
//            AdvancementHolder stinkyAdv = Advancement.Builder.advancement()
//                    .parent(useChisel)
//                    .display(
//                            Items.DIRT,
//                            Component.translatable("advancements.RuleMaster.be_stinky.title"),
//                            Component.translatable("advancements.RuleMaster.be_stinky.description"),
//                            null,
//                            AdvancementType.TASK,
//                            true,
//                            true,
//                            true)
//                    .addCriterion("be_stinky", EffectsChangedTrigger.TriggerInstance.hasEffects(MobEffectsPredicate.Builder.effects().and(ModEffects.STINKY)))
//                    .save(output, RuleMaster.MOD_ID + ":RuleMaster/stinky");
        }
    }
}

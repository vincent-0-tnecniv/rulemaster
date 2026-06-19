package net.vincent.rulemaster.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {


    public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Helper method to add entities to a tag
        addToTag(ModTags.Entities.BLOOD_HUMANOID,
                EntityType.WITCH
        );
        addToTag(ModTags.Entities.LIVING_HUMANOID,
                EntityType.VILLAGER,
                EntityType.WANDERING_TRADER,
                EntityType.PIGLIN,
                EntityType.PIGLIN_BRUTE
        );
        addToTag(ModTags.Entities.HUMANOID,
                EntityType.ZOMBIE,
                EntityType.ZOMBIE_VILLAGER,
                EntityType.DROWNED,
                EntityType.HUSK,
                EntityType.ZOMBIFIED_PIGLIN,
                EntityType.WITCH,
                EntityType.VILLAGER,
                EntityType.WANDERING_TRADER,
                EntityType.PIGLIN,
                EntityType.PIGLIN_BRUTE
        );
    }

    @SafeVarargs
    private void addToTag(TagKey<EntityType<?>> tag, EntityType<?>... entities) {
        var builder = valueLookupBuilder(tag);
        for (EntityType<?> entity : entities) {
            builder.add(entity);
        }
        RuleMaster.LOGGER.debug("Added {} entities to tag {}", entities.length, tag.location());
    }
}
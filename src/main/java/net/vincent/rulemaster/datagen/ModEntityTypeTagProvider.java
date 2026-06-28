package net.vincent.rulemaster.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
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

        var entityLookup = wrapperLookup.lookupOrThrow(Registries.ENTITY_TYPE);

        registerTag(entityLookup, ModTags.Entities.BLOOD_HUMANOID,
                EntityTypes.WITCH
        );
        registerTag(entityLookup, ModTags.Entities.LIVING_HUMANOID,
                EntityTypes.VILLAGER,
                EntityTypes.WANDERING_TRADER,
                EntityTypes.PIGLIN,
                EntityTypes.PIGLIN_BRUTE
        );
        registerTag(entityLookup, ModTags.Entities.HUMANOID,
                EntityTypes.ZOMBIE,
                EntityTypes.ZOMBIE_VILLAGER,
                EntityTypes.DROWNED,
                EntityTypes.HUSK,
                EntityTypes.ZOMBIFIED_PIGLIN,
                EntityTypes.WITCH,
                EntityTypes.VILLAGER,
                EntityTypes.WANDERING_TRADER,
                EntityTypes.PIGLIN,
                EntityTypes.PIGLIN_BRUTE
        );
    }

    private void registerTag(HolderLookup.RegistryLookup<EntityType<?>> entityLookup, TagKey<EntityType<?>> tag, EntityType<?>... entities) {
        for (EntityType<?> entity : entities) {
            tag(tag).add(entityLookup.getOrThrow(entity.builtInRegistryHolder().key()).key());
        }
        RuleMaster.LOGGER.debug("Added {} entities to tag {}", entities.length, tag.location());
    }

    private void deregisterTag(HolderLookup.RegistryLookup<EntityType<?>> entityLookup, TagKey<EntityType<?>> tag, EntityType<?>... entities) {
        for (EntityType<?> entity : entities) {
            tag(tag).remove(entityLookup.getOrThrow(entity.builtInRegistryHolder().key()).key());
        }
        RuleMaster.LOGGER.debug("Removed {} entities from tag {}", entities.length, tag.location());
    }
}
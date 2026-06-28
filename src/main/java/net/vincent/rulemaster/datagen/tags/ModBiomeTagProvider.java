package net.vincent.rulemaster.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.vincent.rulemaster.tag.ModTags;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModBiomeTagProvider extends FabricTagsProvider<Biome> {

    public ModBiomeTagProvider(FabricPackOutput output,
                               CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, Registries.BIOME, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
//        var biomeLookup = wrapperLookup.lookupOrThrow(Registries.BIOME);

        var builder = tag(ModTags.Biomes.HAS_CRADLE_OF_LIFE);

        builder
                .add(Biomes.BADLANDS)
                .add(Biomes.DESERT)
                .add(Biomes.MEADOW)
                .add(Biomes.DARK_FOREST);
    }
}
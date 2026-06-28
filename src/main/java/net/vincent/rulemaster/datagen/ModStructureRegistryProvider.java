package net.vincent.rulemaster.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.tag.ModTags;
import net.vincent.rulemaster.world.structure.CradleOfLifeStructure;

import java.util.concurrent.CompletableFuture;

public class ModStructureRegistryProvider extends FabricDynamicRegistryProvider {

    public ModStructureRegistryProvider(FabricPackOutput output,
                                        CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        // Get the biome lookup
        var biomeLookup = registries.lookupOrThrow(Registries.BIOME);

        // Get the biome tag that defines where the structure spawns
        var biomeTag = biomeLookup.getOrThrow(ModTags.Biomes.HAS_CRADLE_OF_LIFE);

        // Create the resource key for your structure
        var structureKey = ResourceKey.create(
                Registries.STRUCTURE,
                Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "cradle_of_life")
        );

        Structure.StructureSettings settings = new Structure.StructureSettings(
                biomeTag,                                         // biomes
                java.util.Map.of(),                               // spawn overrides (empty)
                GenerationStep.Decoration.SURFACE_STRUCTURES,     // generation step
                TerrainAdjustment.BEARD_THIN                      // terrain adjustment
        );

        CradleOfLifeStructure structure = new CradleOfLifeStructure(settings);

        // Register the structure with its settings
        entries.add(structureKey, structure);
    }

    @Override
    public String getName() {
        return "Structure Registry";
    }
}
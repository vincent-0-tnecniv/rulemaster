package net.vincent.rulemaster.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.vincent.rulemaster.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        var blockLookup = registries.lookupOrThrow(Registries.BLOCK);

        registerTag(blockLookup, BlockTags.MINEABLE_WITH_PICKAXE,
                ModBlocks.BLOOD_CRYSTAL_BLOCK);

//                .add(ModBlocks.RAW_BISMUTH_BLOCK)
//                .add(ModBlocks.BISMUTH_ORE)
//                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE)
//                .add(ModBlocks.BISMUTH_NETHER_ORE)
//                .add(ModBlocks.BISMUTH_END_ORE)
//                .add(ModBlocks.BISMUTH_STAIRS)
//                .add(ModBlocks.BISMUTH_SLAB)
//                .add(ModBlocks.BISMUTH_FENCE)
//                .add(ModBlocks.BISMUTH_FENCE_GATE)
//                .add(ModBlocks.BISMUTH_WALL)
//                .add(ModBlocks.BISMUTH_DOOR)
//                .add(ModBlocks.BISMUTH_TRAPDOOR)
//                .add(ModBlocks.BISMUTH_LAMP);

//        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
//                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE);

        registerTag(blockLookup, BlockTags.NEEDS_DIAMOND_TOOL,
                ModBlocks.BLOOD_CRYSTAL_BLOCK);

//        valueLookupBuilder(BlockTags.STAIRS)
//                .add(ModBlocks.BISMUTH_STAIRS);
//        valueLookupBuilder(BlockTags.SLABS)
//                .add(ModBlocks.BISMUTH_SLAB);
//
//        valueLookupBuilder(BlockTags.BUTTONS)
//                .add(ModBlocks.BISMUTH_BUTTON);
//        valueLookupBuilder(BlockTags.PRESSURE_PLATES)
//                .add(ModBlocks.BISMUTH_PRESSURE_PLATE);
//
//        valueLookupBuilder(BlockTags.FENCES)
//                .add(ModBlocks.BISMUTH_FENCE);
//        valueLookupBuilder(BlockTags.FENCE_GATES)
//                .add(ModBlocks.BISMUTH_FENCE);
//        valueLookupBuilder(BlockTags.WALLS)
//                .add(ModBlocks.BISMUTH_WALL);
//
//        valueLookupBuilder(BlockTags.DOORS)
//                .add(ModBlocks.BISMUTH_DOOR);
//        valueLookupBuilder(BlockTags.TRAPDOORS)
//                .add(ModBlocks.BISMUTH_TRAPDOOR);
//
//        valueLookupBuilder(ModTags.Blocks.NEEDS_BISMUTH_TOOL)
//                .add(ModBlocks.MAGIC_BLOCK)
//                .addTag(BlockTags.NEEDS_IRON_TOOL);
//        valueLookupBuilder(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL)
//                .addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
//
//        valueLookupBuilder(ModTags.Blocks.PAXEL_MINEABLE)
//                .forceAddTag(BlockTags.MINEABLE_WITH_PICKAXE)
//                .forceAddTag(BlockTags.MINEABLE_WITH_SHOVEL)
//                .forceAddTag(BlockTags.MINEABLE_WITH_AXE);
//
//        valueLookupBuilder(BlockTags.CROPS)
//                .add(ModBlocks.CAULIFLOWER_CROP)
//                .add(ModBlocks.RICE_CROP);
//        valueLookupBuilder(BlockTags.FLOWERS)
//                .add(ModBlocks.PETUNIA);
//        valueLookupBuilder(BlockTags.FLOWER_POTS)
//                .add(ModBlocks.POTTED_PETUNIA);
//
//        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
//                .add(ModBlocks.BLOODWOOD_LOG)
//                .add(ModBlocks.BLOODWOOD_WOOD)
//                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG)
//                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
//
//        valueLookupBuilder(BlockTags.SAPLINGS)
//                .add(ModBlocks.BLOODWOOD_SAPLING);
//
//        valueLookupBuilder(BlockTags.PLANKS)
//                .add(ModBlocks.BLOODWOOD_PLANKS);
//
//        valueLookupBuilder(ModTags.Blocks.BLOODWOOD_LOGS)
//                .add(ModBlocks.BLOODWOOD_LOG)
//                .add(ModBlocks.BLOODWOOD_WOOD)
//                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG)
//                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD);

    }

    public void registerTag(HolderLookup.RegistryLookup<Block> blockLookup, TagKey<Block> tag, Block... blocks) {
        for (Block iteratedBlock : blocks) {
            tag(tag).add(blockLookup.getOrThrow(iteratedBlock.builtInRegistryHolder().key()).key());
        }
    }

    public void deregisterTag(HolderLookup.RegistryLookup<Block> blockLookup, TagKey<Block> tag, Block... blocks) {
        for (Block iteratedBlock : blocks) {
            tag(tag).remove(blockLookup.getOrThrow(iteratedBlock.builtInRegistryHolder().key()).key());
        }
    }
}

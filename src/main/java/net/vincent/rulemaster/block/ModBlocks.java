package net.vincent.rulemaster.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.util.BlockPropertiesHelper;

import java.util.function.Function;

public class ModBlocks {
    public static final Block BLOOD_CRYSTAL_BLOCK = registerBlock("blood_crystal_block", properties ->
            new Block(properties.strength(4.0F).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final Block FLESH_BLOCK = registerBlock("flesh_block", properties ->
            new FleshBlock(BlockPropertiesHelper.copy(properties, Blocks.BEDROCK).noLootTable().sound(SoundType.SLIME_BLOCK)));
    // no copy using code allowed - have to do the actual copy yourself

    private static Block registerBlockWithoutBlockItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name), function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name)))));
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of()
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name)))));
    }

    public static void registerBlocks() {
        RuleMaster.LOGGER.info("Registering Mod Blocks for " + RuleMaster.MOD_ID);
    }
}

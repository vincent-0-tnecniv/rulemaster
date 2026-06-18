package net.vincent.rulemaster.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.vincent.rulemaster.block.ModBlocks;
import net.vincent.rulemaster.data.ModDataComponents;
import net.vincent.rulemaster.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    private static final PropertyDispatch<VariantMutator> ROTATION_HORIZONTAL_FACING = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
            .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
            .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
            .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
            .select(Direction.NORTH, BlockModelGenerators.NOP);

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
//        var bismuthFamily = blockModelGenerators.family(ModBlocks.BISMUTH_BLOCK);
//
        blockModelGenerators.createTrivialCube(ModBlocks.BLOOD_CRYSTAL_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.FLESH_BLOCK);
//
//        bismuthFamily.stairs(ModBlocks.BISMUTH_STAIRS);
//        bismuthFamily.slab(ModBlocks.BISMUTH_SLAB);
//
//        bismuthFamily.button(ModBlocks.BISMUTH_BUTTON);
//        bismuthFamily.pressurePlate(ModBlocks.BISMUTH_PRESSURE_PLATE);
//
//        bismuthFamily.fence(ModBlocks.BISMUTH_FENCE);
//        bismuthFamily.fenceGate(ModBlocks.BISMUTH_FENCE_GATE);
//        bismuthFamily.wall(ModBlocks.BISMUTH_WALL);
//
//        blockModelGenerators.createDoor(ModBlocks.BISMUTH_DOOR);
//        blockModelGenerators.createTrapdoor(ModBlocks.BISMUTH_TRAPDOOR);
//
//        MultiVariant off = BlockModelGenerators.plainVariant(TexturedModel.CUBE.create(ModBlocks.BISMUTH_LAMP, blockModelGenerators.modelOutput));
//        MultiVariant on = BlockModelGenerators.plainVariant(blockModelGenerators.createSuffixedVariant(ModBlocks.BISMUTH_LAMP, "_on",
//                ModelTemplates.CUBE_ALL, TextureMapping::cube));
//        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.BISMUTH_LAMP)
//                .with(BlockModelGenerators.createBooleanModelDispatch(BismuthLampBlock.CLICKED, on, off)));
//
//        blockModelGenerators.createCropBlock(ModBlocks.CAULIFLOWER_CROP, CauliflowerCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6);
//        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.PETUNIA, ModBlocks.POTTED_PETUNIA, BlockModelGenerators.PlantType.TINTED);
//
//        blockModelGenerators.createTintedLeaves(ModBlocks.COLORED_LEAVES, TexturedModel.LEAVES, -12466612);
//        blockModelGenerators.createCrossBlock(ModBlocks.HONEY_BERRY_BUSH, BlockModelGenerators.PlantType.NOT_TINTED, HoneyBerryBushBlock.AGE,
//                0, 1, 2, 3);
//
//        blockModelGenerators.createCropBlock(ModBlocks.RICE_CROP, CropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
//
//        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.CHAIR,
//                        BlockModelGenerators.plainVariant(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "block/chair")))
//                .with(ROTATION_HORIZONTAL_FACING));
//
//        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.BISMUTH_WATER_BLOCK);
//
//        blockModelGenerators.createTrivialCube(ModBlocks.BLOODWOOD_PLANKS);
//        blockModelGenerators.woodProvider(ModBlocks.BLOODWOOD_LOG).log(ModBlocks.BLOODWOOD_LOG).wood(ModBlocks.BLOODWOOD_WOOD);
//        blockModelGenerators.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG).log(ModBlocks.STRIPPED_BLOODWOOD_LOG).wood(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
//
//        blockModelGenerators.createTrivialBlock(ModBlocks.BLOODWOOD_LEAVES, TexturedModel.LEAVES);
//        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.BLOODWOOD_SAPLING, ModBlocks.POTTED_BLOODWOOD_SAPLING, BlockModelGenerators.PlantType.TINTED);
//
//        blockModelGenerators.createTrivialCube(ModBlocks.KAUPEN_PORTAL);
//        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.MAIN_PEDESTAL);
//        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.SIDE_PEDESTAL);
//        blockModelGenerators.createFurnace(ModBlocks.CRYSTALLIZER, TexturedModel.ORIENTABLE_ONLY_TOP);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        ItemModel.Unbaked unbakedPiercer = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.BLOOD_PIERCER, ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked unbakedHalfPiercer = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.BLOOD_PIERCER, "_half_health", ModelTemplates.FLAT_HANDHELD_ITEM));
        ItemModel.Unbaked unbakedLowPiercer = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(ModItems.BLOOD_PIERCER, "_low_health", ModelTemplates.FLAT_HANDHELD_ITEM));

        ItemModel.Unbaked healthMatchingPiercerModel = new ConditionalItemModel.Unbaked(Optional.empty(),
                new HasComponent(ModDataComponents.LOW, false),
                    unbakedLowPiercer,
                new ConditionalItemModel.Unbaked(Optional.empty(),
                        new HasComponent(ModDataComponents.HALF, false),
                        unbakedHalfPiercer,
                        unbakedPiercer)
        );

        // What is it doing? It is basically the same as the following logic
        /*
            if(low){
                return low_model
            } else{
                if(half){
                    return half_model;
                } else{
                    return full_model;
                }
            }

         */

        itemModelGenerators.itemModelOutput.accept(ModItems.BLOOD_PIERCER,
                new ClientItem(healthMatchingPiercerModel, new ClientItem.Properties(false, false, 1f)).model());




//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.RAW_BISMUTH, ModelTemplates.FLAT_ITEM);
//        // itemModelGenerators.generateFlatItem(ModItems.CHISEL, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.CAULIFLOWER, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.STARLIGHT_ASHES, ModelTemplates.FLAT_ITEM);
//
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
//
//        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_HELMET, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
//        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_CHESTPLATE, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
//        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_LEGGINGS, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
//        itemModelGenerators.generateTrimmableItem(ModItems.BISMUTH_BOOTS, ModArmorMaterials.BISMUTH_KEY, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
//
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_HORSE_ARMOR, ModelTemplates.FLAT_ITEM);
//
//        itemModelGenerators.createFlatItemModel(ModItems.KAUPEN_BOW, ModelTemplates.BOW);
//        itemModelGenerators.generateBow(ModItems.KAUPEN_BOW);
//
//        itemModelGenerators.generateFlatItem(ModItems.BAR_BRAWL_MUSIC_DISC, ModelTemplates.FLAT_ITEM);
//
//        // itemModelGenerators.declareCustomModelItem(ModItems.SPECTRE_STAFF); This is for 3d only
//        itemModelGenerators.generateFlatItem(ModItems.SPECTRE_STAFF, ModelTemplates.FLAT_HANDHELD_ITEM);
//
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_WATER_BUCKET, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.GIRAFFE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.WARTURTLE_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.DODO_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
//
//        itemModelGenerators.generateFlatItem(ModItems.IRON_WARTURTLE_ARMOR, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.GOLD_WARTURTLE_ARMOR, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.DIAMOND_WARTURTLE_ARMOR, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.NETHERITE_WARTURTLE_ARMOR, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BISMUTH_WARTURTLE_ARMOR, ModelTemplates.FLAT_ITEM);
//
//        itemModelGenerators.generateFlatItem(ModItems.BLOODWOOD_BOAT, ModelTemplates.FLAT_ITEM);
//        itemModelGenerators.generateFlatItem(ModItems.BLOODWOOD_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
//
//        itemModelGenerators.declareCustomModelItem(ModItems.TOMAHAWK);
    }
}
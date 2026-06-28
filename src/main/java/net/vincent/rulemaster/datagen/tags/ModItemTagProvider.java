package net.vincent.rulemaster.datagen.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.vincent.rulemaster.item.ModItems;
import net.vincent.rulemaster.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {

        var itemLookup = registries.lookupOrThrow(Registries.ITEM);

//        valueLookupBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
//                .add(ModItems.BISMUTH)
//                .add(Items.IRON_INGOT)
//                .add(Items.COAL)
//                .add(ModItems.CAULIFLOWER);
//
        registerTag(itemLookup, ModTags.Items.BLOOD_CRYSTAL_REPAIRABLE,
                ModItems.BLOOD_CRYSTAL);
//
//        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.BISMUTH_SWORD);
//        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.BISMUTH_PICKAXE).add(ModItems.BISMUTH_PAXEL).add(ModItems.BISMUTH_HAMMER);
//        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.BISMUTH_SHOVEL).add(ModItems.BISMUTH_PAXEL);
//        valueLookupBuilder(ItemTags.AXES).add(ModItems.BISMUTH_AXE).add(ModItems.BISMUTH_PAXEL);
//        valueLookupBuilder(ItemTags.HOES).add(ModItems.BISMUTH_HOE);

        registerTag(itemLookup, ItemTags.SPEARS,
                ModItems.BLOOD_PIERCER);
//
//        valueLookupBuilder(ItemTags.HEAD_ARMOR)
//                .add(ModItems.BISMUTH_HELMET);
//        valueLookupBuilder(ItemTags.CHEST_ARMOR)
//                .add(ModItems.BISMUTH_CHESTPLATE);
//        valueLookupBuilder(ItemTags.LEG_ARMOR)
//                .add(ModItems.BISMUTH_LEGGINGS);
//        valueLookupBuilder(ItemTags.FOOT_ARMOR)
//                .add(ModItems.BISMUTH_BOOTS);
//
//        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE)
//                .add(ModItems.KAUPEN_BOW);
//
//        valueLookupBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
//                .add(ModItems.CAULIFLOWER_SEEDS);
//
//        valueLookupBuilder(ItemTags.CREEPER_DROP_MUSIC_DISCS)
//                .add(ModItems.BAR_BRAWL_MUSIC_DISC);
//
//        valueLookupBuilder(ModTags.Items.BLOODWOOD_LOGS)
//                .add(ModBlocks.BLOODWOOD_LOG.asItem())
//                .add(ModBlocks.BLOODWOOD_WOOD.asItem())
//                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem())
//                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem());
//
//        valueLookupBuilder(ItemTags.PLANKS)
//                .add(ModBlocks.BLOODWOOD_PLANKS.asItem());
//
//        valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
//                .add(ModBlocks.BLOODWOOD_LOG.asItem())
//                .add(ModBlocks.BLOODWOOD_WOOD.asItem())
//                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.asItem())
//                .add(ModBlocks.STRIPPED_BLOODWOOD_WOOD.asItem());
    }

    public void registerTag(HolderLookup.RegistryLookup<Item> blockLookup, TagKey<Item> tag, Item... items) {
        for (Item iteratedItem : items) {
            tag(tag).add(blockLookup.getOrThrow(iteratedItem.builtInRegistryHolder().key()).key());
        }
    }

    public void deregisterTag(HolderLookup.RegistryLookup<Item> blockLookup, TagKey<Item> tag, Item... items) {
        for (Item iteratedItem : items) {
            tag(tag).remove(blockLookup.getOrThrow(iteratedItem.builtInRegistryHolder().key()).key());
        }
    }
}

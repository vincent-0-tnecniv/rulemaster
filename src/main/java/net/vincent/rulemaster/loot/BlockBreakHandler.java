package net.vincent.rulemaster.loot;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.vincent.rulemaster.item.ModItems;
import net.vincent.rulemaster.tag.ModTags;

public class BlockBreakHandler {

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world instanceof ServerLevel serverLevel) {
                if (state.getBlock().builtInRegistryHolder().is(BlockTags.DIRT)) {
                    ItemStack stack = new ItemStack(ModItems.BLOOD_CRYSTAL);
                    stack.setCount(1 + serverLevel.getRandom().nextInt(3)); // 1-3 items

                    ItemEntity itemEntity = new ItemEntity(
                            serverLevel,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            stack
                    );

                    serverLevel.addFreshEntity(itemEntity);
                }
            }
        });
    }
}
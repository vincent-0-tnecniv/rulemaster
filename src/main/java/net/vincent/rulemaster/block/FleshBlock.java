package net.vincent.rulemaster.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.vincent.rulemaster.datagen.damage.ModDamageTypes;

public class FleshBlock extends Block {
    public static final IntegerProperty TIME_OF_CYCLE = IntegerProperty.create("time_of_cycle", 0, 35);
    public static final int DEFAULT_TOC = 0;

    public FleshBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(TIME_OF_CYCLE, DEFAULT_TOC));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(itemStack.getItem() instanceof BlockItem){
            player.sendOverlayMessage(Component.literal("The flesh tried to consume the block, but you pulled it back..."));
            return InteractionResult.FAIL;
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if(!level.isClientSide()){
            DamageSource damageSource = player.damageSources().source(ModDamageTypes.BLEEDING);
            player.hurt(damageSource, 2);
            player.sendOverlayMessage(Component.literal("§cThe flesh is consuming your body!"));
        }
        super.attack(state, level, pos, player);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIME_OF_CYCLE);
        super.createBlockStateDefinition(builder);
    }
}

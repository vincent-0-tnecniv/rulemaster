package net.vincent.rulemaster.mixin;

import net.minecraft.resources.DependantName;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.StatePredicate;
import net.minecraft.world.level.block.state.BlockBehaviour.StateArgumentPredicate;
import net.minecraft.world.level.block.state.BlockBehaviour.PostProcess;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

@Mixin(BlockBehaviour.Properties.class)
public interface PropertiesAccessor {

    @Accessor("destroyTime")
    float getDestroyTime();

    @Accessor("explosionResistance")
    float getExplosionResistance();

    @Accessor("hasCollision")
    boolean getHasCollision();

    @Accessor("isRandomlyTicking")
    boolean getIsRandomlyTicking();

    @Accessor("lightEmission")
    ToIntFunction<BlockState> getLightEmission();

    @Accessor("mapColor")
    Function<BlockState, MapColor> getMapColor();

    @Accessor("soundType")
    SoundType getSoundType();

    @Accessor("friction")
    float getFriction();

    @Accessor("speedFactor")
    float getSpeedFactor();

    @Accessor("jumpFactor")
    float getJumpFactor();

    @Accessor("bounceRestitution")
    float getBounceRestitution();

    @Accessor("dynamicShape")
    boolean getDynamicShape();

    @Accessor("canOcclude")
    boolean getCanOcclude();

    @Accessor("isAir")
    boolean getIsAir();

    @Accessor("ignitedByLava")
    boolean getIgnitedByLava();

    @Accessor("pushReaction")
    PushReaction getPushReaction();

    @Accessor("requiresCorrectToolForDrops")
    boolean getRequiresCorrectToolForDrops();

    @Accessor("offsetFunction")
    BlockBehaviour.OffsetFunction getOffsetFunction();

    @Accessor("spawnTerrainParticles")
    boolean getSpawnTerrainParticles();

    @Accessor("requiredFeatures")
    FeatureFlagSet getRequiredFeatures();

    @Accessor("requiredFeatures")
    void setRequiredFeatures(FeatureFlagSet requiredFeatures);

    @Accessor("emissiveRendering")
    Predicate<BlockState> getEmissiveRendering();

    @Accessor("instrument")
    NoteBlockInstrument getInstrument();

    @Accessor("replaceable")
    boolean getReplaceable();

    @Accessor("liquid")
    boolean getIsLiquid();

    @Accessor("forceSolidOn")
    boolean getForceSolidOn();

    @Accessor("forceSolidOff")
    boolean getForceSolidOff();

    @Accessor("forceSolidOn")
    void setForceSolidOn(boolean forcedOn);

    @Accessor("forceSolidOff")
    void setForceSolidOff(boolean forcedOff);

    @Accessor("isValidSpawn")
    StateArgumentPredicate<EntityType<?>> getIsValidSpawn();

    @Accessor("isRedstoneConductor")
    StatePredicate getIsRedstoneConductor();

    @Accessor("isSuffocating")
    StatePredicate getIsSuffocating();

    @Accessor("isViewBlocking")
    StatePredicate getIsViewBlocking();

    @Accessor("postProcess")
    PostProcess getPostProcess();

    @Accessor("drops")
    DependantName<Block, Optional<ResourceKey<LootTable>>> getDrops();

    @Accessor("drops")
    void setDrops(DependantName<Block, Optional<ResourceKey<LootTable>>> drops);

    @Accessor("descriptionId")
    DependantName<Block, String> getDescriptionId();

    @Accessor("descriptionId")
    void setDescriptionId(DependantName<Block, String> descriptionId);
}
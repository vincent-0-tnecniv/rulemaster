package net.vincent.rulemaster.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.phys.Vec3;
import net.vincent.rulemaster.mixin.PropertiesAccessor;

public class BlockPropertiesHelper {

    public static BlockBehaviour.Properties copy(Properties properties, Block block) {
        BlockBehaviour.Properties source = block.properties();
        PropertiesAccessor accessor = (PropertiesAccessor) source;
        PropertiesAccessor copyAccessor = (PropertiesAccessor) properties;

        // Copy using public setters and accessor getters
        properties.strength(accessor.getDestroyTime(), accessor.getExplosionResistance()); // V // V
        properties.sound(accessor.getSoundType()); // V
        properties.mapColor(accessor.getMapColor()); // V
        properties.friction(accessor.getFriction()); // V
        properties.speedFactor(accessor.getSpeedFactor()); // V
        properties.jumpFactor(accessor.getJumpFactor()); // X
        properties.lightLevel(accessor.getLightEmission()); // V
        properties.bounceRestitution(accessor.getBounceRestitution()); // V
        properties.emissiveRendering(accessor.getEmissiveRendering()); // V
        properties.instrument(accessor.getInstrument()); // V
        properties.isValidSpawn(accessor.getIsValidSpawn()); // X
        properties.isRedstoneConductor(accessor.getIsRedstoneConductor()); // X
        properties.isSuffocating(accessor.getIsSuffocating()); // X
        properties.isViewBlocking(accessor.getIsViewBlocking()); // X
        properties.postProcess(accessor.getPostProcess()); // X
        properties.pushReaction(accessor.getPushReaction()); // V

        ResourceKey<Block> blockKey = block.builtInRegistryHolder().key();
        String descriptionId = accessor.getDescriptionId().get(blockKey);
        properties.overrideDescription(descriptionId); // X

        // Boolean properties with public setters

        if(accessor.getIsLiquid()){
            properties.liquid(); // V
        }
        if(accessor.getCanOcclude() && !accessor.getHasCollision()){
            properties.noCollision(); // V
        } else if(accessor.getCanOcclude()){
            properties.noOcclusion(); // V
        }
        if (accessor.getIsRandomlyTicking()) {
            properties.randomTicks(); // V
        }
        if (accessor.getDynamicShape()) {
            properties.dynamicShape(); // V
        }
        if (accessor.getIsAir()) {
            properties.air(); // V
        }
        if (accessor.getIgnitedByLava()) {
            properties.ignitedByLava(); // V
        }
        if (accessor.getRequiresCorrectToolForDrops()) {
            properties.requiresCorrectToolForDrops(); // V
        }
        if (accessor.getReplaceable()) {
            properties.replaceable(); // V
        }
        if(!accessor.getSpawnTerrainParticles()){
            properties.noTerrainParticles(); // V
        }

        copyAccessor.setForceSolidOn(accessor.getForceSolidOn()); // V
        copyAccessor.setForceSolidOff(accessor.getForceSolidOff()); // V
        copyAccessor.setRequiredFeatures(accessor.getRequiredFeatures()); // V
        copyAccessor.setDrops(accessor.getDrops()); // X

        if(accessor.getOffsetFunction() == null){ // V
            //  vanilla method case 0
            properties.offsetType(BlockBehaviour.OffsetType.NONE);
        } else {
            // trickier - even XYZ can have y =0
            // following does a 10-block distance test - should not vary
            BlockBehaviour.OffsetFunction offsetFunction = accessor.getOffsetFunction();
            BlockPos pos1 = new BlockPos(0, 0, 0);
            BlockPos pos2 = new BlockPos(0, 10, 0);

            try {
                Vec3 offset1 = offsetFunction.evaluate(null, pos1);
                Vec3 offset2 = offsetFunction.evaluate(null, pos2);
                if (offset1.y != offset2.y) {
                    properties.offsetType(BlockBehaviour.OffsetType.XYZ);
                } else {
                    properties.offsetType(BlockBehaviour.OffsetType.XZ);
                }

            } catch (Exception e) {
                properties.offsetType(BlockBehaviour.OffsetType.NONE);
            }
        }

        // Note: Some properties like isValidSpawn, isRedstoneConductor, etc.
        // need to be set using their respective public setters if available.

        return properties;
    }
}
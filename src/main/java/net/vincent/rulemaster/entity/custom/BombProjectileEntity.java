package net.vincent.rulemaster.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.vincent.rulemaster.client.CameraShakeManager;
import net.vincent.rulemaster.entity.variant.BombVariant;

public class BombProjectileEntity extends AbstractArrow {

    // Use a higher ID that won't conflict with parent class (AbstractArrow uses up to ~11)
    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(BombProjectileEntity.class, EntityDataSerializers.INT);

    private Player owner;
    private BombVariant variant = BombVariant.NORMAL;

    public BombProjectileEntity(EntityType<? extends BombProjectileEntity> type, Level level) {
        super(type, level);
        this.variant = BombVariant.NORMAL;
        this.owner = null;
    }

    public BombProjectileEntity(EntityType<? extends BombProjectileEntity> type, Level level, BombVariant variant, Player owner) {
        super(type, level);
        this.variant = variant;
        this.owner = owner;
        if (owner != null) {
            this.setOwner(owner);
        }
        // Set the variant in synched data
        this.entityData.set(VARIANT, variant.ordinal());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        // THIS MUST BE CALLED BEFORE ANY ACCESS TO entityData
        builder.define(VARIANT, 0);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        int variantOrdinal = input.getInt("Variant").orElse(0);
        this.variant = BombVariant.values()[variantOrdinal];
        this.entityData.set(VARIANT, variantOrdinal);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Variant", this.variant.ordinal());
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(this.variant.sizeX, this.variant.sizeY);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        if (this.level().isClientSide()) {
            super.onHitBlock(hitResult);
            return;
        }
        BlockPos pos = hitResult.getBlockPos();
        float radius = this.variant.explosionRadius;
        this.level().explode(owner, pos.getX(), pos.getY(), pos.getZ(), radius, ExplosionInteraction.BLOCK);
        if(owner instanceof ServerPlayer player){
            CameraShakeManager.sendCameraShake(player, this.variant.horizontalShake, this.variant.verticalShake, this.variant.horizontalShake, this.variant == BombVariant.NUKE ? 200 : 20);
        }
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        if (this.level().isClientSide()) {
            super.onHitEntity(hitResult);
            return;
        }
        Vec3 pos = hitResult.getLocation();
        float radius = this.variant.explosionRadius;
        this.level().explode(owner, pos.x, pos.y, pos.z, radius, ExplosionInteraction.MOB);
        if(owner instanceof ServerPlayer player){
            CameraShakeManager.sendCameraShake(player, this.variant.horizontalShake, this.variant.verticalShake, this.variant.horizontalShake, this.variant == BombVariant.NUKE ? 200 : 20);
        }
        this.discard();
    }

    public BombVariant getVariant() {
        return this.variant;
    }

    public Vec2 getGroundedOffset() {
        return Vec2.ZERO;
    }
}
package net.vincent.rulemaster.item.custom;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.vincent.rulemaster.item.ModToolMaterials;
import org.jspecify.annotations.NonNull;

import java.util.*;
import java.util.function.Consumer;

public class BloodCrystalSword extends Item {

    public static List<Entity> damagedEntities = new ArrayList<>();

    public BloodCrystalSword(Properties properties) {
        properties = properties.sword(ModToolMaterials.BLOOD_CRYSTAL, -2f, 1f);
        super(properties);

        registerDashAndRift();
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        builder.accept(Component.literal("Left click on air to dash forward a forceful strike!"));
        builder.accept(Component.literal("Dashing forward leaves behind an unstable rift."));
        builder.accept(Component.literal("The rift lasts for 30 seconds, right click to tear it and release a massive explosion!"));
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        return super.use(level, player, hand);
    }

    public void registerDashAndRift() {
        UseItemCallback.EVENT.register((player, level, hand) -> {
            ItemStack stack = player.getItemInHand(hand);
            if(stack.getItem() == this.asItem() && !level.isClientSide()){
                var hitResult = player.pick(5.0, 1.0f, false);
                if(hitResult.getType() == HitResult.Type.MISS || hitResult.getType() == HitResult.Type.ENTITY) {
                    onLeftClick(player, (ServerLevel) level, this.getDefaultInstance());
                    return InteractionResult.PASS;
                }
            }
            return InteractionResult.PASS;
        });
    }

    public static void onLeftClick(Player player, ServerLevel level, ItemStack stack) {
        damagedEntities = new ArrayList<>();
        final float DASH_DISTANCE = 60.0f;
        final double STEP_SIZE = 0.5d;
        if(!(stack.getItem() instanceof BloodCrystalSword)){
            return;
        }
        if(player.getCooldowns().isOnCooldown(stack)){
            return;
        }
        Vec3 startPos = player.position();

        float yaw = player.getYRot();
        float pitch = player.getXRot();

        double dx = -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        double dy = -Math.sin(Math.toRadians(pitch));
        double dz = Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));

        Vec3 direction = new Vec3(dx, dy, dz).normalize();

        double distanceTraveled = 0.0;
        Vec3 currentPos = startPos;

        while (distanceTraveled < DASH_DISTANCE) {
            Vec3 step = direction.scale(STEP_SIZE);
            Vec3 nextPos = currentPos.add(step);
            distanceTraveled += STEP_SIZE;
            System.out.println(distanceTraveled);

            BlockPos checkPos = BlockPos.containing(nextPos);
            checkPos = new BlockPos(checkPos.getX(), checkPos.getY() + 1, checkPos.getZ());
            System.out.println("I am going to " + checkPos);
            System.out.println("This block is " + level.getBlockState(checkPos).getBlock());
            System.out.println("This block is probably " + (level.getBlockState(checkPos).isSolid() ? "not safe" : "safe") + " to go to");
            if (level.getBlockState(checkPos).isSolid()) {
                player.teleportTo(currentPos.x, currentPos.y, currentPos.z);
                player.getCooldowns().addCooldown(stack, 20);
                return;
            } else{
                player.teleportTo(nextPos.x, nextPos.y, nextPos.z);
            }
            damageEntitiesInRadius(player, 5.0f, 100.0f);
            currentPos = nextPos;
        }

        BlockPos finalPos = BlockPos.containing(currentPos);
        if (level.getBlockState(finalPos).isSolid()) {
            player.teleportTo(currentPos.x, currentPos.y + 1, currentPos.z);
        }
        player.fallDistance = 0;

        player.getCooldowns().addCooldown(stack, 20);
    }

    public static void damageEntitiesInRadius(Player player, float radius, float damageAmount) {
        ServerLevel level = (ServerLevel) player.level();
        Vec3 center = player.position();

        // Create a bounding box around the player
        AABB bounds = new AABB(
                center.x - radius,
                center.y - radius,
                center.z - radius,
                center.x + radius,
                center.y + radius,
                center.z + radius
        );

        // Get all living entities within the bounds
        // EntityTypeTest is used for type-safe entity collection
        EntityTypeTest<Entity, LivingEntity> typeTest = EntityTypeTest.forClass(LivingEntity.class);

        // Get entities and filter them
        var entities = level.getEntities(typeTest, entity -> {
            // Filter out the player themselves
            if (entity == player) return false;

            // Filter out creative/spectator players (optional)
            if (entity instanceof Player targetPlayer &&
                    (targetPlayer.isCreative() || targetPlayer.isSpectator())) {
                return false;
            }

            return true;
        });

        // Create damage source
        DamageSource damageSource = level.damageSources().playerAttack(player);

        // Apply damage to each entity
        for (Entity entity : entities) {
            float distance = (float) entity.distanceTo(player);

            // Optional: Damage falls off with distance
            float damage = damageAmount * (1.0f - (distance / radius));
            damage = Math.max(1.0f, damage); // Minimum 1 damage

            // Each entity only takes damage once
            if(!damagedEntities.contains(entity)) {
                entity.hurt(damageSource, damage);
                damagedEntities.add(entity);
            }

            // Optional: Apply knockback
            Vec3 knockbackDir = entity.position().subtract(center).normalize();
            entity.setDeltaMovement(
                    entity.getDeltaMovement().add(knockbackDir.scale(0.5))
            );
            entity.hurtMarked = true;
        }

        // Spawn particles for visual effect
        spawnExplosionEffects(level, center, radius);
    }

    private static void spawnExplosionEffects(ServerLevel level, Vec3 center, float radius) {
        // Spawn particles in a sphere
        for (int i = 0; i < 50; i++) {
            double theta = level.getRandom().nextDouble() * 2 * Math.PI;
            double phi = level.getRandom().nextDouble() * Math.PI;
            double r = radius * level.getRandom().nextDouble();

            double x = center.x + r * Math.sin(phi) * Math.cos(theta);
            double y = center.y + r * Math.cos(phi);
            double z = center.z + r * Math.sin(phi) * Math.sin(theta);

            level.sendParticles(
                    net.minecraft.core.particles.ParticleTypes.EXPLOSION,
                    x, y, z,
                    1,
                    0.0, 0.0, 0.0,
                    0.0
            );

            level.playSound(null, center.x, center.y, center.z, SoundEvents.GENERIC_EXPLODE.value(), SoundSource.PLAYERS);
        }
    }
}

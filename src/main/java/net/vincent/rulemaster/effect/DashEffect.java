package net.vincent.rulemaster.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.vincent.rulemaster.datagen.damage.ModDamageTypes;

import java.util.List;

public class DashEffect extends MobEffect {

    protected DashEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity livingEntity, int amplification) {
        List<Entity> list = livingEntity.level().getEntities(livingEntity, livingEntity.getBoundingBox().inflate(10.25, 10.5, 10.25));
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity instanceof LivingEntity && entity != livingEntity) {
                    DamageSource bleeding = livingEntity.damageSources().source(ModDamageTypes.BLEEDING, null);
                    entity.hurt(bleeding, 20 * (amplification + 1));
                }
            }
        } else if (livingEntity.horizontalCollision) {
            return false;
        }
        livingEntity.fallDistance = 0;
        MobEffectInstance effect = livingEntity.getEffect(ModEffects.DASH);
        if(effect != null && effect.endsWithin(10) && livingEntity instanceof Player player) {
            player.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.0f, 1.0f);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplification) {
        return true;
    }
}

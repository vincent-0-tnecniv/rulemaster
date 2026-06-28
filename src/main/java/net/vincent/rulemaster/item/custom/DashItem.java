package net.vincent.rulemaster.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.vincent.rulemaster.effect.ModEffects;

public class DashItem extends Item {

    public static final float multiplier = 10f;
    public static final Vec3 extendRatio = new Vec3(3, 1, 3);

    public DashItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Vec3 forward = player.getLookAngle();
        var vector = forward.multiply(extendRatio).normalize().add(new Vec3(0, 0.1f, 0)).scale(multiplier);
        if (player.onGround()) {
            player.setPos(player.position().add(0, 1.5, 0));
            vector.add(0, 0.25, 0);
        }
        player.setDeltaMovement(new Vec3(
                Mth.lerp(.75f, player.getDeltaMovement().x, vector.x),
                Mth.lerp(.75f, player.getDeltaMovement().y, vector.y),
                Mth.lerp(.75f, player.getDeltaMovement().z, vector.z)
        ));
        player.playSound(SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, 1.0f, 1.0f);
        player.addEffect(new MobEffectInstance(ModEffects.DASH, 50, 0,  false, false));
        player.invulnerableTime = 20;
        player.getCooldowns().addCooldown(itemStack, 100);
        return super.use(level, player, hand);
    }
}

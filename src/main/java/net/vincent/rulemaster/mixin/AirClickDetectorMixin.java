package net.vincent.rulemaster.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.vincent.rulemaster.item.custom.BloodCrystalSword;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class AirClickDetectorMixin {

    @Inject(method = "attack", at = @At("HEAD"))
    private void onLeftClick(Entity entity, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        if(player.level().isClientSide()) return;
        ItemStack stack = player.getMainHandItem();

        // Check if the player is holding your staff
        if (stack.getItem() instanceof BloodCrystalSword) {
            // Call your item's left-click handler
            BloodCrystalSword.onLeftClick(player, (ServerLevel) player.level(), stack);
        }
    }
}
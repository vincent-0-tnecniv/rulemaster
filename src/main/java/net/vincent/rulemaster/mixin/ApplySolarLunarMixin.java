package net.vincent.rulemaster.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(MinecraftServer.class)
public class ApplySolarLunarMixin {
    @Inject(at = @At("HEAD"), method = "loadLevel")
    private void init(CallbackInfo info) {
        // this WIP mixin will be responsible for adding an NBT tag for each player, assigning them as either solar or lunar
        boolean isLunar = Math.random() < 0.5;

    }
}

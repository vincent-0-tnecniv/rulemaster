package net.vincent.rulemaster.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.vincent.rulemaster.RuleMaster;

public class ModEffects {

    public static final Holder<MobEffect> STEALTH = registerMobEffect("stealth",
            new StealthEffect(MobEffectCategory.BENEFICIAL, 0x000000)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stealth"), 0.4f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> STUN = registerMobEffect("stinky",
            new StunEffect(MobEffectCategory.NEUTRAL, 0xffffff)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stun"), -10.0f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ENTITY_INTERACTION_RANGE,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stun"), -10.0f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.BLOCK_INTERACTION_RANGE,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stun"), -10.0f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stun"), -10.0f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stun"), -10.0f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.FLYING_SPEED,
                            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "stun"), -10.0f,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT,
                Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        RuleMaster.LOGGER.info("Registering Mod Effects for " + RuleMaster.MOD_ID);
    }
}
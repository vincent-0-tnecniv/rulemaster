package net.vincent.rulemaster.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.entity.custom.BombProjectileEntity;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> BOMB_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb"));

    public static final EntityType<BombProjectileEntity> BOMB = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb"),
            EntityType.Builder.<BombProjectileEntity>of(BombProjectileEntity::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(1f, 1f) // Default size, will be overridden in entity
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb")))
    );

    public static final EntityType<BombProjectileEntity> BOMB_SMALL = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb_small"),
            EntityType.Builder.<BombProjectileEntity>of(BombProjectileEntity::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(0.5f, 0.5f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb_small")))
    );

    public static final EntityType<BombProjectileEntity> BOMB_NUKE = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb_nuke"),
            EntityType.Builder.<BombProjectileEntity>of(BombProjectileEntity::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(5f, 5f)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb_nuke")))
    );


    public static void registerModEntities() {
        RuleMaster.LOGGER.info("Registering Mod Entities for " + RuleMaster.MOD_ID);
    }
}

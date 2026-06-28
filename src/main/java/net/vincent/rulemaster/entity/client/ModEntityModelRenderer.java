package net.vincent.rulemaster.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.RuleMasterClient;
import net.vincent.rulemaster.entity.ModEntities;

public class ModEntityModelRenderer {
    public static void registerEntityModel() {
        ModelLayerRegistry.registerModelLayer(ModModelLayerLocations.BOMB, BombModel::createBodyLayer);
        EntityRenderers.register(ModEntities.BOMB, BombRenderer::new);
        EntityRenderers.register(ModEntities.BOMB_SMALL, BombRenderer::new);
        EntityRenderers.register(ModEntities.BOMB_NUKE, BombRenderer::new);

        RuleMasterClient.LOGGER.info("Registering Entity Models for " + RuleMaster.MOD_ID);
    }
}

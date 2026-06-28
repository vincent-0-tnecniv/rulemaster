package net.vincent.rulemaster.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.entity.custom.BombProjectileEntity;
import net.vincent.rulemaster.entity.variant.BombVariant;

public class BombRenderer extends EntityRenderer<BombProjectileEntity, BombRenderState> {
    private BombModel model;

    public BombRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BombModel(context.bakeLayer(ModModelLayerLocations.BOMB));
    }

    @Override
    public BombRenderState createRenderState() {
        return new BombRenderState();
    }

    @Override
    public void extractRenderState(BombProjectileEntity entity, BombRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.yaw = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
        state.pitch = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());

        state.spinRotation = (entity.tickCount + partialTicks) * 30.0F;

        state.groundedOffset = entity.getGroundedOffset();

        BombVariant variant = entity.getVariant();
        if (variant == BombVariant.SMALL) {
            state.bombType = 0;
        } else if (variant == BombVariant.NORMAL) {
            state.bombType = 1;
        } else if (variant == BombVariant.NUKE) {
            state.bombType = 2;
        }
    }

    @Override
    public void submit(BombRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
                       CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        poseStack.pushPose();

        // Scale based on bomb type
        switch (state.bombType) {
            case 0: // SMALL
                poseStack.scale(0.5f, 0.5f, 0.5f);
                break;
            case 2: // NUKE
                poseStack.scale(5f, 5f, 5f);
                break;
            case 1: // NORMAL
            default:
                poseStack.scale(1f, 1f, 1f);
                break;
        }

        // Rotation logic
        if (!state.isGrounded) {
            poseStack.mulPose(Axis.YP.rotationDegrees(state.yaw));
            poseStack.mulPose(Axis.XP.rotationDegrees(state.spinRotation));
        } else {
            poseStack.mulPose(Axis.YP.rotationDegrees(state.groundedOffset.y));
            poseStack.mulPose(Axis.XP.rotationDegrees(state.groundedOffset.x));
        }

        // Translate to center the model
        poseStack.translate(0, -1.0f, 0);

        submitNodeCollector.order(1).submitModel(this.model, state, poseStack, RenderTypes.entitySolid(getTextureLocation()),
                state.lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
        poseStack.popPose();
    }

    public Identifier getTextureLocation() {
        return Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "textures/entity/bomb/bomb.png");
    }
}
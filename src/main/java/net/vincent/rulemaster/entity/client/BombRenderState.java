package net.vincent.rulemaster.entity.client;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.phys.Vec2;

public class BombRenderState extends EntityRenderState {
    public float yaw;
    public float pitch;
    public float spinRotation;
    public boolean isGrounded;
    public Vec2 groundedOffset;
    public int bombType;
}
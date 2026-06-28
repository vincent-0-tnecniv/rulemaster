package net.vincent.rulemaster.entity.variant;

public enum BombVariant {
    NORMAL(1f, 1f, 10f, 4f, 3f),
    SMALL(0.5f, 0.5f, 2f, 2f, 1.5f),
    NUKE(5f, 5f, 100f, 25f, 12.5f);

    public final float sizeX;
    public final float sizeY;
    public final float explosionRadius;
    public final float horizontalShake;
    public final float verticalShake;

    BombVariant(float sizeX, float sizeY, float explosionRadius, float horizontalShake, float verticalShake) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.explosionRadius = explosionRadius;
        this.horizontalShake = horizontalShake;
        this.verticalShake = verticalShake;
    }
}
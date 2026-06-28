package net.vincent.rulemaster.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.vincent.rulemaster.entity.ModEntities;
import net.vincent.rulemaster.entity.custom.BombProjectileEntity;
import net.vincent.rulemaster.entity.variant.BombVariant;

public class CannonItem extends Item {

    public static final int BOMB_COOLDOWN = 20;
    public static final int NUKE_COOLDOWN = 100;

    public CannonItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ItemStack stack = player.getItemInHand(hand);

        // Check cooldown
        if (player.getCooldowns().isOnCooldown(stack)) {
            return InteractionResult.FAIL;
        }

        // Determine bomb type
        // 0 = small, 1 = normal, 2 = nuke
        int bombType = 0; // default: small

        if (player.isShiftKeyDown()) {
            // Check if player has TNT in inventory for nuke
            boolean hasTnt = false;
            for (ItemStack invStack : player.getInventory().getNonEquipmentItems()) {
                if (!invStack.isEmpty() && invStack.getItem() == Items.TNT) {
                    hasTnt = true;
                    break;
                }
            }

            if (hasTnt) {
                bombType = 2; // NUKE
                // Consume one TNT
                for (int i = 0; i < player.getInventory().getNonEquipmentItems().size(); i++) {
                    ItemStack invStack = player.getInventory().getNonEquipmentItems().get(i);
                    if (!invStack.isEmpty() && invStack.getItem() == Items.TNT) {
                        invStack.shrink(1);
                        break;
                    }
                }
            } else {
                // Sneaking but no TNT = normal bomb
                bombType = 1;
            }
        }
        // Not sneaking = small bomb (bombType stays 0)

        // Spawn the bomb
        spawnBomb(level, player, bombType);

        // Add cooldown based on bomb type
        int cooldown = bombType == 2 ? NUKE_COOLDOWN : BOMB_COOLDOWN;
        player.getCooldowns().addCooldown(stack, cooldown);

        // Play sound
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f);

        return InteractionResult.SUCCESS;
    }

    private void spawnBomb(Level level, Player player, int bombType) {
        // Determine entity type and variant based on bomb type
        EntityType<? extends BombProjectileEntity> entityType;
        BombVariant variant;
        float speed;

        switch (bombType) {
            case 2: // NUKE
                entityType = ModEntities.BOMB_NUKE;
                variant = BombVariant.NUKE;
                speed = 5f;
                break;
            case 1: // NORMAL
                entityType = ModEntities.BOMB;
                variant = BombVariant.NORMAL;
                speed = 1.5f;
                break;
            case 0: // SMALL
            default:
                entityType = ModEntities.BOMB_SMALL;
                variant = BombVariant.SMALL;
                speed = 1.5f;
                break;
        }

        BombProjectileEntity bomb = new BombProjectileEntity(entityType, level, variant, player);
        Vec3 pos = player.position();
        bomb.setPos(pos.x, pos.y + player.getEyeHeight(), pos.z);
        Vec3 lookAngle = player.getLookAngle();
        bomb.shoot(lookAngle.x, lookAngle.y, lookAngle.z, speed, 1.0f);
        level.addFreshEntity(bomb);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
package net.vincent.rulemaster.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;
import net.vincent.rulemaster.item.ModItems;
import net.vincent.rulemaster.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public class EntityLootTableModifierMixin {

	// Target the FINAL method that all others call into
	@Inject(method = "dropFromLootTable(Lnet/minecraft/server/level/ServerLevel;" +
			"Lnet/minecraft/world/damagesource/DamageSource;" +
			"Z" +
			"Lnet/minecraft/resources/ResourceKey;" +
			"Ljava/util/function/Consumer;)V",
			at = @At("HEAD"))
	private void addCustomDrops(ServerLevel serverLevel, DamageSource damageSource, boolean causedByPlayer,
	                            ResourceKey<LootTable> lootTable, Consumer<ItemStack> consumer, CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;
		addDropsForTag(ModTags.Entities.BLOOD_HUMANOID, entity, ModItems.BLOOD_CRYSTAL, 1.0f,1, 2);
		addDropsForTag(ModTags.Entities.LIVING_HUMANOID, entity, ModItems.BLOOD_CRYSTAL, 0.5f, 1, 2);
	}

	@Unique
	private static void addDropsForTag(TagKey<EntityType<?>> tag, LivingEntity entity, Item item, float chance, int min, int max) {
		float rng = new Random().nextFloat();
		boolean wouldDrop = rng < chance;
		if(wouldDrop){
			if (entity.getType().builtInRegistryHolder().is(tag) && !entity.level().isClientSide()) {
				// Create the item to drop
				ItemStack stack = new ItemStack(item);
				Random random = new Random();
				stack.setCount(min + random.nextInt(max - min + 1));
				// Spawn the item entity
				ItemEntity itemEntity = new ItemEntity(
						entity.level(),
						entity.getX(),
						entity.getY(),
						entity.getZ(),
						stack
				);
				// Add the item to the world
				entity.level().addFreshEntity(itemEntity);
			}
		}
	}
}
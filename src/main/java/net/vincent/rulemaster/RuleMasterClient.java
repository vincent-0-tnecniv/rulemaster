package net.vincent.rulemaster;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.resources.Identifier;
import net.vincent.rulemaster.client.CameraShakeManager;
import net.vincent.rulemaster.client.CameraShakePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleMasterClient implements ClientModInitializer {
	public static final String MOD_ID = "rulemaster";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(
				CameraShakePayload.TYPE,
				(payload, context) -> {
					context.client().execute(() -> {
						CameraShakeManager.triggerShake(
								payload.intensityX(),
								payload.intensityY(),
								payload.intensityZ(),
								payload.duration()
						);
					});
				}
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			CameraShakeManager.tick();
		});

		LOGGER.info("Client Initialized!");
	}
}
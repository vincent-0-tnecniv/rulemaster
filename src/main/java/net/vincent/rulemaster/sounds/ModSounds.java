package net.vincent.rulemaster.sounds;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.vincent.rulemaster.RuleMaster;

public class ModSounds {
    public static final SoundEvent BLOOD_CRYSTAL_SWORD_RELEASE = registerSoundEvent("blood_crystal_sword_release");

    private static Holder.Reference<SoundEvent> registerJukeboxSong(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name);
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        RuleMaster.LOGGER.info("Registering Mod Sounds for " + RuleMaster.MOD_ID);
    }
    
}

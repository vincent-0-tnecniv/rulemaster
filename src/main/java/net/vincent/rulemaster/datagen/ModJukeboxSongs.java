package net.vincent.rulemaster.datagen;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

public class ModJukeboxSongs {
//    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
//            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bar_brawl"));

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
//        register(context, BAR_BRAWL_KEY, ModSounds.BAR_BRAWL, 162, 15);
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> resourceKey,
                                 final Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorValue) {
        context.register(resourceKey, new JukeboxSong(soundEvent,
                Component.translatable(Util.makeDescriptionId("jukebox_song", resourceKey.identifier())), lengthInSeconds, comparatorValue));
    }
}

package net.vincent.rulemaster.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.item.custom.BloodPiercerItem;

import java.util.function.Function;

public class ModItems {
    public static final Item BLOOD_CRYSTAL = registerItem("blood_crystal", Item::new);

    public static final Item BLOOD_PIERCER = registerItem("blood_piercer", properties ->
            new BloodPiercerItem(ModToolMaterials.BLOOD_CRYSTAL, properties.spear(ModToolMaterials.BLOOD_CRYSTAL,
                    0.5F, 1.2F, 0.3F, 2.0F, 8.0F,
                    5.0F, 5.1F, 8.0F, 4.6F))
    );

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name)))));
    }

    public static void registerItems() {
        RuleMaster.LOGGER.info("Registering Mod Items for " + RuleMaster.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(BLOOD_CRYSTAL);
        });
    }

}

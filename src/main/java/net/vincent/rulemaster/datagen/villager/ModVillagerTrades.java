package net.vincent.rulemaster.datagen.villager;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.item.ModItems;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {
    public static final ResourceKey<VillagerTrade> CLERIC_1_ROTTEN_FLESH_BLOOD_CRYSTAL = createKey("cleric/1/rotten_flesh_blood_crystal");
    public static final ResourceKey<VillagerTrade> CLERIC_3_EMERALD_BLOOD_CRYSTAL = createKey("cleric/3/diamond_cauliflower_seeds");

    public static final ResourceKey<VillagerTrade> FARMER_2_EMERALD_HONEY_BERRIES = createKey("farmer/2/emerald_honey_berries");

    public static final ResourceKey<VillagerTrade> MASON_1_EMERALD_CHISEL = createKey("mason/1/emerald_chisel");

    public static final ResourceKey<VillagerTrade> KAUPENGER_1_EMERALD_BISMUTH = createKey("kaupenger/1/emerald_bismuth");
    public static final ResourceKey<VillagerTrade> KAUPENGER_1_EMERALD_RAW_BISMUTH = createKey("kaupenger/1/emerald_raw_bismuth");

    public static final ResourceKey<VillagerTrade> KAUPENGER_2_EMERALD_CHAIR = createKey("kaupenger/2/emerald_chair");
    public static final ResourceKey<VillagerTrade> KAUPENGER_2_BISMUTH_SPECTRE_STAFF = createKey("kaupenger/2/bismuth_spectre_staff");



    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        register(context, CLERIC_1_ROTTEN_FLESH_BLOOD_CRYSTAL, new VillagerTrade(
                new TradeCost(Items.ROTTEN_FLESH, 16),
                new ItemStackTemplate(ModItems.BLOOD_CRYSTAL),
                12, 10, 0.05f,
                Optional.empty(), List.of()));
        register(context, CLERIC_3_EMERALD_BLOOD_CRYSTAL, new VillagerTrade(
                new TradeCost(Items.EMERALD, 6),
                new ItemStackTemplate(ModItems.BLOOD_CRYSTAL, 16),
                12, 10, 0.05f,
                Optional.empty(), List.of()));
    }


    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name));
    }

    private static void register(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade trade) {
        context.register(resourceKey, trade);
    }
}
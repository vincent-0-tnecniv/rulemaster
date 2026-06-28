package net.vincent.rulemaster.entity.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import net.vincent.rulemaster.RuleMaster;

public class ModModelLayerLocations {
    public static final ModelLayerLocation GIRAFFE =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "giraffe"), "main");

    public static final ModelLayerLocation WARTURTLE =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "warturtle"), "main");
    public static final ModelLayerLocation WARTURTLE_ARMOR =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "warturtle_armor"), "armor");

    public static final ModelLayerLocation DODO =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "dodo"), "main");

    public static final ModelLayerLocation BLOODWOOD_BOAT =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "boat/bloodwood_boat"), "main");
    public static final ModelLayerLocation BLOODWOOD_CHEST_BOAT =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "chest_boat/bloodwood_boat"), "main");

    public static final ModelLayerLocation BOMB =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "bomb"), "main");

}
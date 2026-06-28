package net.vincent.rulemaster.world.structure.pieces;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.vincent.rulemaster.RuleMaster;
import net.vincent.rulemaster.world.structure.CradleOfLifeStructure;

public class ModStructureTypes {

    public static StructureType<CradleOfLifeStructure> CRADLE_OF_LIFE;

    public static void register() {
        CRADLE_OF_LIFE = Registry.register(
                BuiltInRegistries.STRUCTURE_TYPE,
                Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "cradle_of_life"),
                () -> CradleOfLifeStructure.CODEC
        );
    }
}
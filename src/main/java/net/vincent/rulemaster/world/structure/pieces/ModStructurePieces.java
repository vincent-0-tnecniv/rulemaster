package net.vincent.rulemaster.world.structure.pieces;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.vincent.rulemaster.RuleMaster;

public class ModStructurePieces {

    public static final StructurePieceType CRADLE_OF_LIFE_PIECE = CradleOfLifePiece::new;

    public static void register() {
        Registry.register(
                BuiltInRegistries.STRUCTURE_PIECE,
                Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "cradle_of_life_piece"),
                CRADLE_OF_LIFE_PIECE
        );
    }
}
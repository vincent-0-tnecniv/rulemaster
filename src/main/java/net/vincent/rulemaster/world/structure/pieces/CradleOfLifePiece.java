package net.vincent.rulemaster.world.structure.pieces;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.vincent.rulemaster.RuleMaster;

public class CradleOfLifePiece extends StructurePiece {
    private static final Identifier TEMPLATE_ID =
            Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, "cradle_of_life");
    private final Rotation rotation;
    private final BlockPos pos;

    public CradleOfLifePiece(BlockPos pos, Rotation rotation) {
        super(ModStructurePieces.CRADLE_OF_LIFE_PIECE, 0, new BoundingBox(pos));
        this.rotation = rotation;
        this.pos = pos;
    }

    public CradleOfLifePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieces.CRADLE_OF_LIFE_PIECE, tag);
        this.rotation = Rotation.valueOf(tag.getString("Rotation").orElse("NONE"));
        this.pos = BlockPos.of(tag.getLong("Pos").orElse(0L));
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {
        tag.putString("Rotation", this.rotation.name());
        tag.putLong("Pos", this.pos.asLong());
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator,
                            RandomSource random, BoundingBox chunkBB, ChunkPos chunkPos, BlockPos referencePos) {
        // Load and place the template
        StructureTemplateManager templateManager = level.getLevel().getServer().getStructureManager();
        var template = templateManager.get(TEMPLATE_ID);

        if (template.isPresent()) {
            var settings = new StructurePlaceSettings()
                    .setRotation(this.rotation)
                    .setBoundingBox(chunkBB);

            template.get().placeInWorld(level, this.pos, this.pos, settings, random, 2);
        }
    }

    @Override
    public StructurePieceType getType() {
        return ModStructurePieces.CRADLE_OF_LIFE_PIECE;
    }
}
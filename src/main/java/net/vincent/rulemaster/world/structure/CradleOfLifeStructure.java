package net.vincent.rulemaster.world.structure;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.vincent.rulemaster.world.structure.pieces.CradleOfLifePiece;
import net.vincent.rulemaster.world.structure.pieces.ModStructureTypes;

import java.util.Optional;

public class CradleOfLifeStructure extends Structure {

    public static final MapCodec<CradleOfLifeStructure> CODEC = simpleCodec(CradleOfLifeStructure::new);

    public CradleOfLifeStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        // Get the chunk position
        BlockPos chunkPos = context.chunkPos().getWorldPosition();

        // Get the height at the center of the chunk (x+8, z+8)
        int height = context.chunkGenerator().getBaseHeight(
                chunkPos.getX() + 8,
                chunkPos.getZ() + 8,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );

        // Position where the structure will be placed
        BlockPos pos = new BlockPos(chunkPos.getX() + 8, height, chunkPos.getZ() + 8);

        // Random rotation
        Rotation rotation = Rotation.getRandom(context.random());

        // Return the generation stub
        return Optional.of(new GenerationStub(pos, (builder) -> {
            builder.addPiece(new CradleOfLifePiece(pos, rotation));
        }));
    }

    @Override
    public StructureType<?> type() {
        // This will work because CRADLE_OF_LIFE is now assigned
        return ModStructureTypes.CRADLE_OF_LIFE;
    }
}
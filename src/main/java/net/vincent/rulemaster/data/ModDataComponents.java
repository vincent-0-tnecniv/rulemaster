package net.vincent.rulemaster.data;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.vincent.rulemaster.RuleMaster;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final StreamCodec<ByteBuf, Boolean> BOOLEAN_STREAM_CODEC =
            StreamCodec.of(ByteBuf::writeBoolean, ByteBuf::readBoolean);

    public static final DataComponentType<Boolean> HALF =
            register("half_health", builder -> builder.persistent(Codec.BOOL)
                    .networkSynchronized(BOOLEAN_STREAM_CODEC).cacheEncoding());

    public static final DataComponentType<Boolean> LOW =
            register("low_health", builder -> builder.persistent(Codec.BOOL)
                    .networkSynchronized(BOOLEAN_STREAM_CODEC).cacheEncoding());


    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(RuleMaster.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents() {
        RuleMaster.LOGGER.info("Registering Data Components for " + RuleMaster.MOD_ID);
    }
}
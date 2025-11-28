package com.tacuarita.tacotools.block;

import com.tacuarita.tacotools.block.entity.NetherFurnaceBlockEntity;
import com.tacuarita.tacotools.registry.TacoBlockEntities;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

// Nether Furnace Block
public class NetherFurnaceBlock
    extends AbstractFurnaceBlock
    implements BlockEntityProvider {

    public NetherFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NetherFurnaceBlockEntity(pos, state);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NetherFurnaceBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory) blockEntity);
            player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        World world,
        BlockState state,
        BlockEntityType<T> type
    ) {
        return world.isClient
            ? null
            : checkType(
                  type,
                  TacoBlockEntities.NETHER_FURNACE,
                  NetherFurnaceBlockEntity::tick
              );
    }
}

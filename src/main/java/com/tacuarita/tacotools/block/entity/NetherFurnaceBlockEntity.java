package com.tacuarita.tacotools.block.entity;

import com.tacuarita.tacotools.mixin.AbstractFurnaceBlockEntityAccessor;
import com.tacuarita.tacotools.registry.TacoBlockEntities;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class NetherFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private static final int CONSTANT_FUEL_TICKS = 200;

    public NetherFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(
            TacoBlockEntities.NETHER_FURNACE,
            pos,
            state,
            RecipeType.SMELTING
        );
    }

    public static void tick(
        World world,
        BlockPos pos,
        BlockState state,
        NetherFurnaceBlockEntity furnace
    ) {
        AbstractFurnaceBlockEntityAccessor access =
            (AbstractFurnaceBlockEntityAccessor) furnace;
        boolean wasLit = access.tacotools$getBurnTime() > 0;
        boolean dirty = false;

        Recipe<?> recipe = world
            .getRecipeManager()
            .getFirstMatch(RecipeType.SMELTING, furnace, world)
            .orElse(null);
        boolean canProcess =
            recipe != null &&
            AbstractFurnaceBlockEntityAccessor.tacotools$canAcceptRecipeOutput(
                world.getRegistryManager(),
                recipe,
                furnace.inventory,
                furnace.getMaxCountPerStack()
            );

        if (canProcess) {
            // Infinite fuel: keep the furnace lit while processing input.
            access.tacotools$setBurnTime(CONSTANT_FUEL_TICKS);
            access.tacotools$setFuelTime(CONSTANT_FUEL_TICKS);

            if (recipe != null) {
                access.tacotools$setCookTimeTotal(getCookTime(recipe));
            }

            int cookTime = access.tacotools$getCookTime() + 1;
            int cookTimeTotal = access.tacotools$getCookTimeTotal();
            if (cookTime >= cookTimeTotal) {
                cookTime = 0;
                access.tacotools$setCookTimeTotal(getCookTime(recipe));
                if (
                    AbstractFurnaceBlockEntityAccessor.tacotools$craftRecipe(
                        world.getRegistryManager(),
                        recipe,
                        furnace.inventory,
                        furnace.getMaxCountPerStack()
                    )
                ) {
                    furnace.setLastRecipe(recipe);
                }
                dirty = true;
            }
            access.tacotools$setCookTime(cookTime);
        } else {
            int cookTime = access.tacotools$getCookTime();
            if (cookTime > 0) {
                access.tacotools$setCookTime(
                    MathHelper.clamp(
                        cookTime - 2,
                        0,
                        access.tacotools$getCookTimeTotal()
                    )
                );
            }
            access.tacotools$setBurnTime(0);
        }

        boolean isLit = access.tacotools$getBurnTime() > 0;
        if (wasLit != isLit) {
            state = state.with(AbstractFurnaceBlock.LIT, isLit);
            world.setBlockState(pos, state, 3);
            dirty = true;
        }

        if (dirty) {
            AbstractFurnaceBlockEntity.markDirty(world, pos, state);
        }
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.tacotools.nether_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(
        int syncId,
        PlayerInventory playerInventory
    ) {
        return new net.minecraft.screen.FurnaceScreenHandler(
            syncId,
            playerInventory,
            this,
            this.propertyDelegate
        );
    }

    private static int getCookTime(Recipe<?> recipe) {
        return recipe instanceof AbstractCookingRecipe cookingRecipe
            ? cookingRecipe.getCookTime()
            : 200;
    }
}

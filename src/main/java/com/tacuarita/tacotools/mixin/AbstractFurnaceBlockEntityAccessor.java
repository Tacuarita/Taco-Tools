package com.tacuarita.tacotools.mixin;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbstractFurnaceBlockEntityAccessor {
	@Accessor("burnTime")
	int tacotools$getBurnTime();

	@Accessor("burnTime")
	void tacotools$setBurnTime(int burnTime);

	@Accessor("fuelTime")
	void tacotools$setFuelTime(int fuelTime);

	@Accessor("cookTime")
	int tacotools$getCookTime();

	@Accessor("cookTime")
	void tacotools$setCookTime(int cookTime);

	@Accessor("cookTimeTotal")
	int tacotools$getCookTimeTotal();

	@Accessor("cookTimeTotal")
	void tacotools$setCookTimeTotal(int cookTimeTotal);

	@Invoker("canAcceptRecipeOutput")
	static boolean tacotools$canAcceptRecipeOutput(DynamicRegistryManager registryManager, Recipe<?> recipe, DefaultedList<ItemStack> inventory, int count) {
		throw new AssertionError();
	}

	@Invoker("craftRecipe")
	static boolean tacotools$craftRecipe(DynamicRegistryManager registryManager, Recipe<?> recipe, DefaultedList<ItemStack> inventory, int count) {
		throw new AssertionError();
	}
}

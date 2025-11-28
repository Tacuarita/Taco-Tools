package com.tacuarita.tacotools.registry;

import com.tacuarita.tacotools.TacoTools;
import com.tacuarita.tacotools.block.entity.NetherFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class TacoBlockEntities {
	public static final BlockEntityType<NetherFurnaceBlockEntity> NETHER_FURNACE = Registry.register(
		Registries.BLOCK_ENTITY_TYPE,
		new Identifier(TacoTools.MOD_ID, "nether_furnace"),
		BlockEntityType.Builder.create(NetherFurnaceBlockEntity::new, TacoBlocks.NETHER_FURNACE).build(null)
	);

	private TacoBlockEntities() {
	}

	public static void register() {
		// Method reference used to ensure class loads and static initializers run.
	}
}

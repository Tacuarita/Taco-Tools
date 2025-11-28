package com.tacuarita.tacotools.registry;

import com.tacuarita.tacotools.TacoTools;
import com.tacuarita.tacotools.block.NetherFurnaceBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class TacoBlocks {

    public static final Block NETHER_FURNACE = registerBlock(
        "nether_furnace",
        new NetherFurnaceBlock(FabricBlockSettings.copyOf(Blocks.FURNACE))
    );

    private TacoBlocks() {}

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(
            Registries.BLOCK,
            new Identifier(TacoTools.MOD_ID, name),
            block
        );
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
            Registries.ITEM,
            new Identifier(TacoTools.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings())
        );
    }

    public static void register() {
        // Method reference used to ensure class loads and static initializers run.
    }
}

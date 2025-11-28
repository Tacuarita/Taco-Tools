package com.tacuarita.tacotools;

import com.tacuarita.tacotools.registry.TacoBlockEntities;
import com.tacuarita.tacotools.registry.TacoBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TacoTools implements ModInitializer {

    public static final String MOD_ID = "tacotools";

    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        TacoBlocks.register();
        TacoBlockEntities.register();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(
            entries -> entries.add(TacoBlocks.NETHER_FURNACE)
        );
        LOGGER.info("Loaded Taco Tools with Nether Furnace.");
    }
}

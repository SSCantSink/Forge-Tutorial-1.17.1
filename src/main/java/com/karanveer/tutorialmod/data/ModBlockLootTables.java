package com.karanveer.tutorialmod.data;

import com.karanveer.tutorialmod.block.ModBlocks;
import com.karanveer.tutorialmod.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        this.add(ModBlocks.TITANIUM_SLAB.get(), (block) -> {
            return createSlabItemTable(ModBlocks.TITANIUM_SLAB.get());
        });

        // Specifically the loot table of this ore is like an ore
        this.add(ModBlocks.TITANIUM_ORE.get(), (block) -> {
            return createOreDrop(ModBlocks.TITANIUM_ORE.get(), ModItems.RAW_TITANIUM.get());
        });

        // drops the blocks themselves, you know for only there kinds of blocks
        this.dropSelf(ModBlocks.TITANIUM_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_DOOR.get());
        this.dropSelf(ModBlocks.TITANIUM_TRAPDOOR.get());
        this.dropSelf(ModBlocks.TITANIUM_BUTTON.get());
        this.dropSelf(ModBlocks.TITANIUM_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.TITANIUM_FENCE.get());
        this.dropSelf(ModBlocks.TITANIUM_WALL.get());
        this.dropSelf(ModBlocks.TITANIUM_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SPEEDY_BLOCK.get());
        this.dropSelf(ModBlocks.TITANIUM_STAIRS.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {

        // returns the list of registered blocks in our ModBlocks class
        // What's with the :: shenanigans
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;

    }
}

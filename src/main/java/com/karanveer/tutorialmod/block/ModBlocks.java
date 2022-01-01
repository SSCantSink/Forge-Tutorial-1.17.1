package com.karanveer.tutorialmod.block;

import com.karanveer.tutorialmod.TutorialMod;
import com.karanveer.tutorialmod.item.ModCreativeModeTab;
import com.karanveer.tutorialmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    /**
     * Just like for items, this is a list the Forge keeps track for us of
     * what blocks we have actually created.
     */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(12f)), CreativeModeTab.TAB_MATERIALS);

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(12f)));

    /**
     * Used to register a block to Minecraft under any
     * particular minecraft creative mode tab
     * Note the T generic is any class that extends Block
     * @param name the name of the block
     * @param block the block to registered using a Supplier
     * @param tab the creative mode tab for the block to be available in
     * @param <T> some class that extends Block
     * @return a Registry Object of that block
     */
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    // Used to register the block as an item as well (since you can carry the block too you know?)
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get()
                , new Item.Properties().tab(tab)));
    }

    /**
     * Used to register a block to Minecraft under the
     * modded creative mode tab called tutorialModTab
     * Note the T generic is any class that extends Block
     * @param name the name of the block
     * @param block the block to registered using a Supplier
     * @param <T> some class that extends Block
     * @return a Registry Object of that block
     */
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Used to register the block as an item as well (since you can carry the block too you know?)
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get()
                , new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

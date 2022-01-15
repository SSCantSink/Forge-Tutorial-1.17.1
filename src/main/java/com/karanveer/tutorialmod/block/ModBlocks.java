package com.karanveer.tutorialmod.block;

import com.karanveer.tutorialmod.TutorialMod;
import com.karanveer.tutorialmod.block.custom.SpeedyBlock;
import com.karanveer.tutorialmod.item.ModCreativeModeTab;
import com.karanveer.tutorialmod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {

    /**
     * Just like for items, this is a list the Forge keeps track for us of
     * what blocks we have actually created.
     */
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(12f).requiresCorrectToolForDrops()),
            "tooltip.block.tutorialmod.titanium_block");

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(10f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SPEEDY_BLOCK = registerBlock("speedy_block",
            () -> new SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(5f).requiresCorrectToolForDrops()));

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

    // Literally the same thing as the above two pairs of methods but also register the block with a tooltip
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tooltipKey) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)) { // now there is an anonymous class
            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
                tooltip.add(new TranslatableComponent(tooltipKey));
                super.appendHoverText(stack, level, tooltip, flagIn);
            }
        });
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

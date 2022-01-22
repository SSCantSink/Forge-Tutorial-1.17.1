package com.karanveer.tutorialmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

/**
 * Creates a new Creative Mode Tab featuring all the new added Modded Items and
 * Blocks
 */
public class ModCreativeModeTab {
    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab("tutorial_mod_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TITANIUM_INGOT.get());
        }
    };

}

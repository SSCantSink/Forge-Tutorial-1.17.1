package com.karanveer.tutorialmod.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

// you know different tiers of items like wood, gold, stone, iron, etc.
public class ModTiers {

    public static final ForgeTier TITANIUM = new ForgeTier(3, 1500, 1f,
            4f, 10, Tags.Blocks.NEEDS_GOLD_TOOL,
            () -> Ingredient.of(ModItems.TITANIUM_INGOT.get()));

}

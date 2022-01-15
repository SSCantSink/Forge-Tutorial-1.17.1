package com.karanveer.tutorialmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class CoalCokeItem extends Item {
    public CoalCokeItem(Properties pProperties) {
        super(pProperties);
    }

    // Only thing to add to create fuel for an item. That's it
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 1200 ;
    }
}

package com.karanveer.tutorialmod.data;

import com.karanveer.tutorialmod.block.ModBlocks;
import com.karanveer.tutorialmod.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        // Builder patterned used in order to make recipes
        // .unlockBy unlocks this recipe in the recipe book (green book in inventory menu)
        ShapedRecipeBuilder.shaped(ModBlocks.TITANIUM_DOOR.get())
                .define('T', ModItems.TITANIUM_INGOT.get())
                .pattern("TT")
                .pattern("TT")
                .pattern("TT")
                .unlockedBy("has_material", has(ModItems.TITANIUM_INGOT.get()))
                .save(pFinishedRecipeConsumer);
    }
}

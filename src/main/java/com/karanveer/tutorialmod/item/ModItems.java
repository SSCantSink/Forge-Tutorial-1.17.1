package com.karanveer.tutorialmod.item;

import com.karanveer.tutorialmod.TutorialMod;
import com.karanveer.tutorialmod.item.custom.SmartBlowTorchItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    /**
     * List the Forge keeps track for us of what items we have actually
     * created.
     */
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    // Item Titanium Ingot has been added to game (accessed through custom Tutorial Mod Tab of
    // Creative mode)
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    // Interesting builder kind of thing to make items have a certain food property thingamabob
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2f).build())));

    public static final RegistryObject<Item> SMART_BLOW_TORCH = ITEMS.register("smart_blow_torch",
            () -> new SmartBlowTorchItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).durability(64)));


    /**
     * Used so Forge can know about this list
     * @param eventBus tells forge the list of items for our Tutorial Mod
     */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}

package com.karanveer.tutorialmod.item;

import com.karanveer.tutorialmod.TutorialMod;
import com.karanveer.tutorialmod.item.custom.CoalCokeItem;
import com.karanveer.tutorialmod.item.custom.SmartBlowTorchItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

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
    public static final RegistryObject<Item> TOMATO = registerFoodItem("tomato", 2, 0.2f,
            "tooltip.item.tutorialmod.tomato");

    public static final RegistryObject<Item> SMART_BLOW_TORCH = ITEMS.register("smart_blow_torch",
            () -> new SmartBlowTorchItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB).durability(64)));

    public static final RegistryObject<Item> COAL_COKE = ITEMS.register("coal_coke",
            () -> new CoalCokeItem(new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));


    // Tools and stuff
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword",
            () -> new SwordItem(ModTiers.TITANIUM, 2, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe",
            () -> new PickaxeItem(ModTiers.TITANIUM, 0, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel",
            () -> new ShovelItem(ModTiers.TITANIUM, 1, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe",
            () -> new AxeItem(ModTiers.TITANIUM, 5, -0.4f,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe",
            () -> new HoeItem(ModTiers.TITANIUM, -3, 1f,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));


    // Armor items
    public static final RegistryObject<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));

    public static final RegistryObject<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet",
            () -> new ArmorItem(ModArmorMaterial.TITANIUM, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.TUTORIAL_TAB)));


    // Registers a food with so-n-so nutrition and saturation under the custom creative mod tab tutorial tab
    public static RegistryObject<Item> registerFoodItem(String name, int nutrition, float saturation) {
        RegistryObject<Item> toReturn = ModItems.ITEMS.register(name, () -> new Item(new Item.Properties()
                .tab(ModCreativeModeTab.TUTORIAL_TAB)
                .food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build())));

        return toReturn;
    }

    // Registers a food with so-n-so nutrition and saturation under the custom creative mod tab tutorial tab and tooltip
    public static RegistryObject<Item> registerFoodItem(String name, int nutrition, float saturation,
                                                        String tooltipKey) {

        RegistryObject<Item> toReturn = ModItems.ITEMS.register(name, () -> new Item(new Item.Properties()
                .tab(ModCreativeModeTab.TUTORIAL_TAB)
                .food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build())) {

            @Override
            public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
                tooltip.add(new TranslatableComponent(tooltipKey));
                super.appendHoverText(stack, level, tooltip, flagIn);
            }
        });

        return toReturn;

    }

    /**
     * Used so Forge can know about this list
     * @param eventBus tells forge the list of items for our Tutorial Mod
     */
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}

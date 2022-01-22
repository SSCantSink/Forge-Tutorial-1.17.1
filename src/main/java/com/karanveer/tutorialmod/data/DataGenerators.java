package com.karanveer.tutorialmod.data;

import com.karanveer.tutorialmod.TutorialMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        // Says hey, actually do add these recipes too my dude.
        generator.addProvider(new ModRecipeProvider(generator));

        // ay we got the blocks loot tables
        generator.addProvider(new ModLootTableProvider(generator));
    }

}

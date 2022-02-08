package com.karanveer.tutorialmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LightningStrikerEnchantment extends Enchantment {

    // the ... is there so it can accept an array or just one thing.
    protected LightningStrikerEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    // Method happens when something gets attacked by an item enchanted with this
    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if ( !pAttacker.level.isClientSide()) { // we are in the server
            ServerLevel world = (ServerLevel) pAttacker.level;
            ServerPlayer player = ((ServerPlayer) pAttacker);
            BlockPos position = pTarget.blockPosition();

            if (pLevel == 1) { // spawn 1 lightning bolt if level 1
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, position,
                        MobSpawnType.TRIGGERED, true, true);
            }

            if (pLevel == 2) { // spawn 2 if level 2
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, position,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, position,
                        MobSpawnType.TRIGGERED, true, true);
            }
        };
    }

    // This enchantment has a max of 2 levels I and II
    // also determines how many enchantment books are generated
    @Override
    public int getMaxLevel() {
        return 2;
    }
}

package com.karanveer.tutorialmod.block.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SpeedyBlock extends Block {
    public SpeedyBlock(Properties p_49795_) {
        super(p_49795_);
    }

    // This method below is deprecated.
    // For ModBlocks/ModItems, you can implement them, however you should not call them in
    // your code
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer,
                                 InteractionHand pHand, BlockHitResult pHit) {

        // not in the server side (also so use won't be called twice)
        if (!pLevel.isClientSide()) {

            // For some reason this method is called twice for both hands
            if (pHand == InteractionHand.MAIN_HAND) {
                pPlayer.sendMessage(new TextComponent("Hello I have been right-clicked from main hand"),
                        Util.NIL_UUID);
            }

            if (pHand == InteractionHand.OFF_HAND) {
                pPlayer.sendMessage(new TextComponent("Hello I have been right-clicked from off hand"),
                        Util.NIL_UUID);
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    // Happens when an entity walks over this block
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {

        // Check if we are on the server side
        if (!pLevel.isClientSide()) {

            // We can only add potion effects to living entities
            if (pEntity instanceof LivingEntity) {
                LivingEntity entity = ((LivingEntity) pEntity);

                // add effect of movement speed on that mob for 10 seconds
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}

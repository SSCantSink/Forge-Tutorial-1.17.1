package com.karanveer.tutorialmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.karanveer.tutorialmod.block.ModBlocks;
import com.karanveer.tutorialmod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class SmartBlowTorchItem extends Item {

    /**
     * This is used to see what blocks give what when right-clicked by
     * the Blow Torch
     */
    private static final Map<Block, Item> BLOW_TORCH_ITEM_CRAFT =
            new ImmutableMap.Builder<Block, Item>()
                    .put(ModBlocks.TITANIUM_BLOCK.get(), ModItems.TITANIUM_NUGGET.get())
                    .put(Blocks.SAND, Blocks.GLASS.asItem())
                    .build();


    public SmartBlowTorchItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        // Make sure we are working on the server side here
        if (!pContext.getLevel().isClientSide()) {

            // This will be used later in order to spawn items into a level
            Level level = pContext.getLevel();

            // Grab the block that was clicked on using pContext
            BlockPos positionClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(positionClicked).getBlock();

            // If the block Clicked can be Blow Torched
            if (canBlowTorch(blockClicked)) {

                // Create a new Item (ItemStack since there can be multiple)
                ItemEntity entityItem = new ItemEntity(level,
                        positionClicked.getX(), positionClicked.getY(), positionClicked.getZ(),
                        new ItemStack(BLOW_TORCH_ITEM_CRAFT.get(blockClicked), 1));

                // Destroy the block that is being clicked.
                level.destroyBlock(positionClicked, false);

                // Actually spawn the item that drops
                level.addFreshEntity(entityItem);

                // Grab the Item that is in the player's hand and damage it
                // If the item will break, broadcast that break event to the player so that
                // particular animation will play.
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), p -> {
                    p.broadcastBreakEvent(pContext.getHand());
                });
            } else { // Can't blowtorch this item
                pContext.getPlayer().sendMessage(new TextComponent("Cannot blow torch this Block!"),
                        Util.NIL_UUID);
            }

        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        // Show additional text if shift is held down
        if (Screen.hasShiftDown()) {

            // the pKey is what we need to supply in en_us.json to translate
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.blowtorch"));

        } else {
            pTooltipComponents.add(new TranslatableComponent("tooltip.tutorialmod.blowtorch_shift"));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    // Searches through the map to see if a certain block can be blowtorched.
    private boolean canBlowTorch(Block block) {
        return BLOW_TORCH_ITEM_CRAFT.containsKey(block);
    }
}

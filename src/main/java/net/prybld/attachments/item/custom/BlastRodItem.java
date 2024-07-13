package net.prybld.attachments.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.prybld.attachments.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlastRodItem extends Item {

    public BlastRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if(isValuableBlock(blockBelow)){
                    outValueCords(positionClicked.below(i), player, blockBelow);
                    foundBlock = true;
                }
            }
            if(!foundBlock){
                player.sendMessage(new TranslatableComponent( "item.attachments.blast_rod.no_minerals"), player.getUUID());
            }
        }

        

        return super.useOn(pContext);
    }

    private void outValueCords(BlockPos blockPos, Player player, Block blockBelow){
        player.sendMessage(new TextComponent("Found " + blockBelow.getName().getString() + " at " + blockPos.getY()), player.getUUID());

    }

    private boolean isValuableBlock(Block block){
        return ModTags.Blocks.BLAST_ROD_VALUABLES.contains(block);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level plevel, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(pStack, plevel, components, tooltipFlag);
        components.add(new TranslatableComponent("tooltip.attachments.blast_rod"));
    }
}

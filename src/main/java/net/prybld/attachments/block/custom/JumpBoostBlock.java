package net.prybld.attachments.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;
import net.prybld.attachments.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class JumpBoostBlock extends Block {

    int jumpStr = 5;

    public JumpBoostBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            jumpStr++;
            if(jumpStr == 11){
                jumpStr = 2;
            }
            player.sendMessage(new TextComponent("Set jump hight to " + jumpStr), player.getUUID());
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void attack(BlockState state, Level world, BlockPos pos, Player player) {
        if (!world.isClientSide()) {
            jumpStr--;
            if(jumpStr == 1){
                jumpStr = 10;
            }
            player.sendMessage(new TextComponent("Set jump hight to " + jumpStr), player.getUUID());
        }
        super.attack(state, world, pos, player);
    }


    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide()){
            if(pEntity instanceof LivingEntity){
                LivingEntity entity = ((LivingEntity) pEntity);
                entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 1, jumpStr-1));
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }


}

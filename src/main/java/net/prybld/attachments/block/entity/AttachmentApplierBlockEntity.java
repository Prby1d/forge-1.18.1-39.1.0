package net.prybld.attachments.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.prybld.attachments.item.ModItems;
import net.prybld.attachments.screen.AttachmentApplierMenu;
import net.prybld.attachments.util.ModTags;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class AttachmentApplierBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public AttachmentApplierBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.ATTACHMENT_APPLIER.get(), pPos, pState);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Attachment Applier");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new AttachmentApplierMenu(id, inventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public static void tick(Level level, BlockPos pos, BlockState state, AttachmentApplierBlockEntity entity) {
        // Tick logic
    }

    private static boolean hasRecipe(AttachmentApplierBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasAttachmentInFirstSlot = entity.itemHandler.getStackInSlot(0).is(ModTags.Items.ATTACHMENT);
        boolean hasAccessoryInSecondSlot = entity.itemHandler.getStackInSlot(1).is(ModTags.Items.APPLICABLE_ACCESSORIES);

        return hasAttachmentInFirstSlot && hasAccessoryInSecondSlot;
    }

    private static void craftItem(AttachmentApplierBlockEntity entity) {
        entity.itemHandler.extractItem(0, 1, false);
        entity.itemHandler.extractItem(1, 1, false);

        ItemStack resultItem = new ItemStack(entity.itemHandler.getStackInSlot(1).getItem());
        // Add NBT data to resultItem here

        entity.itemHandler.setStackInSlot(2, resultItem);
    }
}

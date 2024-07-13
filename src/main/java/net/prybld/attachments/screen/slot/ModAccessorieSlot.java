package net.prybld.attachments.screen.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.prybld.attachments.util.ModTags;

public class ModAccessorieSlot extends SlotItemHandler {
    public ModAccessorieSlot(IItemHandler itemHandler, int index, int x, int y) {
        super(itemHandler, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(ModTags.Items.APPLICABLE_ACCESSORIES);
    }
}

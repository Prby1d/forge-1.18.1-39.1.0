package net.prybld.attachments.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.prybld.attachments.block.ModBlock;

public class ModCreativeModeTab {
    public static final CreativeModeTab ATTACHMENTS_TAB = new CreativeModeTab("attachmentstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlock.ATTACHMENT_APPLIER.get());
        }
    };
}

package net.prybld.attachments.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Attachment extends Item {

    public Attachment(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level plevel, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(pStack, plevel, components, tooltipFlag);
        components.add(new TranslatableComponent("tooltip.attachments."+this));
    }
}

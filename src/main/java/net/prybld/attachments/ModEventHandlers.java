package net.prybld.attachments;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prybld.attachments.block.ModBlock;

@Mod.EventBusSubscriber(modid = attachments.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ItemStack fuelStack = event.getItemStack();
        // Check if the fuel item is your custom block item
        if (fuelStack.getItem() == ModBlock.JUMP_BOOST.get().asItem()) {
            // Set the burn time for the custom block (e.g., 1600 ticks, which is equivalent to 1 item smelted)
            event.setBurnTime(100);
        }
    }
}

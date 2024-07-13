package net.prybld.attachments.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.prybld.attachments.attachments;
import net.prybld.attachments.item.custom.Attachment;
import net.prybld.attachments.item.custom.BlastRodItem;

public class ModItems{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, attachments.MOD_ID);

    public static final RegistryObject<Item> COMPACT_FURNACE = ITEMS.register("compact_furnace",
            () -> new Attachment(new Item.Properties().tab(ModCreativeModeTab.ATTACHMENTS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> STRAYS_CLOTH = ITEMS.register("strays_cloth",
            () -> new Attachment(new Item.Properties().tab(ModCreativeModeTab.ATTACHMENTS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> SNORKELING = ITEMS.register("snorkeling",
            () -> new Attachment(new Item.Properties().tab(ModCreativeModeTab.ATTACHMENTS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> BLAST_ROD = ITEMS.register("blast_rod",
            () -> new BlastRodItem(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus)    {
        ITEMS.register(eventBus);
    }
}

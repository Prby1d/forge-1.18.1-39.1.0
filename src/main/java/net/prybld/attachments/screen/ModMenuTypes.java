package net.prybld.attachments.screen;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.prybld.attachments.attachments;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, attachments.MOD_ID);

    public static final RegistryObject<MenuType<AttachmentApplierMenu>> ATTACHMENT_APPLIER_MENU =
            MENUS.register("attachment_applier", () -> IForgeMenuType.create(AttachmentApplierMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}


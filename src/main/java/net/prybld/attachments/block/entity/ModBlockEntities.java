package net.prybld.attachments.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.prybld.attachments.attachments;
import net.prybld.attachments.block.ModBlock;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, attachments.MOD_ID);

    public static final RegistryObject<BlockEntityType<AttachmentApplierBlockEntity>> ATTACHMENT_APPLIER =
            BLOCK_ENTITIES.register("attachment_applier", () ->
                    BlockEntityType.Builder.of(AttachmentApplierBlockEntity::new,
                            ModBlock.ATTACHMENT_APPLIER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

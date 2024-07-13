package net.prybld.attachments.util;

import net.prybld.attachments.attachments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class ModTags {
    public static class Blocks {
        public static final Tags.IOptionalNamedTag<Block> BLAST_ROD_VALUABLES =
                tag("blast_rod_valuables");

        public static final Tags.IOptionalNamedTag<Block> ATTACHMENT =
                tag("attachment");

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(new ResourceLocation(attachments.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final Tags.IOptionalNamedTag<Item> ATTACHMENT =
                tag("attachment");

        // New tags for each accessory type
        public static final Tags.IOptionalNamedTag<Item> HELMETS =
                tag("helmets");
        public static final Tags.IOptionalNamedTag<Item> CHESTPLATES =
                tag("chestplates");
        public static final Tags.IOptionalNamedTag<Item> LEGGINGS =
                tag("leggings");
        public static final Tags.IOptionalNamedTag<Item> BOOTS =
                tag("boots");
        public static final Tags.IOptionalNamedTag<Item> SWORDS =
                tag("swords");
        public static final Tags.IOptionalNamedTag<Item> PICKAXES =
                tag("pickaxes");
        public static final Tags.IOptionalNamedTag<Item> AXES =
                tag("axes");
        public static final Tags.IOptionalNamedTag<Item> SHOVELS =
                tag("shovels");
        public static final Tags.IOptionalNamedTag<Item> HOES =
                tag("hoes");

        // New tag for all applicable accessories
        public static final Tags.IOptionalNamedTag<Item> APPLICABLE_ACCESSORIES =
                tag("attachment_applicable_accessories");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(new ResourceLocation(attachments.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}

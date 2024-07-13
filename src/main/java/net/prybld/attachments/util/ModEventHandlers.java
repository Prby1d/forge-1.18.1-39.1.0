package net.prybld.attachments.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.prybld.attachments.attachments;

@Mod.EventBusSubscriber(modid = attachments.MOD_ID)
public class ModEventHandlers {

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (event.getProjectile() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getProjectile();
            if (arrow.getOwner() instanceof Player) {
                Player shooter = (Player) arrow.getOwner();
                ItemStack bow = shooter.getMainHandItem();
                if (bow.getItem() == Items.BOW || bow.getItem() == Items.CROSSBOW) {
                    if (!EnchantmentHelper.getEnchantments(bow).containsKey(Enchantments.INFINITY_ARROWS)) {
                        if (event.getRayTraceResult().getType() == net.minecraft.world.phys.HitResult.Type.ENTITY) {
                            net.minecraft.world.phys.EntityHitResult entityHitResult = (net.minecraft.world.phys.EntityHitResult) event.getRayTraceResult();
                            Entity hitEntity = entityHitResult.getEntity();
                            if (hitEntity instanceof LivingEntity) {
                                LivingEntity hitLivingEntity = (LivingEntity) hitEntity;
                                hitLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3, 2));
                            }
                        }
                    }
                }
            }
        }
    }
}

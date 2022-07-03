package committee.nova.technototem.event;

import com.redgrapefruit.itemnbt3.DataClient;
import committee.nova.technototem.data.TotemOfTechnobladeData;
import committee.nova.technototem.item.TotemOfTechnobladeItem;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerDeathEvent {
    public static boolean onAllowDeath(ServerPlayerEntity player, DamageSource damageSource, float damageAmount) {
        AtomicBoolean bl = new AtomicBoolean(true);
        if (player.getOffHandStack().getItem() instanceof TotemOfTechnobladeItem) {
            Random random = new Random();
            if (random.nextInt(100) != 0) {
                DataClient.use(TotemOfTechnobladeData::new, player.getOffHandStack(), (data) -> {
                    if (data.getKills() >= 1) {
                        ignoreDeath(player, damageSource);
                        data.setKills(data.getKills() - 1);
                        bl.set(false);
                    }
                });
            } else {
                player.sendMessage(Text.translatable("message.technototem.blessed"), true);
                ignoreDeath(player, damageSource);
                bl.set(false);
            }
        }
        return bl.get();
    }

    private static void ignoreDeath(ServerPlayerEntity player, DamageSource damageSource) {
        player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
        player.sendMessage(Text.literal("<Technoblade> NOT EVEN CLOSE! TECHNOBLADE NEVER DIES!"));
        player.setHealth(5.0f);
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 1200));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600));
        if (damageSource.isOutOfWorld()) {
            player.teleport(player.getX(), player.getY() + 1024, player.getZ());
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 6000));
        }
        if (damageSource.isFire()) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1200));
        }
        player.getWorld().playSound(null, player.getBlockPos(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1f, 1f);
    }
}

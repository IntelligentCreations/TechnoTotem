package committee.nova.technototem.event;

import com.redgrapefruit.itemnbt3.DataClient;
import committee.nova.technototem.data.TotemOfTechnobladeData;
import committee.nova.technototem.item.TotemOfTechnobladeItem;
import committee.nova.technototem.util.TechnobladeWordsUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class KilledEntityEvent {
    public static void onAfterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        if (entity instanceof PlayerEntity player) {
            if (killedEntity.getMaxHealth() >= 20) {
                if (player.getOffHandStack().getItem() instanceof TotemOfTechnobladeItem) {
                    DataClient.use(TotemOfTechnobladeData::new, player.getOffHandStack(), (data) ->
                            data.setKills(data.getKills() + 1)
                    );
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
                    player.sendMessage(Text.literal("<Technoblade> " + TechnobladeWordsUtil.genRandomTechnoWords()));
                }
            }
        }
    }
}

package committee.nova.technototem.item;

import com.redgrapefruit.itemnbt3.DataClient;
import committee.nova.technototem.data.TotemOfTechnobladeData;
import committee.nova.technototem.util.TechnobladeWordsUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TotemOfTechnobladeItem extends Item {
    public TotemOfTechnobladeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PIG_AMBIENT, SoundCategory.PLAYERS, 1f, 1f);
            user.sendMessage(Text.literal("<Technoblade> " + TechnobladeWordsUtil.genRandomTechnoWords()));
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        DataClient.use(TotemOfTechnobladeData::new, stack, (data) ->
                tooltip.add(Text.translatable("tooltip.technototem.kills", data.getKills()).formatted(Formatting.GOLD))
        );
        tooltip.add(Text.literal("Technoblade never dies!").formatted(Formatting.GRAY, Formatting.ITALIC));
    }
}

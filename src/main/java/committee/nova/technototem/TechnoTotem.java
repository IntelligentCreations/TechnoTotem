package committee.nova.technototem;

import committee.nova.technototem.event.KilledEntityEvent;
import committee.nova.technototem.event.PlayerDeathEvent;
import committee.nova.technototem.item.TotemOfTechnobladeItem;
import committee.nova.technototem.util.TechnobladeWordsUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TechnoTotem implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("TechnoTotem");
	public static final TotemOfTechnobladeItem TECHNO_TOTEM = new TotemOfTechnobladeItem(new FabricItemSettings().group(ItemGroup.COMBAT));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("technototem", "totem_of_technoblade"), TECHNO_TOTEM);
		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(KilledEntityEvent::onAfterKilledOtherEntity);
		ServerPlayerEvents.ALLOW_DEATH.register(PlayerDeathEvent::onAllowDeath);
		LOGGER.info(TechnobladeWordsUtil.genRandomTechnoWords());
	}
}

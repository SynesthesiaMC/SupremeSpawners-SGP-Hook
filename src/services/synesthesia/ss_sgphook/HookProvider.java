package services.synesthesia.ss_sgphook;

import java.util.Objects;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;

import life.savag3.supremespawners.struct.SpawnerType;
import life.savag3.supremespawners.utilities.SpawnerItem;
import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.spawner.external.provider.ExternalSpawnerProvider;

public class HookProvider implements ExternalSpawnerProvider {

	private Main plugin;
	
	public HookProvider(Main plugin) {
		this.plugin = Objects.<Main>requireNonNull(plugin, "plugin must not be null!");
	}
	
	public void register() {
		ShopGuiPlusApi.registerSpawnerProvider(this);
	}
	
	@Override
	public String getName() {
		PluginDescriptionFile description = this.plugin.getDescription();
	    return description.getPrefix();
	}

	@Override
	public EntityType getSpawnerEntityType(ItemStack type) {
		return SpawnerItem.getSpawnerType(type).getType();
	}

	@Override
	public ItemStack getSpawnerItem(EntityType entityType) {
		String entity = entityType.toString();
		SpawnerType spawner = SpawnerType.fromString(entity);
		return SpawnerItem.getSpawnerItem(spawner, 0, 0);
	}

}

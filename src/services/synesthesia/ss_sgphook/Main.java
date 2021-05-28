package services.synesthesia.ss_sgphook;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		final Date date = new Date();
		final long startTime = date.getTime();

		this.getLogger().info("------------[SYNESTHESIA-ADDONS]-------------");

		this.getLogger().info(" - Enabling Supreme Spawners ShopguiPlus Hook");

		if (this.checkHook("ShopGUIPlus")) {
			this.getLogger().warning(" - Could not find the ShopGUIPlus plugin...");
			this.getLogger().warning(" - Disabling...");
			setEnabled(false);
			return;
		}

		if (this.checkHook("SupremeSpawners")) {
			this.getLogger().warning(" - Could not find the SupremeSpawners plugin...");
			this.getLogger().warning(" - Disabling...");
			setEnabled(false);
			return;
		}
		
		HookProvider hookProvider = new HookProvider(this);
	    hookProvider.register();
		
		this.getLogger().info("");
		final Date date2 = new Date();
		final long endTime = date2.getTime();
		this.getLogger().info("Plugin successfully loaded in " + (endTime - startTime) + "ms.");

		this.getLogger().info("------------[SYNESTHESIA-ADDONS]-------------");

	}

	@Override
	public void onDisable() {

	}

	private boolean checkHook(String pluginName) {
		PluginManager manager = Bukkit.getPluginManager();
		Plugin plugin = manager.getPlugin(pluginName);
		if (plugin == null || !plugin.isEnabled())
			return true;
		PluginDescriptionFile description = plugin.getDescription();
		String fullName = description.getFullName();
		this.getLogger().info(" - Successfully hooked into plugin '" + fullName + "'.");
		return false;
	}

}

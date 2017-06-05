package de.rasmusantons.moles;

import de.rasmusantons.moles.default_kits.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;

import java.util.ArrayList;
import java.util.List;

public class Config {

	public final static String KEY_KITS = "kits";

	private Main main;

	public Config(Main main) {
		this.main = main;
		List<Kit> defaultKits = new ArrayList<>();
		defaultKits.add(Archer.createInstance());
		defaultKits.add(Bomber.createInstance());
		defaultKits.add(Enchanter.createInstance());
		defaultKits.add(Healer.createInstance());
		defaultKits.add(Pyro.createInstance());
		defaultKits.add(Warrior.createInstance());
		main.getConfig().addDefault(KEY_KITS, defaultKits);
		main.getConfig().options().copyDefaults(true);
		main.saveConfig();
	}

	public List<Kit> getKits() {
		return (List<Kit>) main.getConfig().get(KEY_KITS);
	}
}

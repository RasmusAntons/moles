package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Troll {
	private static String name = "Troll";
	private static String description = "End portal block";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.ENDER_PORTAL));
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

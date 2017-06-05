package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Bomber {
	private static String name = "Bomber";
	private static String description = "10 TNT";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.TNT, 10));
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

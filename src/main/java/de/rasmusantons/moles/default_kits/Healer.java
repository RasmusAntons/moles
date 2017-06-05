package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Healer {
	private static String name = "Healer";
	private static String description = "2 golden apples";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.GOLDEN_APPLE, 2));
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

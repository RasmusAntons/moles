package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Archer {
	private static String name = "Archer";
	private static String description = "Bow\n32 Arrows";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.BOW));
		items.add(new ItemStack(Material.ARROW, 32));
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

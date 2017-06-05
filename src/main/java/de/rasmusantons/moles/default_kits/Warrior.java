package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Warrior {
	private static String name = "Warrior";
	private static String description = "Diamond sword";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.DIAMOND_SWORD));
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

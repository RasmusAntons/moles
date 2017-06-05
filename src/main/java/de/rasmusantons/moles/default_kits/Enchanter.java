package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Enchanter {
	private static String name = "Enchanter";
	private static String description = "Enchantment table\n32 exp bottles";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.ENCHANTMENT_TABLE));
		items.add(new ItemStack(Material.EXP_BOTTLE, 32));
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

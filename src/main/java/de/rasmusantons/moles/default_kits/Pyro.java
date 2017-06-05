package de.rasmusantons.moles.default_kits;

import de.rasmusantons.moles.Kit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Pyro {
	private static String name = "Pyro";
	private static String description = "5 lava buckets\nIron chestplate with Fire Protection 3";
	private static List<ItemStack> items;

	static {
		items = new ArrayList<>();
		items.add(new ItemStack(Material.LAVA_BUCKET));
		items.add(new ItemStack(Material.LAVA_BUCKET));
		items.add(new ItemStack(Material.LAVA_BUCKET));
		items.add(new ItemStack(Material.LAVA_BUCKET));
		items.add(new ItemStack(Material.LAVA_BUCKET));
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		chestplate.addEnchantment(Enchantment.PROTECTION_FIRE, 3);
		items.add(chestplate);
	}

	public static Kit createInstance() {
		return new Kit(name, description, items);
	}
}

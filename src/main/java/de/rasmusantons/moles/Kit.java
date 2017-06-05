package de.rasmusantons.moles;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kit implements Cloneable, ConfigurationSerializable {
	private String name;
	private String description;
	private List<ItemStack> items;

	public Kit(String name, String description, List<ItemStack> items) {
		this.name = name;
		this.description = description;
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public List<ItemStack> getItems() {
		return items;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> kit = new HashMap<>();
		kit.put("items", items);
		kit.put("description", description);
		kit.put("name", name);
		return kit;
	}

	public static Kit deserialize(Map<String, Object> args) {
		return new Kit((String) args.get("name"),
				(String) args.get("description"),
				(List<ItemStack>) args.get("items")
		);
	}
}

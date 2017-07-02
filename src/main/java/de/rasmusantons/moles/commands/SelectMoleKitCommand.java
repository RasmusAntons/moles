package de.rasmusantons.moles.commands;

import de.rasmusantons.moles.Kit;
import de.rasmusantons.moles.Main;
import de.rasmusantons.moles.MoleInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;

public class SelectMoleKitCommand implements CommandExecutor {

	private Main main;

	public SelectMoleKitCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(ChatColor.RED + "You're not a real mole!");
			return true;
		}
		Player player = (Player) commandSender;
		MoleInfo moleInfo = main.getMoles().get(player.getUniqueId());
		if (moleInfo == null) {
			commandSender.sendMessage(ChatColor.RED + "You're not a real mole!");
			return true;
		}
		if (moleInfo.hasKit()) {
			commandSender.sendMessage(ChatColor.RED + "You already chose the kit " + moleInfo.getKit().getName() + ".");
			return true;
		}
		if (args.length != 1) {
			commandSender.sendMessage(ChatColor.RED + "Usage: /selectmolekit <kit number>");
			return true;
		}
		int kitNumber;
		try {
			kitNumber = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			commandSender.sendMessage(ChatColor.RED + "Invalid kit number: " + args[0]);
			return true;
		}
		if (kitNumber < 0 || kitNumber > main.getKits().size()) {
			commandSender.sendMessage(ChatColor.RED + "Invalid kit number: " + kitNumber);
			return true;
		}
		Kit kit = main.getKits().get(kitNumber);
		PlayerInventory inventory = player.getInventory();
		for (ItemStack item : kit.getItems()) {
			HashMap<Integer, ItemStack> extraItems = inventory.addItem(item);
			for (ItemStack extraItem : extraItems.values()) {
				player.getWorld().dropItem(player.getLocation().add(0, 1, 0), extraItem);
			}
		}
		moleInfo.setKit(kit);
		return true;
	}
}

package de.rasmusantons.moles.commands;

import de.rasmusantons.moles.Main;
import de.rasmusantons.moles.MoleInfo;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

public class ListMolesCommand implements CommandExecutor {

	private Main main;

	public ListMolesCommand(Main main) {
		this.main = main;
	}

	public boolean checkPermission(Player player, boolean bypass) {
		if (bypass)
			return  player.hasPermission("moles.bypasslist");
		MoleInfo moleInfo = main.getMoles().get(player);
		if (moleInfo == null)
			return false;
		return true;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(ChatColor.RED + "only players can use this");
			return true;
		}
		Player player = (Player) commandSender;
		if (!checkPermission(player, args.length > 0 && args[0].equals("bypass"))) {
			commandSender.sendMessage(ChatColor.RED + "only moles can use this command");
			return true;
		}
		ComponentBuilder messageBuilder = new ComponentBuilder("Moles: ")
				.color(net.md_5.bungee.api.ChatColor.GOLD).bold(true);
		for (Map.Entry<Player, MoleInfo> entry : main.getMoles().entrySet()) {
			messageBuilder
					.append(entry.getKey().getName() + " ")
					.color(net.md_5.bungee.api.ChatColor.BLUE).bold(false);
			if (entry.getValue().hasKit())
				messageBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(entry.getValue().getKit().getName()).create()));
			else
				messageBuilder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("no kit").create()));
		}
		player.spigot().sendMessage(messageBuilder.create());
		return true;
	}
}

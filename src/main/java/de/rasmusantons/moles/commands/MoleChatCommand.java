package de.rasmusantons.moles.commands;

import de.rasmusantons.moles.Main;
import de.rasmusantons.moles.MoleInfo;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class MoleChatCommand implements CommandExecutor {

	private Main main;

	public MoleChatCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage(ChatColor.RED + "only players can use this");
			return true;
		}
		Player player = (Player) commandSender;
		MoleInfo moleInfo = main.getMoles().get(player.getUniqueId());
		if (moleInfo == null) {
			commandSender.sendMessage(ChatColor.RED + "only moles can use this");
			return true;
		}
		StringBuilder message = new StringBuilder(ChatColor.RED + "[MOLECHAT] " + ChatColor.RESET + player.getName() + ": ");
		for (String arg : args)
			message.append(arg).append(" ");
		for (UUID moleID : main.getMoles().keySet()) {
			Player target = main.getServer().getPlayer(moleID);
			if (target != null) {
				target.sendMessage(message.toString());
			}
		}
		return true;
	}
}

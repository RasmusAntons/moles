package de.rasmusantons.moles.commands;

import de.rasmusantons.moles.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitMolesCommand implements CommandExecutor {

	private Main main;

	public InitMolesCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
		if (!commandSender.hasPermission("moles.init")) {
			commandSender.sendMessage(ChatColor.RED + "missing permission");
			return true;
		}
		if (main.isInitialized()) {
			commandSender.sendMessage(ChatColor.RED + "Moles has already been initialized");
			return true;
		}
		main.initMoles();
		return true;
	}
}

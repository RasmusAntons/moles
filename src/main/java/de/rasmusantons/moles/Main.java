package de.rasmusantons.moles;

import de.rasmusantons.moles.commands.InitMolesCommand;
import de.rasmusantons.moles.commands.ListMolesCommand;
import de.rasmusantons.moles.commands.MoleChatCommand;
import de.rasmusantons.moles.commands.SelectMoleKitCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class Main extends JavaPlugin {

	private List<Kit> kits;
	private Config config;
	private Map<UUID, MoleInfo> moles;
	private boolean initialized = false;
	private BaseComponent[] molesMessage;

	@Override
	public void onEnable() {
		config = new Config(this);
		kits = config.getKits();
		moles = new HashMap<>();
		getCommand("initmoles").setExecutor(new InitMolesCommand(this));
		getCommand("selectmolekit").setExecutor(new SelectMoleKitCommand(this));
		getCommand("listmoles").setExecutor(new ListMolesCommand(this));
		getCommand("mc").setExecutor(new MoleChatCommand(this));

		ComponentBuilder messageBuilder = new ComponentBuilder("I am sorry to inform you that you are a mole.\n")
				.color(ChatColor.GOLD).bold(true)
				.append("Use ").bold(false)
				.append("/listmoles").color(ChatColor.BLUE).event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/listmoles"))
				.append(" to see who else is a mole.\n").reset().color(ChatColor.GOLD)
				.append("Use ")
				.append("/mc").color(ChatColor.BLUE)
				.append(" to send messages to other moles.\n").color(ChatColor.GOLD)
				.append("You can select one of the following kits to help you survive. ")
				.append("Make sure to have enough free space in your inventory.");
		for (int i = 0; i < kits.size(); ++i) {
			messageBuilder.append(kits.get(i).getName() + " ")
					.color(ChatColor.BLUE)
					.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(kits.get(i).getDescription()).create()))
					.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/selectmolekit " + i));
		}
		molesMessage = messageBuilder.create();
	}

	public void initMoles() {
		Map<Team, List<Player>> teams = new HashMap<>();
		Scoreboard mainScoreboard = getServer().getScoreboardManager().getMainScoreboard();
		for (Player player : getServer().getOnlinePlayers()) {
			if (player.getGameMode() == GameMode.SURVIVAL) {
				Team team = mainScoreboard.getEntryTeam(player.getName());
				List<Player> teamMembers = teams.get(team);
				if (teamMembers == null) {
					teamMembers = new ArrayList<>();
					teamMembers.add(player);
					teams.put(mainScoreboard.getEntryTeam(player.getName()), teamMembers);
				} else {
					teamMembers.add(player);
				}
			}
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1F, 1F);
		}
		Random random = new Random();
		for (Team team : teams.keySet()) {
			List<Player> teamMembers = teams.get(team);
			Player mole = teamMembers.get(random.nextInt(teamMembers.size()));
			moles.put(mole.getUniqueId(), new MoleInfo());
			mole.spigot().sendMessage(molesMessage);
		}
		getServer().dispatchCommand(getServer().getConsoleSender(), "title @a title {\"text\":\"Moles have been chosen\",\"color\":\"gold\"}");
		initialized = true;
	}

	public Map<UUID, MoleInfo> getMoles() {
		return moles;
	}

	public List<Kit> getKits() {
		return kits;
	}

	public boolean isInitialized() {
		return initialized;
	}
}

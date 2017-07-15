package de.rasmusantons.moles;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {

	private Main main;

	public PlayerJoinHandler(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		MoleInfo info = main.getMoles().get(event.getPlayer().getUniqueId());
		if (info != null) {
			if (!info.hasKit()) {
				event.getPlayer().spigot().sendMessage(main.getMolesMessage());
			}
		}
	}
}

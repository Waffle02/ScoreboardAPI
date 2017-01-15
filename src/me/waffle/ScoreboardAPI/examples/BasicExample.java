package me.waffle.ScoreboardAPI.examples;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import me.waffle.ScoreboardAPI.Objectives;
import me.waffle.ScoreboardAPI.Score;
import me.waffle.ScoreboardAPI.ScoreboardBuilder;

public class BasicExample extends JavaPlugin implements Listener {
	private static ScoreboardBuilder builder;
	
	public void onEnable() {
		ScoreboardBuilder.initialize(this);
		
		builder = new ScoreboardBuilder();
		builder.createObjective("health", "§cHealth", "health", DisplaySlot.BELOW_NAME);
		
		Objectives sidebar = builder.createObjective("scoreboard", "§e§lWaffle", "dummy", DisplaySlot.SIDEBAR);
		sidebar.addScore(new Score(sidebar, "I'm cool, right?", 6));
		sidebar.addScore(new Score(sidebar, "Sup!", 5));
		sidebar.addScore(new Score(sidebar, "§2", 4));
		sidebar.addScore(new Score(sidebar, "Coolness Level: §b10/9", 3));
		sidebar.addScore(new Score(sidebar, "§1", 2));
		sidebar.addScore(new Score(sidebar, "§aWaffleorg", 1));
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		builder.addPlayer(player);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		builder.removePlayer(player);
	}
}


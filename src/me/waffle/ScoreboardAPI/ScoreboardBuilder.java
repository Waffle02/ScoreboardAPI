package me.waffle.ScoreboardAPI;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardBuilder {
	private static ScoreboardManager sbm;
	private static ArrayList<ScoreboardBuilder> scoreboards = new ArrayList<ScoreboardBuilder>();
	
	public static ArrayList<ScoreboardBuilder> getScoreboards() {
		return scoreboards;
	}
	
	public static void initialize(Plugin instance) {
		sbm = Bukkit.getScoreboardManager();
		
		new BukkitRunnable() {
			public void run() {
				for(ScoreboardBuilder sbb : scoreboards) {
					sbb.update();
				}
			}
		}.runTaskTimer(instance, 20, 20);
	}
	
	/*
	 * Constructor
	 */
	
	protected Scoreboard scoreboard;
	protected ArrayList<Objectives> objectives;
	protected ArrayList<Player> players;
	
	public ScoreboardBuilder() {
		this.scoreboard = sbm.getNewScoreboard();
		this.objectives = new ArrayList<Objectives>();
	}
	
	public void addPlayer(Player...players) {
		for(Player lp : players) {
			if(!this.players.contains(lp)) {
				this.players.add(lp);
				lp.setScoreboard(this.scoreboard);
			}
		} this.update();
	}
	
	public void removePlayer(Player...players) {
		for(Player lp : players) {
			if(this.players.contains(lp)) {
				this.players.remove(lp);
				lp.setScoreboard(null);
			}
		} this.update();
	}
	
	public void update() {
		for(Player lp : this.players) {
			lp.setScoreboard(this.scoreboard);
		}
	}
	
	public Objectives createObjective(String name) {
		Objectives objs = new Objectives(this.scoreboard, name, name, "dummy", null);
		this.objectives.add(objs);
		objs.setBuilder(this);
		return objs;
	}
	
	public Objectives createObjective(String name, String displayName) {
		Objectives objs = new Objectives(this.scoreboard, name, displayName, "dummy", null);
		this.objectives.add(objs);
		objs.setBuilder(this);
		return objs;
	}
	
	public Objectives createObjective(String name, String displayName, String criteria) {
		Objectives objs = new Objectives(this.scoreboard, name, displayName, criteria, null);
		this.objectives.add(objs);
		objs.setBuilder(this);
		return objs;
	}
	
	public Objectives createObjective(String name, String displayName, String criteria, DisplaySlot displaySlot) {
		Objectives objs = new Objectives(this.scoreboard, name, displayName, criteria, null);
		this.objectives.add(objs);
		objs.setBuilder(this);
		return objs;
	}
	
	/*
	 * Getters
	 */
	
	public Objectives getObjectives(String name) {
		for(Objectives lbo : this.objectives) {
			if(lbo.getName().equals(name)) {
				return lbo;
			}
		} return null;
	}
	
	public ArrayList<Objectives> getObjectives() {
		return this.objectives;
	}
}
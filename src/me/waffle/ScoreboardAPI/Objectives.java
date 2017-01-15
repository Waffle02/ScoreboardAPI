package me.waffle.ScoreboardAPI;

import java.util.ArrayList;

import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Objectives {
	protected ArrayList<Score> scores;
	protected ScoreboardBuilder sbb;
	protected Objective obj;
	protected boolean autoUpdate;
	
	public Objectives(Scoreboard sb, String name, String displayName, String criteria, DisplaySlot displaySlot) {
		this.scores = new ArrayList<Score>();
		this.obj = sb.registerNewObjective(name, criteria);
		
		this.obj.setDisplayName(displayName);
		if(displaySlot != null)
			this.obj.setDisplaySlot(displaySlot);
	}
	
	public void resetScores() {
		this.scores = new ArrayList<Score>();
	}
	
	/*
	 * Setters
	 */
	
	public Objectives addScore(Score... scores) {
		for(Score ls : scores) {
			this.scores.add(ls);
		} return this;
	}
	
	public Objectives setBuilder(ScoreboardBuilder sbb) {
		this.sbb = sbb;
		return this;
	}
	
	public Objectives setAutoUpdate(boolean value) {
		this.autoUpdate = value;
		return this;
	}
	
	public Objectives setDisplayName(String displayName) {
		this.obj.setDisplayName(displayName);
		return this;
	}
	
	public Objectives setDisplaySlot(DisplaySlot displaySlot) {
		this.obj.setDisplaySlot(displaySlot);
		return this;
	}
	
	/*
	 * Getters
	 */
	
	public ArrayList<Score> getScores() {
		return this.scores;
	}
	
	public Scoreboard getScoreboard() {
		return this.obj.getScoreboard();
	}
	
	public DisplaySlot getDisplaySlot() {
		return this.obj.getDisplaySlot();
	}
	
	public String getCriteria() {
		return this.obj.getCriteria();
	}
	
	public String getDisplay() {
		return this.obj.getDisplayName();
	}
	
	public String getName() {
		return this.obj.getName();
	}
	
	public void delete() {
		this.obj.unregister();
	}
}
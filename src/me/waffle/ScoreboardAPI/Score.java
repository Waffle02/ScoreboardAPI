package me.waffle.ScoreboardAPI;

import java.util.ArrayList;

public class Score {
	protected Objectives objs;
	protected String name;
	protected int score;
	
	public Score(Objectives objs, String name, int score) {
		this.objs = objs;
		this.name = name;
		this.score = score;
	}
	
	/*
	 * Setters
	 */
	
	public Score setScore(int score) {
		this.score = score;
		this.update();
		return this;
	}
	
	public Score setName(String name) {
		this.name = name;
		this.update();
		return this;
	}
	
	/*
	 * Getters
	 */
	
	public Objectives getObjectives() {
		return this.objs;
	}
	
	private void update() {
		if(this.objs.autoUpdate) {
			ArrayList<Score> scores = new ArrayList<Score>();
			for(Score ls : this.objs.getScores()) {
				scores.add(ls);
			}
			this.objs.resetScores();
			for(Score ls : scores) {
				this.objs.scores.add(ls);
			}
		}
	}
}
package com.readcollin0.dungeonrun.entity.stats;

public enum Attribute {
	STRENGTH(8, true),
	DEXTERITY(8, true),
	INTELLIGENCE(8, true),
	ARMOR_CLASS(10, "AC"),
	HEALTH(5, "HP"),
	MAX_HEALTH(5, "Max HP");
	
	public String shortName;
	public int defaultScore;
	public boolean checkable = false;
	
	Attribute(int defaultScore) {
		this.defaultScore = defaultScore;
		this.shortName = this.name().substring(0,3);
	}
	
	Attribute(int defaultScore, String shortName) {
		this.defaultScore = defaultScore;
		this.shortName = shortName;
	}
	
	Attribute(int defaultScore, boolean checkable) {
		this(defaultScore);
		this.checkable = checkable;
	}
	
	Attribute(int defaultScore, String shortName, boolean checkable) {
		this(defaultScore, shortName);
		this.checkable = checkable;
	}
	
}

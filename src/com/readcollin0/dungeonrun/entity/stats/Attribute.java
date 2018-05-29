package com.readcollin0.dungeonrun.entity.stats;

public enum Attribute {
	STRENGTH(8),
	DEXTERITY(8),
	INTELLIGENCE(8),
	ARMOR_CLASS(10, "AC"),
	HEALTH(5);
	
	public String shortName;
	public int defaultScore;
	
	Attribute(int defaultScore) {
		this.defaultScore = defaultScore;
		this.shortName = this.name().substring(0,3);
	}
	
	Attribute(int defaultScore, String shortName) {
		this.defaultScore = defaultScore;
		this.shortName = shortName;
	}
	
	
}

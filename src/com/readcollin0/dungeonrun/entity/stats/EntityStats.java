package com.readcollin0.dungeonrun.entity.stats;

import java.util.HashMap;

import com.readcollin0.dungeonrun.util.Dice;

public class EntityStats {
	
	private HashMap<Attribute, Integer> stats = new HashMap<Attribute, Integer>();
	
	public EntityStats() {
		for (Attribute attr : Attribute.values() ){
			stats.put(attr, attr.defaultScore);
		}
	}
	
	public EntityStats(HashMap<Attribute, Integer> customStats) {
		this();
		for (Attribute stat : customStats.keySet()) {
			stats.put(stat, customStats.get(stat));
		}
		if (customStats.containsKey(Attribute.MAX_HEALTH))
			stats.put(Attribute.MAX_HEALTH, customStats.get(Attribute.HEALTH));
	}
	
	public int skillCheck(Attribute a) {
		if (!a.checkable) return Integer.MIN_VALUE;
		return Dice.d20() + calculateModifier(a);
	}
	
	public int calculateModifier(Attribute a) {
		if (!a.checkable) return Integer.MIN_VALUE;
		return (stats.get(a)-10)/2;
	}
	
	public int getStat(Attribute stat) {
		return stats.get(stat);
	}
	
	public HashMap<Attribute, Integer> getStats() {
		return stats;
	}
	
	public void setStat(Attribute stat, int score) {
		stats.put(stat, score);
	}
	
}

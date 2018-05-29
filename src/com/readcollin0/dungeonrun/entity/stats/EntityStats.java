package com.readcollin0.dungeonrun.entity.stats;

import java.util.HashMap;

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

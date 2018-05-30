package com.readcollin0.dungeonrun.entity;

import java.util.HashMap;

import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;

public class Entity {
	
	EntityStats stats;
	
	public Entity() {
		stats = new EntityStats();
	}
	
	public Entity(EntityStats stats) {
		stats = new EntityStats(stats.getStats());
	}
	
	public Entity(HashMap<Attribute, Integer> customStats) {
		stats = new EntityStats(customStats);
	}
	
	public EntityStats getStats() {
		return stats;
	}
	
	public int getStat(Attribute a) {
		return stats.getStat(a);
	}
	
}

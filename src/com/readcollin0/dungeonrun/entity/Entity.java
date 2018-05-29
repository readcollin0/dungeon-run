package com.readcollin0.dungeonrun.entity;

import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;

public class Entity {
	
	EntityStats stats;
	
	public Entity() {
		stats = new EntityStats();
	}
	
	public EntityStats getStats() {
		return stats;
	}
	
	public int getStat(Attribute a) {
		return stats.getStat(a);
	}
	
}

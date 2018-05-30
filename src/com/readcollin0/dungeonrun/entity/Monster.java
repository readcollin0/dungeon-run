package com.readcollin0.dungeonrun.entity;

import java.util.HashMap;

import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;

public class Monster extends Entity {
	
	String name;
	int health;
	EntityStats stats;
	
	public Monster(String name) {
		this.name = name;
		stats = new EntityStats();
	}
	
	public Monster(String name, HashMap<Attribute, Integer> customStats) {
		this(name);
		stats = new EntityStats(customStats);
	}
	
	public Monster(String name, EntityStats stats) {
		this(name, stats.getStats());
	}
	
}

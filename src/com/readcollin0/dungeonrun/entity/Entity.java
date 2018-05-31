package com.readcollin0.dungeonrun.entity;

import java.util.HashMap;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;
import com.readcollin0.dungeonrun.event.entity.EntityCreateEvent;
import com.readcollin0.dungeonrun.event.entity.EntityDamageEvent;
import com.readcollin0.dungeonrun.event.entity.EntityDeathEvent;

public class Entity implements DamageSource {
	
	protected EntityStats stats;
	protected String name;
	
	{
		DungeonRun.EVENT_BUS.fire(new EntityCreateEvent(this));
	}
	
	public Entity(String name) {
		this.name = name;
		stats = new EntityStats();
	}
	
	public Entity(String name, EntityStats stats) {
		this(name);
		stats = new EntityStats(stats.getStats());
	}
	
	public Entity(String name, HashMap<Attribute, Integer> customStats) {
		this(name);
		stats = new EntityStats(customStats);
	}
	
	public EntityStats getStats() {
		return stats;
	}
	
	public int getStat(Attribute a) {
		return stats.getStat(a);
	}
	
	public void damage(int amount, DamageSource source) {
		DungeonRun.EVENT_BUS.fire(new EntityDamageEvent(this, source, amount));
		int health = stats.getStat(Attribute.HEALTH);
		health -= amount;
		stats.setStat(Attribute.HEALTH, health);
		
		if (health <= 0) {
			die();
		}
	}
	
	protected void die() {
		DungeonRun.EVENT_BUS.fire(new EntityDeathEvent(this));
		stats.setStat(Attribute.HEALTH, 0);
	}

	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}
	
}

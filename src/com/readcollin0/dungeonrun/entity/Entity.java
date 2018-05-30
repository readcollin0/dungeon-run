package com.readcollin0.dungeonrun.entity;

import java.util.HashMap;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;
import com.readcollin0.dungeonrun.event.entity.EntityCreateEvent;
import com.readcollin0.dungeonrun.event.entity.EntityDamageEvent;
import com.readcollin0.dungeonrun.event.entity.EntityDeathEvent;

public class Entity implements DamageSource {
	
	EntityStats stats;
	
	{
		DungeonRun.EVENT_BUS.fire(new EntityCreateEvent(this));
	}
	
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
	
	public void damage(int amount, DamageSource source) {
		DungeonRun.EVENT_BUS.fire(new EntityDamageEvent(this, source, amount));
		int health = stats.getStat(Attribute.HEALTH);
		health -= amount;
		stats.setStat(Attribute.HEALTH, health);
		
		if (health <= 0) {
			die();
		}
	}
	
	void die() {
		DungeonRun.EVENT_BUS.fire(new EntityDeathEvent(this));
	}

	@Override
	public int getDamage() {
		return 0;
	}
	
}

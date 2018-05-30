package com.readcollin0.dungeonrun.event.entity;

import com.readcollin0.dungeonrun.entity.DamageSource;
import com.readcollin0.dungeonrun.entity.Entity;

public class EntityDamageEvent extends EntityEvent {

	int damage;
	DamageSource source;
	
	public EntityDamageEvent(Entity entity) {
		super(entity);
		source = null;
		damage = 0;
	}
	
	public EntityDamageEvent(Entity entity, DamageSource source, int damage) {
		super(entity);
		this.source = source;
		this.damage = damage;
	}
	
	
	
}

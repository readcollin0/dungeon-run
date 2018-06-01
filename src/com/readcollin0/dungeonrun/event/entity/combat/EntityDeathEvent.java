package com.readcollin0.dungeonrun.event.entity.combat;

import com.readcollin0.dungeonrun.entity.DamageSource;
import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.event.entity.EntityEvent;

public class EntityDeathEvent extends EntityEvent {

	DamageSource source;
	
	public EntityDeathEvent(Entity entity, DamageSource source) {
		super(entity);
		this.source = source;
	}
	
	public DamageSource getDeathSource() {
		return source;
	}
	
}

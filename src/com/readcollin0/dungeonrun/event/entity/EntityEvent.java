package com.readcollin0.dungeonrun.event.entity;

import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.util.eventbus.Event;

public class EntityEvent extends Event {
	
	Entity e;
	
	public EntityEvent(Entity entity) {
		e = entity;
	}
	
	public Entity getEntity() {
		return e;
	}
	
}

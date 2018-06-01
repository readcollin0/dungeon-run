package com.readcollin0.dungeonrun.event.entity.combat;

import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.event.entity.EntityEvent;

public class EntityAttackEvent extends EntityEvent {

	protected Entity subject;
	protected boolean hit;
	protected int damage;
	
	public EntityAttackEvent(Entity attacker, Entity attackee, boolean hit, int damage) {
		super(attacker);
		this.subject = attackee;
		this.hit = hit;
		this.damage = damage;
	}
	
	public Entity getAttackee() {
		return subject;
	}
	
	public boolean didHit() {
		return hit;
	}
	
	public int getDamage() {
		return damage;
	}
	
}

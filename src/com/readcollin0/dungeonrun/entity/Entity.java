package com.readcollin0.dungeonrun.entity;

import java.util.HashMap;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;
import com.readcollin0.dungeonrun.entity.weapon.Unarmed;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;
import com.readcollin0.dungeonrun.entity.weapon.WeaponType;
import com.readcollin0.dungeonrun.event.entity.EntityCreateEvent;
import com.readcollin0.dungeonrun.event.entity.combat.EntityAttackEvent;
import com.readcollin0.dungeonrun.event.entity.combat.EntityDamageEvent;
import com.readcollin0.dungeonrun.event.entity.combat.EntityDeathEvent;
import com.readcollin0.dungeonrun.util.Dice;

public class Entity implements DamageSource {
	
	protected EntityStats stats;
	protected String name;
	protected Weapon weapon = new Unarmed();
	
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
	
	public void attack(Entity subject) {
		attack(subject, getEquippedWeapon());
	}

	public void attack(Entity subject, Weapon weapon) {
		boolean hit;
		int damage = 0;
		if (!(weapon instanceof Unarmed) && (weapon != null)) {
			boolean isMelee = weapon.getWeaponType().equals(WeaponType.MELEE);
			hit = stats.skillCheck(isMelee ? Attribute.STRENGTH : Attribute.DEXTERITY) <= subject.getStat(Attribute.ARMOR_CLASS);
			if (hit) {
				damage = weapon.getAttackDamage(true); 
				subject.damage(damage, this);
			}
		} else {
			hit = stats.skillCheck(Attribute.STRENGTH) <= subject.getStat(Attribute.ARMOR_CLASS);
			if (hit) {
				damage = Dice.d4() + stats.calculateModifier(Attribute.STRENGTH);
				subject.damage(damage, this);
			}
		}
		
		DungeonRun.EVENT_BUS.fire(new EntityAttackEvent(this, subject, hit, damage));
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
			die(source);
		}
	}
	
	protected void die(DamageSource source) {
		DungeonRun.EVENT_BUS.fire(new EntityDeathEvent(this, source));
		stats.setStat(Attribute.HEALTH, 0);
	}
	
	protected Weapon getEquippedWeapon() {
		return weapon;
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

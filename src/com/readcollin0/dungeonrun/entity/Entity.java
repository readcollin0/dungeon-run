package com.readcollin0.dungeonrun.entity;

import java.util.HashMap;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;
import com.readcollin0.dungeonrun.entity.weapon.Unarmed;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;
import com.readcollin0.dungeonrun.entity.weapon.WeaponType;
import com.readcollin0.dungeonrun.event.entity.EntityCreateEvent;
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
		Player player = DungeonRun.CONTROLLER.getMainPlayer();
		if (!(weapon instanceof Unarmed) && (weapon != null)) {
			boolean isMelee = weapon.getWeaponType().equals(WeaponType.MELEE);
			boolean hit = stats.skillCheck(isMelee ? Attribute.STRENGTH : Attribute.DEXTERITY) <= player.getStat(Attribute.ARMOR_CLASS);
			if (hit) {
				player.damage(weapon.getDamage(), this);
			}
		} else {
			boolean hit = stats.skillCheck(Attribute.STRENGTH) <= player.getStat(Attribute.ARMOR_CLASS);
			if (hit) {
				int damage = Dice.d4() + stats.calculateModifier(Attribute.STRENGTH);
				player.damage(damage, this);
			}
		}
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

	@Override
	public int getDamage() {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}
	
}

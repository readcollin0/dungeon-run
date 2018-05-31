package com.readcollin0.dungeonrun.entity.enemy;

import java.util.HashMap;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;
import com.readcollin0.dungeonrun.entity.weapon.WeaponType;
import com.readcollin0.dungeonrun.util.Dice;

public class Enemy extends Entity {
	
	protected Weapon weapon;
	
	public Enemy(String name) {
		super(name);
	}
	
	public Enemy(String name, HashMap<Attribute, Integer> customStats) {
		super(name, customStats);
	}
	
	public Enemy(String name, EntityStats stats) {
		super(name, stats);
	}
	
	public void attackPlayer() {
		Player player = DungeonRun.CONTROLLER.getMainPlayer();
		if (weapon != null) {
			boolean isMelee = weapon.getWeaponType().equals(WeaponType.MELEE);
			boolean hit = stats.skillCheck(isMelee ? Attribute.STRENGTH : Attribute.DEXTERITY) <= player.getStat(Attribute.ARMOR_CLASS);
			if (hit) {
				player.damage(weapon.getDamage(), weapon);
			}
		} else {
			boolean hit = stats.skillCheck(Attribute.STRENGTH) <= player.getStat(Attribute.ARMOR_CLASS);
			if (hit) {
				int damage = Dice.d4() + stats.calculateModifier(Attribute.STRENGTH);
				player.damage(damage, this);
			}
		}
		
		DungeonRun.CONTROLLER.isPlayersTurn = true;
	}
	
	public void setWeapon(Weapon w) {
		this.weapon = w;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	protected void die() {
		super.die();
		DungeonRun.GUI.lblMessage.setText("U dun kild mee");
	}
	
}

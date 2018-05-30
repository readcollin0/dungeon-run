package com.readcollin0.dungeonrun.entity.weapon;

import com.readcollin0.dungeonrun.entity.DamageSource;

public class Weapon implements DamageSource {

	String name;
	
	public Weapon(String name, WeaponType type) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int getDamage() {
		return 0;
	}
	
}

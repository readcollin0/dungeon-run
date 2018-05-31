package com.readcollin0.dungeonrun.entity.weapon;

import com.readcollin0.dungeonrun.entity.DamageSource;

public class Weapon implements DamageSource {

	protected String name;
	protected WeaponType type;
	
	public Weapon(String name, WeaponType type) {
		this.name = name;
		this.type = type;
	}
	
	public WeaponType getWeaponType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int getDamage() {
		return 0;
	}
	
}

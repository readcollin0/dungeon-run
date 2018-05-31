package com.readcollin0.dungeonrun.entity.weapon;

import com.readcollin0.dungeonrun.util.Dice;

public class TestWeapon extends Weapon {

	public TestWeapon() {
		super("Test Weapon", WeaponType.MELEE);
	}
	
	@Override
	public int getDamage() {
		return Dice.d6() + Dice.d6();
	}
	
	@Override
	public int getAttackDamage(boolean isMelee) {
		if (isMelee) {
			return getDamage();
		} else {
			return 0;
		}
	}
	
}

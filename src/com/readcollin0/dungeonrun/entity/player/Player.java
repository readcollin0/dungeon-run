package com.readcollin0.dungeonrun.entity.player;

import java.util.ArrayList;

import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.entity.weapon.TestWeapon;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;

public class Player extends Entity {
	
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	int weaponEquipped = 1;
	
	public Player() {
		weapons.add(new TestWeapon());
	}
	
}

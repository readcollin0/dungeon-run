package com.readcollin0.dungeonrun.entity.player;

import java.util.ArrayList;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.DamageSource;
import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.entity.enemy.Enemy;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.weapon.TestWeapon;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;
import com.readcollin0.dungeonrun.entity.weapon.WeaponType;
import com.readcollin0.dungeonrun.event.TurnTakeEvent;
import com.readcollin0.dungeonrun.event.button.ButtonClickEvent;
import com.readcollin0.dungeonrun.event.entity.player.PlayerDamageEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class Player extends Entity {
	
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	int weaponEquipped = 0;
	
	public Player() {
		super("Player");
		DungeonRun.EVENT_BUS.subscribeObject(this);
		weapons.add(new TestWeapon());
	}
	
	@Override
	public void damage(int amount, DamageSource source) {
		super.damage(amount, source);
		int health = stats.getStat(Attribute.HEALTH);
		int maxHealth = stats.getStat(Attribute.MAX_HEALTH);
		int progress = (int) ((1.0 * health) / maxHealth);
		
		DungeonRun.GUI.progressBar.setValue(progress);
	}
	
	@SubscribeEvent
	public void onPlayerDamage(PlayerDamageEvent e) {
		
	}
	
	// TODO: Add unarmed
	@SubscribeEvent
	public void onAttackButtonClicked(ButtonClickEvent e) {
		System.out.println("Event Handler Called!");
		if (!e.getButtonClicked().getText().equals("Attack")) return; // Checks to make sure the button is the attack button
		if (!DungeonRun.CONTROLLER.isPlayersTurn) return; // Checks to make sure it is players turn
		
		DungeonRun.EVENT_BUS.fire(new TurnTakeEvent());
		
		Weapon weapon = weapons.get(weaponEquipped);
		Enemy enemy = DungeonRun.CONTROLLER.getCurrentEnemy();
		
		// TODO: Implement Proficiencies and other things like them
		boolean isMelee = weapon.getWeaponType().equals(WeaponType.MELEE);
		boolean hit = stats.skillCheck(isMelee ? Attribute.STRENGTH : Attribute.DEXTERITY) <= enemy.getStat(Attribute.ARMOR_CLASS);
		if (hit) {
			int damage = weapon.getAttackDamage(true);
			enemy.damage(damage, this);
//			DungeonRun.GUI.lblMessage.setText("You hit the " + DungeonRun.CONTROLLER.getCurrentEnemy().getName() + " for " + damage + " damage!");
		}
	}
	
}

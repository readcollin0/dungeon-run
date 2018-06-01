package com.readcollin0.dungeonrun.entity.player;

import java.util.ArrayList;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.DamageSource;
import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.weapon.TestWeapon;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;
import com.readcollin0.dungeonrun.event.GameStartEvent;
import com.readcollin0.dungeonrun.event.TurnTakeEvent;
import com.readcollin0.dungeonrun.event.button.ButtonClickEvent;
import com.readcollin0.dungeonrun.event.entity.player.PlayerDamageEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class Player extends Entity {
	
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	int weaponEquipped = 0;
	
	public Player() {	
		super("Player");
		stats.setStat(Attribute.MAX_HEALTH, 20);
		stats.setStat(Attribute.HEALTH, 20);
		DungeonRun.EVENT_BUS.subscribeObject(this);
		weapons.add(new TestWeapon());
	}
	
	@Override
	public void damage(int amount, DamageSource source) {
		super.damage(amount, source);
		updateHealthBar();
	}
	
	public void updateHealthBar() {
		int health = stats.getStat(Attribute.HEALTH);
		int maxHealth = stats.getStat(Attribute.MAX_HEALTH);
		int progress = (int) (100.0 * health / maxHealth);
		
//		System.out.println(String.format("Health: %d\t\tMax Health: %d\t\tProgress: %d\t\t", health, maxHealth, progress));
		
		DungeonRun.GUI.progressBar.setValue(progress);
	}
	
	@SubscribeEvent
	public void onGameStart(GameStartEvent e) {
		updateHealthBar();
	}
	
	@SubscribeEvent
	public void onPlayerDamage(PlayerDamageEvent e) {
		
	}

	@SubscribeEvent
	public void onAttackButtonClicked(ButtonClickEvent e) {
		if (!DungeonRun.CONTROLLER.inCombat) return;
		if (!e.getButtonClicked().getText().equals("Attack")) return; // Checks to make sure the button is the attack button
		if (!DungeonRun.CONTROLLER.isPlayersTurn) return; // Checks to make sure it is players turn
		
//		Weapon weapon = weapons.get(weaponEquipped);
//		Enemy enemy = DungeonRun.CONTROLLER.getCurrentEnemy();
//		
//		// TODO: Implement Proficiencies and other things like them
//		boolean isMelee = weapon.getWeaponType().equals(WeaponType.MELEE);
//		boolean hit = stats.skillCheck(isMelee ? Attribute.STRENGTH : Attribute.DEXTERITY) <= enemy.getStat(Attribute.ARMOR_CLASS);
//		if (hit) {
//			int damage = weapon.getAttackDamage(true);
//			enemy.damage(damage, this);
////			DungeonRun.GUI.lblMessage.setText("You hit the " + DungeonRun.CONTROLLER.getCurrentEnemy().getName() + " for " + damage + " damage!");
//		}
		
		attack(DungeonRun.CONTROLLER.getCurrentEnemy(), getEquippedWeapon());
		DungeonRun.EVENT_BUS.fire(new TurnTakeEvent());
	}
	
	@Override
	protected Weapon getEquippedWeapon() {
		return weapons.get(weaponEquipped);
	}
	
}

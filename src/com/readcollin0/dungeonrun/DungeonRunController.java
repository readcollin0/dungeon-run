package com.readcollin0.dungeonrun;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.readcollin0.dungeonrun.entity.DamageSource;
import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.entity.enemy.Enemy;
import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.event.EnterCombatEvent;
import com.readcollin0.dungeonrun.event.GameStartEvent;
import com.readcollin0.dungeonrun.event.TurnTakeEvent;
import com.readcollin0.dungeonrun.event.entity.combat.EntityAttackEvent;
import com.readcollin0.dungeonrun.event.entity.combat.EntityDeathEvent;
import com.readcollin0.dungeonrun.util.CanvasButton;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class DungeonRunController {

	private Timer timer = new Timer();
	@SuppressWarnings("unused")
	private CanvasButton testButton = new CanvasButton(100, 100, 250, 100, "Test Button", true, new Font("Helvetica", Font.PLAIN, 36), new Runnable() {

		@Override
		public void run() {
			System.out.println("Button Clicked!");
		}
		
	});
	
	// TODO: Add more players
	private ArrayList<Player> players = new ArrayList<Player>();
	private Enemy currentEnemy;
	public boolean isPlayersTurn = true;
	public boolean inCombat = true;

	public DungeonRunController() {
		DungeonRun.EVENT_BUS.subscribeObject(this);
		HashMap<Attribute, Integer> stats = new HashMap<Attribute, Integer>();
		stats.put(Attribute.HEALTH, 16);
		currentEnemy = new Enemy("Test Monster", stats);
		players.add(new Player());
	}

	@SubscribeEvent
	public void onGameStart(GameStartEvent e) {
		DungeonRun.EVENT_BUS.fire(new EnterCombatEvent(players.get(0)));
	}

	public Player getMainPlayer() {
		return players.get(0);
	}

	public Enemy getCurrentEnemy() {
		return currentEnemy;
	}

	@SubscribeEvent
	public void onMonsterDie(EntityDeathEvent e) {
		DamageSource source = e.getDeathSource();
		Entity entity = e.getEntity();
		
		DungeonRun.GUI.lblMessage.setText(entity.getName() + " was killed by " + source.getName());
		
		if (entity.equals(currentEnemy)) {
			currentEnemy = null;
			inCombat = false;
		}
	}

	@SubscribeEvent
	public void onDamage(EntityAttackEvent e) {
		if (e.didHit() && e.getDamage() >= e.getAttackee().getStat(Attribute.HEALTH)) return;
		if (e.didHit()) {
			DungeonRun.GUI.lblMessage.setText(String.format("%s was hit by %s for %d damage!", e.getAttackee().getName(), e.getEntity().getName(), e.getDamage()));
		} else {
			DungeonRun.GUI.lblMessage.setText(String.format("%s tried to hit %s but missed!", e.getEntity().getName(), e.getAttackee().getName()));
		}
	}

	// TODO: Figure out something
	@SubscribeEvent
	public void onTakeTurn(TurnTakeEvent e) {
		if (currentEnemy != null) { 
			timer.schedule(new TimerTask() {
	
				@Override
				public void run() {
					if (currentEnemy != null) currentEnemy.attack(players.get(0));
				}
	
			}, 1500L);
		}
	}

}

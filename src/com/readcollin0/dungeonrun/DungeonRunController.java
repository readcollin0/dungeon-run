package com.readcollin0.dungeonrun;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.readcollin0.dungeonrun.entity.enemy.Enemy;
import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.event.GameStartEvent;
import com.readcollin0.dungeonrun.event.TurnTakeEvent;
import com.readcollin0.dungeonrun.event.entity.EntityDamageEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class DungeonRunController {

	private Timer timer = new Timer();
	
	// TODO: Add more players
	private ArrayList<Player> players = new ArrayList<Player>();
	private Enemy currentEnemy;
	public boolean isPlayersTurn = true;
	
	public DungeonRunController() {
		DungeonRun.EVENT_BUS.subscribeObject(this);
		currentEnemy = new Enemy("Test Monster");
		players.add(new Player());
	}
	
	@SubscribeEvent
	public void onGameStart(GameStartEvent e) {
	}
	
	public Player getMainPlayer() {
		return players.get(0);
	}
	
	public Enemy getCurrentEnemy() {
		return currentEnemy;
	}
	
	@SubscribeEvent
	public void onDamage(EntityDamageEvent e) {
		DungeonRun.GUI.lblMessage.setText(e.getEntity().getName() + " was hit by " + e.getDamageSource().getName() + " for " + e.getDamage() + " damage!");
	}
	
	// TODO: Figure out something
	@SubscribeEvent
	public void onTakeTurn(TurnTakeEvent e) {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				currentEnemy.attackPlayer();
				
			}
			
		}, 1500L);
	}
	
}

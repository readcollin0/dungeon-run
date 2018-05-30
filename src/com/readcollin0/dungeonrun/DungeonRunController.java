package com.readcollin0.dungeonrun;

import java.util.ArrayList;

import com.readcollin0.dungeonrun.entity.Monster;
import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.event.GameStartEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class DungeonRunController {

	// TODO: Add more players
	private ArrayList<Player> players = new ArrayList<Player>();
	private Monster currentMonster;
	
	public DungeonRunController() {
		DungeonRun.EVENT_BUS.subscribeObject(this);
	}
	
	@SubscribeEvent
	public void onGameStart(GameStartEvent e) {
		currentMonster = new Monster("Test Monster");
	}
	
	public Player getMainPlayer() {
		return players.get(0);
	}
	
	public Monster getCurrentMonster() {
		return currentMonster;
	}
	
}

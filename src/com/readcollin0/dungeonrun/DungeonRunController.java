package com.readcollin0.dungeonrun;

import com.readcollin0.dungeonrun.event.GameStartEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class DungeonRunController {

	public DungeonRunController() {
		DungeonRun.EVENT_BUS.subscribeObject(this);
	}
	
	@SubscribeEvent
	public void onGameStart(GameStartEvent e) {
		
	}
	
}

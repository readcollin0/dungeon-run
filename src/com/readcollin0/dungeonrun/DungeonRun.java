package com.readcollin0.dungeonrun;

import com.readcollin0.dungeonrun.event.GameStartEvent;
import com.readcollin0.util.eventbus.Event;
import com.readcollin0.util.eventbus.ObjectBus;

public class DungeonRun {
	
	public static final ObjectBus<Event> EVENT_BUS = new ObjectBus<Event>(); 
	public static final DungeonRunController CONTROLLER = new DungeonRunController();
	public static final DungeonRunUI GUI = new DungeonRunUI();
	
	public static void main(String[] args) {
		
		GUI.start();
		EVENT_BUS.fire(new GameStartEvent());
		
	}
	
	
	
}

package com.readcollin0.dungeonrun.event.entity.player;

import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.event.entity.EntityEvent;

public class PlayerEvent extends EntityEvent {
	
	public PlayerEvent(Player p) {
		super(p);
	}
	
}

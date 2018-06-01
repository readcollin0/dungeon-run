package com.readcollin0.dungeonrun.event;

import com.readcollin0.dungeonrun.entity.player.Player;
import com.readcollin0.dungeonrun.event.entity.player.PlayerEvent;

public class EnterCombatEvent extends PlayerEvent {

	public EnterCombatEvent(Player p) {
		super(p);
	}

}

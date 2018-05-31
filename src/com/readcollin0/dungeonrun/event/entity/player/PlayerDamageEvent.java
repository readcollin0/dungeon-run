package com.readcollin0.dungeonrun.event.entity.player;

import com.readcollin0.dungeonrun.entity.player.Player;

public class PlayerDamageEvent extends PlayerEvent {
	
	protected int amount;
	
	public PlayerDamageEvent(Player p, int amount) {
		super(p);
	}
	
	public int getDamageAmount() {
		return amount;
	}
	
}

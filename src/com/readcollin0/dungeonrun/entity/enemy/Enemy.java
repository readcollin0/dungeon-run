package com.readcollin0.dungeonrun.entity.enemy;

import java.awt.Graphics;
import java.util.HashMap;

import com.readcollin0.dungeonrun.DungeonRun;
import com.readcollin0.dungeonrun.entity.Entity;
import com.readcollin0.dungeonrun.entity.stats.Attribute;
import com.readcollin0.dungeonrun.entity.stats.EntityStats;
import com.readcollin0.dungeonrun.entity.weapon.Weapon;
import com.readcollin0.dungeonrun.event.view.CanvasUpdateTopEvent;
import com.readcollin0.util.eventbus.SubscribeEvent;

public class Enemy extends Entity {
	
	protected Weapon weapon;
	{DungeonRun.EVENT_BUS.subscribeObject(this);}
	
	public Enemy(String name) {
		super(name);
	}
	
	public Enemy(String name, HashMap<Attribute, Integer> customStats) {
		super(name, customStats);
	}
	
	public Enemy(String name, EntityStats stats) {
		super(name, stats);
	}
	
	public void setWeapon(Weapon w) {
		this.weapon = w;
	}
	
	public String getName() {
		return name;
	}
	
	@SubscribeEvent
	public void onCanvasUpdateTop(CanvasUpdateTopEvent e) {
		if (!DungeonRun.CONTROLLER.inCombat) return;
		if (!DungeonRun.CONTROLLER.getCurrentEnemy().equals(this)) return;
		Graphics g = e.getCanvas().getGraphics();
		g.drawString(name + " Health: " + stats.getStat(Attribute.HEALTH), 450, 320);
	}
	
}

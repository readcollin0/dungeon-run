package com.readcollin0.dungeonrun.event.button;

import com.readcollin0.util.eventbus.Event;

public class CanvasClickEvent extends Event {
	
	protected int x, y;
	
	public CanvasClickEvent(int mouseX, int mouseY) {
		this.x = mouseX;
		this.y = mouseY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}

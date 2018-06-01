package com.readcollin0.dungeonrun.event.view;

import java.awt.Canvas;

import com.readcollin0.util.eventbus.Event;

public class CanvasEvent extends Event {
	
	private Canvas canvas;
	
	public CanvasEvent(Canvas c) {
		this.canvas = c;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
}

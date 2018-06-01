package com.readcollin0.dungeonrun.event.button;

import com.readcollin0.dungeonrun.util.CanvasButton;

public class CanvasButtonClickEvent extends CanvasClickEvent {

	protected CanvasButton button;
	
	public CanvasButtonClickEvent(int mouseX, int mouseY, CanvasButton button) {
		super(mouseX, mouseY);
		this.button = button;
	}
	
	public CanvasButton getButtonClicked() {
		return button;
	}

}

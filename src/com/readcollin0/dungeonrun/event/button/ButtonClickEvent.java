package com.readcollin0.dungeonrun.event.button;

import javax.swing.JButton;

import com.readcollin0.util.eventbus.Event;

public class ButtonClickEvent extends Event {
	
	private JButton button;
	
	public ButtonClickEvent(JButton buttonClicked) {
		this.button = buttonClicked;
	}
	
	public JButton getButtonClicked() {
		return button;
	}
	
}

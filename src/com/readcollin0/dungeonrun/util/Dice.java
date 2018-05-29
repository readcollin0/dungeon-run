package com.readcollin0.dungeonrun.util;

public class Dice {
	
	public int rollDie(int sides) {
		return ((int) Math.random() * sides) + 1;
	}
	
	public int d4() {
		return rollDie(4);
	}
	
	public int d6() {
		return rollDie(6);
	}
	
	public int d8() {
		return rollDie(8);
	}
	
	public int d10() {
		return rollDie(10);
	}
	
	public int d12() {
		return rollDie(12);
	}
	
	public int d20() {
		return rollDie(20);
	}
	
	public int d100() {
		return rollDie(100);
	}
	
}

package com.readcollin0.dungeonrun.util;

public class Dice {
	
	public static int rollDie(int sides) {
		return ((int) Math.random() * sides) + 1;
	}
	
	public static int d4() {
		return rollDie(4);
	}
	
	public static int d6() {
		return rollDie(6);
	}
	
	public static int d8() {
		return rollDie(8);
	}
	
	public static int d10() {
		return rollDie(10);
	}
	
	public static int d12() {
		return rollDie(12);
	}
	
	public static int d20() {
		return rollDie(20);
	}
	
	public static int d100() {
		return rollDie(100);
	}
	
}

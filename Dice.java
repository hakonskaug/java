// -------------------------------------------------------
// Assignment 4
// Written by: Haakon Skaug Ingebrigtsen, 40105722
// For COMP 248 Section EE – Fall 2018
// --------------------------------------------------------
import java.util.Random;

//TODO Check rand

public class Dice {
	private int roll1, roll2;
	private Random generate = new Random();
	
	Dice(){
		roll1 = 0;
		roll2 = 0;
	}
	
	public void rollDice() {
		roll1 = generate.nextInt(5) + 1;
		roll2 = generate.nextInt(5) + 1;

	}
	public int getRoll1() {
		return roll1;
	}
	public int getRoll2() {
		return roll2;
	}
	public int totalRoll() {
		return roll1+roll2;
	}
	public String toString() {
		return " rolled " + (roll1+roll2);
	}
	
}

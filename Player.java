// -------------------------------------------------------
// Assignment 4
// Written by: Haakon Skaug Ingebrigtsen, 40105722
// For COMP 248 Section EE – Fall 2018
// --------------------------------------------------------

public class Player {

	private String name;
	private int roll;
	Garden playerGarden;
	
	Player(String nameOfPlayer, int sizeOfGarden){
		name = nameOfPlayer;
		playerGarden = new Garden(sizeOfGarden);
	}
	
	public String getName() {
		return name;
	}
	public void roll(int newRoll) {
		roll = newRoll;
	}
	public int getRoll() {
		return roll;
	}
//	public int getRoll() {
//		
//	}
	public int howManyFlowers() {
		return playerGarden.countPossibleFlowers();
	}
	public int howManyTrees() {
		return playerGarden.countPossibleTrees();
	}
	public char whatIsPlanted(int x, int y) {
		return playerGarden.getInLocation(x, y);
	}
	public void plantTreeInGarden(int x, int y) {
		playerGarden.plantTree(x, y);
	}
	public void plantFlowerInGarden(int x, int y) {
		playerGarden.plantFlower(x, y);
	}
	public void eatHere(int x, int y) {
		playerGarden.removeFlower(x, y);
	}
	public void setName(String newName) {
		name = newName;
	}
	//Two different methods for displaying the garden.
	public void showGarden() {
		System.out.println("\n\n" + playerGarden.toString());
	}
	public void printGarden() {
		playerGarden.getGarden();
	}
	public boolean isGardenFull() {
		return playerGarden.gardenFull();
	}
	public boolean isGardenEmpty() {
		return playerGarden.gardenEmpty();
	}
	
}

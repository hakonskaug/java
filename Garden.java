// -------------------------------------------------------
// Assignment 4
// Written by: Haakon Skaug Ingebrigtsen, 40105722
// For COMP 248 Section EE – Fall 2018
// --------------------------------------------------------

import java.util.Scanner;

public class Garden {
	Scanner userInput = new Scanner(System.in);
	private char[][] gardenGrid;
	public static int size;
	Garden() {
		size = 3;
		gardenGrid = new char[size][size];
		initializeGarden();
	}
	Garden(int newSize){
		if(newSize >= 3) {
			size = newSize;
		}
		else {
			size = 3;
		}
		gardenGrid = new char[size][size];
		initializeGarden();
	}
	
	private void initializeGarden(){
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				gardenGrid[i][j] = '-';
			}
		}
	}
	public int getSize() {
		return size;
	}
	public char getInLocation(int x, int y) {
		return (gardenGrid[x][y]);
	}
	public void plantFlower(int x, int y) {
		int newX, newY;
		if(plantPossible("flower", x, y) && countPossibleFlowers() > 0) {
			gardenGrid[x][y] = 'f';
		}
		else {
			System.out.println("**It is not possible to plant a flower in (" + x + ", " + y + ").");
			System.out.print("Please enter new coordinates:\n>>");
			newX = userInput.nextInt();
			newY = userInput.nextInt();
			plantFlower(newX, newY);
		}

	}
	public void plantTree(int x, int y) {
		int newX, newY;
		if(plantPossible("tree", x, y) && countPossibleTrees() > 0) {
			gardenGrid[x][y] = 't';
			gardenGrid[x][y+1] = 't';
			gardenGrid[x+1][y] = 't';
			gardenGrid[x+1][y+1] = 't';
		}
		else {
			System.out.println("It is not possible to plant tree in (" + x + ", " + y + ").");
			System.out.print("Please enter new coordinates:\n>>");
			newX = userInput.nextInt();
			newY = userInput.nextInt();
			plantTree(newX, newY);
		}
		

	}
	public void removeFlower(int x, int y) {
		gardenGrid[x][y] = '-';
	}
	public int countPossibleFlowers() {
		int flowerCount = 0;
		for(int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				if(gardenGrid[x][y] == '-') {
					flowerCount++;
				}
			}
		}
		return flowerCount;
	}
	public int countPossibleTrees() {
		int treeCount = 0;
		for(int x = 0; x < size-1; x++) {
			for(int y = 0; y < size-1; y++) {
				if((gardenGrid[x][y] == '-') && (gardenGrid[x][y+1] == '-') && (gardenGrid[x+1][y] == '-') && (gardenGrid[x][y+1] == '-')) {
					treeCount++;
				}
			}
		}
		return treeCount;
	}
	public boolean gardenFull() {
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				if(gardenGrid[x][y] == '-') {
					return false;
				}
			}
		}
		return true;
	}
	public boolean gardenEmpty() {
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				if(this.getInLocation(i, j) != '-') {
					return false;
				}
			}
		}
		return true;
	}
	//Method to check if a specific plant is possible
	public boolean plantPossible(String type, int x, int y) {
		//If planting a tree: check if 2x2 is free
		if((x <= size-1 && y <= size-1) && (x >= 0 && y >= 0)) { //Checking to see if x and y is within map
			if(type.equals("tree")) {
				if((gardenGrid[x][y] == '-') && (gardenGrid[x][y+1] == '-') && (gardenGrid[x+1][y] == '-') && (gardenGrid[x][y+1] == '-')) {
					return true;
				}
				else {
					return false;
				}
			}
			//If planting anything else (i.e a flower) check 1x1
			else {
				if(x > size-1 || x < 0 || y > size-1 || y < 0) {
					return false;
				}
				else if((gardenGrid[x][y] == '-')) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	//The assignment specifies that we must make a method toString that returns the garden as a string
	//I do however sometimes prefer a basic that prints out the garden. Like getGarden()
	public String toString() {
		String grid = "\n  |";
		for(int i = 0; i < size; i++) {
			grid += "\t" + (i);
		}
		grid += "\n  |\n";
		for(int i = 0; i < size; i++) {
			grid += (i) + " |";
			for(int j = 0; j < size; j++) {
				grid += "\t" + gardenGrid[i][j];
			}
			grid += "\n  |\n";
		}
		return grid;
	}
	public void getGarden() {
		System.out.printf("  |");
		for(int i = 0; i < size-1; i++) {
			System.out.printf("\t%d", (i+1));
		}
		System.out.printf("\n  |\n");
		for(int i = 0; i < size-1; i++) {
			System.out.printf("%d |", (i+1));
			for(int j = 0; j < size-1; j++) {
				System.out.printf("\t%c", gardenGrid[j][i]);
			}
			System.out.printf("\n  |\n");
		}
	}
	
}

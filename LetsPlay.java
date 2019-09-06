// -------------------------------------------------------
// Assignment 4
// Written by: Haakon Skaug Ingebrigtsen, 40105722
// For COMP 248 Section EE – Fall 2018
// --------------------------------------------------------

import java.util.Scanner;
import java.util.Random;

//Driver


public class LetsPlay {
	

	//WELCOME BANNER
	public static void main(String []args) {
		String userString, winner = "";
		int userInt = 0, userInt2 = 0;
		int size = 0, numberOfPlayers = 0;
		Dice dice = new Dice();
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("----------------------------------------------------"
				+ "\nWelcome to Crazy Haakon's Garden Game ;-)\n"
				+ "-------------------------------------------------------");
		System.out.println("To play this game, you will first have to enter the "
				+ "size of the board, and how many players.\nEvery player will take their"
				+ "turn to roll the dice. The player with the highest roll will start the game."
				+ "\nWhen it is your turn you will roll the dice. Based on the roll you get,"
				+ " you can do the following actions:\n"
				+ "Roll\t| Outcome\n"
				+ "3\t| Plant a tree (2x2) and a flower (1x1)\n"
				+ "6\t| Plant 2 flowers (2 times 1x1)\n"
				+ "12\t| Plant 2 trees (2 times 2x2)\n"
				+ "5 or 10\t| The rabbit will eat something\n"
				+ "Even roll: Plant a tree (2x2)\nOdd roll: Plant a flower");
		System.out.println("------------------------------------\nInstructions:\n- "
				+ "Minimum # of players: 2\n- Minimum garden size: 3x3\n- You can only plant"
				+ " in an empty location."
				+ "- To plant a tree, give the top left coordinates of the 2x2 space."
				+ "  (Make sure all tiles of the 2x2 are free)"
				+ "\n\nLet's start the game!\n");
		
		//Input
		//PLayers
		do {
			System.out.print("\nHow many players?\n>>");
			userInt = userInput.nextInt();
			if(userInt < 2 || userInt > 10) {
				System.out.println("** Sorry, but " + userInt + " is not a valid input.");
			}
			else {
				numberOfPlayers = userInt;
			}
		}
		while((numberOfPlayers < 2) | (numberOfPlayers > 10));
		Player [] players = new Player[numberOfPlayers];
		
		
		//Size:
		System.out.println(userInt + " players it is!");
		System.out.print("\nThe default garden size is 3. Would you like to change it?\n1: Yes\t2: No\n>>");
		userInt = userInput.nextInt();
		if(userInt == 1) {
			do {
				System.out.println("What size?\n>>");
				userInt = userInput.nextInt();
				if(userInt < 3 || userInt > 10) {
					System.out.println("** Sorry, but " + userInt + " is not a valid input."
							+ "\nMinimum size is 3, and maximum 10.\n");
				}
				else {
					size = userInt;
				}
			}
			while(userInt < 3 | userInt > 10);
		}
		else {
			size = 3;
		}
		System.out.println("Ok. Size is " + size + ".");
		System.out.println("Please enter the names of the players..");

		for(int i = 1; i <= numberOfPlayers; i++) {
			System.out.println("Name of player " + i + " (no spaces allowed):");
			userString = userInput.next();
			players[i-1] = new Player(userString, size);
//			2
		}
		
		//DECIDE WHO GOES FIRST
		//Using a while to start over in case two players get the same roll.
		System.out.println("Ok. Let's see who goes first!");
		while(true) {
			for(int i = 0; i < numberOfPlayers; i++) {
				dice.rollDice();											//Roll new dice
				players[i].roll(dice.totalRoll());							//Save roll to player
				System.out.println(players[i].getName() + dice.toString());	//Output players roll
			}
			//Going through the players to sort them, based on roll, using bubble sort
			//Bubble sort: descending order: Index 0 will have the highest roll
			for(int i = 0; i < numberOfPlayers-1; i++) {
				for(int j = 0; j < numberOfPlayers-1; j++) {
					if(players[j+1].getRoll() > players[j].getRoll()) {
						//Using temporary object to switch reference
						Player temp = players[j];
						players[j] = players[j+1];
						players[j+1] = temp;
					}
					
				}
			}
			//Going through the players make sure they didn't get the same roll
			int check = 0;
			for(int i = 0; i < numberOfPlayers-1; i++) {
				if(players[i].getRoll() == players[i+1].getRoll()) {		//If two players have the same roll
					System.out.println(players[i].getName() + " and " + players[i+1].getName() + " "
							+ "rolled the same.\nStarting over..");
					check++;
					continue;												//Start over.
				}
			}
			if(check == 0) {
				break;
			}
		}
		//Printing the players and their total roll
		for(int i = 0; i < numberOfPlayers; i++) {
			System.out.printf("\n%s\t%d", players[i].getName(), players[i].getRoll());
		}
		
		//We have now decided who goes first, and sorted them in respectable order.
		//Now to start the game.
		//While there is no winner: keep going.
		//winner is initialized as "". The winning players name will be assigned to winner.
		System.out.println("\n----------------------------------------\n"
				+ "Time to play!\n");
		while(winner.equals("")) {
			int roll, count=1;															//Variable to save space
			//FOR-LOOP to cycle through players
			for(int i = 0; i < numberOfPlayers; i++) {
				System.out.printf("----------------------------------------\n");
				dice.rollDice();	
				roll = dice.totalRoll();
				players[i].roll(roll);
				System.out.printf("%s, you rolled %d (Die 1: %d, Die 2: %d)\n"
						+ "", players[i].getName(), roll, dice.getRoll1(), dice.getRoll2());
				if(players[i].isGardenFull()) {
					winner = players[i].getName();
					break;
				}
			switch(roll) {
			
			case 3:
				//Plant a tree, and a flower 	
				System.out.printf("You have to plant a tree (2x2) and a flower (1x1).\n");				
				//PLANT TREE
				if(players[i].howManyTrees() != 0) {
					System.out.printf("First: plant a tree. You have %d"
							+ " places to do this.\n", players[i].howManyTrees());
					players[i].showGarden();
					System.out.printf("Enter coordinates for the tree.\nRow: ");
					userInt = userInput.nextInt();
					System.out.printf("Column: ");
					userInt2 = userInput.nextInt();
					players[i].plantTreeInGarden(userInt, userInt2);
					players[i].showGarden();
				}
				else {
					System.out.printf("No more space to plant a tree. Skipping.\n");
				}
				//PLANT FLOWER
				if(players[i].howManyFlowers() == 0) {
					System.out.printf("No more space to plant a flower. Skipping.\n");
					break;
				}
				System.out.printf("Second: plant a flower. You have %d"
						+ " places to do this.\n", players[i].howManyFlowers());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the flower.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantFlowerInGarden(userInt, userInt2);
				players[i].showGarden();
				break;
			case 6:
				//Plant 2 flowers
				System.out.printf("You have to plant two flowers (1x1).\n");				
				//Flower 1
				System.out.printf("First flower: You have %d"
						+ " places to do this.\n", players[i].howManyFlowers());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the first flower.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantFlowerInGarden(userInt, userInt2);				
				//Flower 2
				if(players[i].howManyFlowers() == 0) {
					System.out.printf("No more space to plant a flower. Skipping.\n");
					break;
				}
				System.out.printf("Second flower: You have %d"
						+ " places to do this.\n", players[i].howManyFlowers());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the second flower.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantFlowerInGarden(userInt, userInt2);
				players[i].showGarden();
				break;
			case 12:
				//Plant 2 trees
				if(players[i].howManyTrees() == 0) {
					System.out.printf("No more space to plant a tree. Skipping turn.\n");
					break;
				}
				System.out.printf("You must plant two trees (2x2)\n");
				System.out.printf("First tree: You have %d"
						+ " places to do this.\n", players[i].howManyTrees());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the first tree.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantTreeInGarden(userInt, userInt2);
				players[i].showGarden();
				
				//Plant Tree 2
				if(players[i].howManyTrees() == 0) {
					System.out.printf("No more space to plant a tree. Skipping turn.\n");
					break;
				}
				System.out.printf("Second tree: You have %d"
						+ " places to do this.\n", players[i].howManyTrees());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the second tree.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantTreeInGarden(userInt, userInt2);
				players[i].showGarden();
				break;
				
			case 5:
				//Rabbit
			case 10:
				//Rabbit
				//The rabbit will eat at a random coordinate,
				//Granted the player has planted something there
				Random randomCoordinate = new Random();
				int randomX = randomCoordinate.nextInt(size);
				int randomY = randomCoordinate.nextInt(size);
				if(!players[i].isGardenEmpty()) {
					if(players[i].whatIsPlanted(randomX, randomY) != '-') {
						players[i].eatHere(randomX, randomY);
						
					}
					else {
						do {
							randomX = randomCoordinate.nextInt(size);
							randomY = randomCoordinate.nextInt(size);
							if(players[i].whatIsPlanted(randomX, randomY) != '-') {
								players[i].eatHere(randomX, randomY);
								break;
							}
						}
						while(players[i].whatIsPlanted(randomX, randomY) == '-');
					}
					System.out.printf("A rabbit ate from you garden at: Row %d, Col %d", randomX, randomY);
					players[i].showGarden();
				}
				
				//If nothing is planted, the rabbit will go away.
				else {
					System.out.printf("Your garden is empty. You escaped the terror of the rabbit for now...\n");
				}
				break;
			case 2:				
			case 4:
			case 8:
				//Plant a tree
				if(players[i].howManyTrees() == 0) {
					System.out.printf("No more space to plant a tree. Skipping turn.\n");
					break;
				}
				System.out.printf("You must plant a tree. You have %d"
						+ " places to do this.\n", players[i].howManyTrees());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the tree.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantTreeInGarden(userInt, userInt2);
				players[i].showGarden();
				break;
			case 7:
			case 9:
			case 11:
				//Plant a flower
				System.out.printf("You must plant a flower. You have %d"
						+ " places to do this.\n", players[i].howManyFlowers());
				players[i].showGarden();
				System.out.printf("Enter coordinates for the flowers.\nRow: ");
				userInt = userInput.nextInt();
				System.out.printf("Column: ");
				userInt2 = userInput.nextInt();
				players[i].plantFlowerInGarden(userInt, userInt2);
				players[i].showGarden();
				break;
			default:
				
				break;
			}
				if(players[i].isGardenFull()) {
				winner = players[i].getName();
				break;
				}
			}
			count++;
		}
		
		System.out.printf("\nFINAL RESULTS:\n");
		for(int i=0; i<numberOfPlayers; i++) {
			System.out.printf("\n" + players[i].getName() + "'s garden:\n");
			players[i].showGarden();
		}
		
		System.out.printf("\n\nThe winner is %s!!! Congratulations!!!\n\n", winner);
		
	}	
}
	
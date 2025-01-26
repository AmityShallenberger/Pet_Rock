package pet.rock;

import java.util.Scanner;


public class PetRockMain 
{
	public static void main (String [] args) 
	{
		
		Scanner input = new Scanner(System.in);
		
		boolean shouldLoop = true;
		boolean gameOver = false;
		
		boolean feedOnCooldown = false;
		boolean playOnCooldown = false;
		int polishDiminishReturnCurrent = 0;
		
		String feedRockInput = "1";
		String playRockInput = "2";
		String polishRockInput = "3";
		String displayRockStatInput = "4";
		String exitAppInput = "5";
		
		PetRock rockPet = new PetRock();
		// Need to add something to the petrock to get data from the other thing.
		
		while (shouldLoop == true) 
		{
			
			if ( (rockPet.hunger == 10) || (rockPet.boredom == 10) || (rockPet.energy == 0) ) 
			{
				System.out.println("Your rock has rolled away in protest! Game over.");
				shouldLoop = false;
				gameover = true;
			}
		
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println( ((feedOnCooldown == true) ? "Feeding rock is on cooldown." : "Press '" + feedRockInput + "' to feed the rock") );
			System.out.println( ((playOnCooldown == true) ? "Playing with rock is on cooldown." : "Press '" + playRockInput + "' to play with the rock") );

			System.out.println("Press '" + polishRockInput + "' to polish the rock");
			System.out.println("Press '" + displayRockStatInput + "' to display the rock's status");
			System.out.println("Press '" + exitAppInput + "' to exit the application");
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			String userInput = input.nextLine();
			
			if (userInput.equals(feedRockInput)) 
			{
				if (petRock.energy < 1) 
				{
					System.out.println("Pet rock does not have enough energy to be fed");
				} else 
				{
					petRock.feedRock();
					feedOnCooldown = true;
				}
				playOnCooldown = false;
				polishDiminishReturnCurrent = 0;
			}
			else if (userInput.equals(playRockInput))
			{
				if (petRock.energy < 2) 
				{
					System.out.println("Pet rock does not have enough energy to play");
				} else 
				{
					petRock.playRock();
					playOnCooldown = true;
				}
				feedOnCooldown = false;
				polishDiminishReturnCurrent = 0;
			}
			else if (userInput.equals(polishRockInput))
			{
				petRock.polishRock();
				polishDiminishReturnCurrent += 1;
				feedOnCooldown = false;
				playOnCooldown = false;
			}
			else if (userInput.equals(displayRockStatInput))
			{
				System.out.println("Name: " + petRock.name);
				System.out.println("Hunger: " + petRock.hunger);
				System.out.println("Boredom: " + petRock.boredom);
				System.out.println("Energy: " + petRock.energy);
				System.out.println("Mood: " + petRock.mood);
				petRock.energy += 1;
			}
			// Change this to separate quitting from game over! Make sure to save rock state!
			else if (userInput.equals(exitAppInput)) 
			{
				shouldLoop = false;
				System.out.println("Exiting Application...");
			}
			else 
			{
				System.out.println("Invalid Input: Please enter a valid command.");
			}
			
			petRock.hunger += 1;
			petRock.boredom += 1;
			
			////// Random EVENTS
			int propertyofEvent = (int)(Math.random() * 3);
			int typeOfEvent = (int)(Math.random() * 10);
			
			if (propertyOfEvent < 3) 
			{
				// Positive
				if (propertyOfEvent == 0) 
				{
					switch (typeOfEvent) 
					{
						case 0: 
							System.out.println("Your rock found a shiny pebble! It’s happier now!");
							petRock.hunger -= 1;
							petRock.boredom -= 2;
							break;
						case 1: 
							System.out.println("Your rock got some extra sleep! Energy restored!");
							petRock.energy = 10;
							break;
						case 2: 
							System.out.println("Your rock found a snack! Satiated some Hunger!");
							petRock.hunger -= 3;
							break;
						case 3: 
							break;
						case 4: 
							break;
						case 5: 
							break;
						default: break;
					}
				}
				// Negative
				else 
				{
					switch (typeOfEvent) 
					{
						case 0: 
							System.out.println("Your rock is scared by a sudden noise! Boredom increased!");
							petRock.boredom += 2;
							break;
						case 1: 
							System.out.println("Your rock is grumpy today. Hunger increased!");
							petRock.hunger += 2;
							break;
						case 2: 
							System.out.println("Your rock smelled something delicious. Hunger increased!");
							petRock.hunger += 2;
							break;
						case 3: 
							System.out.println("Your rock is feeling out of it today. Energy decreased!");
							petRock.energy -= 2;
							break;
						case 4: 
							break;
						case 5: 
							break;
						default: break;
					}
	
				}
			}
			
			petRock.updateStats();
			
		}
		
	}
    
}

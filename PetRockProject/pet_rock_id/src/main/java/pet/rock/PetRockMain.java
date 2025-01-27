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
		
		PetRock petRock = new PetRock("", "", 1, 1, 1);
		// Need to add something to the petrock to get data from the other thing.
		
		while (shouldLoop == true) 
		{
			
			if ( (petRock.getHunger() == 10) || (petRock.getBoredom() == 10) || (petRock.getEnergy() == 0) ) 
			{
				System.out.println("Your rock has rolled away in protest! Game over.");
				shouldLoop = false;
				gameOver = true;
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
				if (petRock.getEnergy() < 1) 
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
				if (petRock.getEnergy() < 2) 
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
				System.out.println(petRock);
				petRock.setEnergy(petRock.getEnergy() + 1);
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
			
			petRock.setHunger(petRock.getHunger() + 1);
			petRock.setBoredom(petRock.getBoredom() + 1);
			
			////// Random EVENTS
			int propertyOfEvent = (int)(Math.random() * 3);
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
							petRock.setHunger(petRock.getHunger() - 1);
							petRock.setBoredom(petRock.getBoredom() - 2);
							break;
						case 1: 
							System.out.println("Your rock got some extra sleep! Energy restored!");
							petRock.setEnergy(10);
							break;
						case 2: 
							System.out.println("Your rock found a snack! Satiated some Hunger!");
							petRock.setHunger(petRock.getHunger() - 3);
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
							petRock.setBoredom(petRock.getBoredom() + 2);
							break;
						case 1: 
							System.out.println("Your rock is grumpy today. Hunger increased!");
							petRock.setHunger(petRock.getHunger() + 2);
							break;
						case 2: 
							System.out.println("Your rock smelled something delicious. Hunger increased!");
							petRock.setHunger(petRock.getHunger() + 2);
							break;
						case 3: 
							System.out.println("Your rock is feeling out of it today. Energy decreased!");
							petRock.setHunger(petRock.getHunger() + 2);
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

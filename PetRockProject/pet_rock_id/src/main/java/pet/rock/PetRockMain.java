package pet.rock;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PetRockMain 
{
	public static void main (String [] args) 
	{
		//Get file the make settings for gson
		File f = new File("SavedData.json");
		GsonBuilder gsonB = new GsonBuilder();
		
		Scanner input = new Scanner(System.in);
		
		boolean shouldLoop = true;
		boolean gameOver = false;
		int turnNumber = 0;
		
		
		boolean feedOnCooldown = false;
		boolean playOnCooldown = false;
		int polishDiminishReturnCurrent = 0;
		
		
		String feedRockInput = "1";
		String playRockInput = "2";
		String polishRockInput = "3";
		String displayRockStatInput = "4";
		String exitAppInput = "5";
		
		PetRock petRock = new PetRock("", "", 1, 1, 1);
		if (f.exists()) {
			Gson gson = new Gson();

			try {
				Scanner readFile = new Scanner(f);
				String currJson = "";

				while (readFile.hasNext()) {
					currJson = currJson + readFile.nextLine();
				}

				petRock = gson.fromJson(currJson, PetRock.class);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		else {
			
			System.out.print("No save data found for rock. A pet rock must be created. Please enter a name for your rock: ");
			String newRockName = input.nextLine();
			petRock.setName(newRockName);
			petRock.setHunger(6);
			petRock.setBoredom(6);
			petRock.setEnergy(10);
		}
		
		while ((shouldLoop == true) && (gameOver == false)) 
		{
			
			if ( (petRock.getHunger() == 10) || (petRock.getBoredom() == 10) || (petRock.getEnergy() == 0) ) 
			{
				System.out.println("Your rock has rolled away in protest! Game over. You lasted " + turnNumber + " turns!");
				gameOver = true;
				f.delete(); // delete saved data if game over
				break;
			}
		
			System.out.println("--------------------------------------------------------------------------------------------------------");
			System.out.println( ((feedOnCooldown == true) ? "Feeding rock is on cooldown." : "Press '" + feedRockInput + "' to feed the rock") );
			System.out.println( ((playOnCooldown == true) ? "Playing with rock is on cooldown." : "Press '" + playRockInput + "' to play with the rock") );

			System.out.println("Press '" + polishRockInput + "' to polish the rock (diminishing returns occur)");
			System.out.println("Press '" + displayRockStatInput + "' to display the rock's status");
			System.out.println("Press '" + exitAppInput + "' to exit the application");
			System.out.println("--------------------------------------------------------------------------------------------------------");
			
			String userInput = input.nextLine();
			
			if ((userInput.equals(feedRockInput)) && (feedOnCooldown == false)) 
			{
				if (petRock.getEnergy() < 1) 
				{
					System.out.println("Pet rock does not have enough energy to be fed");
				} 
				else 
				{
					petRock.feedRock();
					feedOnCooldown = true;
				}
				playOnCooldown = false;
				polishDiminishReturnCurrent = 0;
			}
			else if ((userInput.equals(playRockInput)) && (playOnCooldown == false))
			{
				if (petRock.getEnergy() < 2) 
				{
					System.out.println("Pet rock does not have enough energy to play");
				} 
				else 
				{
					petRock.playRock();
					playOnCooldown = true;
				}
				feedOnCooldown = false;
				polishDiminishReturnCurrent = 0;
			}
			else if (userInput.equals(polishRockInput))
			{
				petRock.polishRock(polishDiminishReturnCurrent);
				polishDiminishReturnCurrent += 1;
				feedOnCooldown = false;
				playOnCooldown = false;
			}
			else if (userInput.equals(displayRockStatInput))
			{

				System.out.println("Name: " + petRock.getName());
				System.out.println("Hunger: " + petRock.getHunger());
				System.out.println("Boredom: " + petRock.getBoredom());
				System.out.println("Energy: " + petRock.getEnergy());
				System.out.println("Mood: " + petRock.getMood());
				
				feedOnCooldown = false;
				playOnCooldown = false;
				polishDiminishReturnCurrent = 0;
				
				petRock.setEnergy(petRock.getEnergy() + 1);
				
				
			}
			// Change this to separate quitting from game over! Make sure to save rock state!
			else if (userInput.equals(exitAppInput)) 
			{
				input.close();	
				shouldLoop = false;
				System.out.println("Exiting Application...");
			}
			else 
			{
				
				petRock.setEnergy(petRock.getEnergy() + 1);
				
				System.out.println("Invalid Input: Please enter a valid command.");
			}
			
			petRock.setHunger(petRock.getHunger() + 1);
			petRock.setBoredom(petRock.getBoredom() + 1);
			
			////// Random EVENTS
			int propertyOfEvent = (int)(Math.random() * 5);
			int typeOfEvent = (int)(Math.random() * 10);
			
			// Should have a random event this turn?
			if ((propertyOfEvent < 3) && (shouldLoop == true) && (gameOver == false))
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
							petRock.setEnergy(petRock.getEnergy() - 2);
							break;
						default: break;
					}
	
				}
			}
			
			petRock.updateStats();
			
			turnNumber += 1;

			//After every action turn the current stats into json then write to the current json file
			
			try {
				if (!f.exists()) {
					f = new File("SavedData.json");
				} 

				String jsonData = gsonB.setPrettyPrinting().create().toJson(petRock);

				FileWriter fw = new FileWriter(f.getPath());

				fw.write(jsonData);	
				fw.close();

			} catch (Exception e) {
				System.err.println(e);
			}

		}
		
		
		
		
	}
    
}

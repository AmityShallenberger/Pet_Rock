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
		
		boolean feedOnCooldown = false;
		boolean playOnCooldown = false;
		int polishDiminishReturnCurrent = 0;
		
		String feedRockInput = "1";
		String playRockInput = "2";
		String polishRockInput = "3";
		String displayRockStatInput = "4";
		String exitAppInput = "5";
		
		//Make a rock, if saved data exists make the rock with the saved stats
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
		else { //Who ever is in charge of being able to set name of new rocks should do it in this else statment using petRock.setName()

		}
		
		while (shouldLoop == true) 
		{
			
			if ( (petRock.getHunger() == 10) || (petRock.getBoredom() == 10) || (petRock.getEnergy() == 0) ) 
			{
				System.out.println("Your rock has rolled away in protest! Game over.");
				shouldLoop = false;
				gameOver = true;
				f.delete(); //delete saved data if gameover
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
				System.out.println("Name: " + petRock.getName());
				System.out.println("Hunger: " + petRock.getHunger());
				System.out.println("Boredom: " + petRock.getBoredom());
				System.out.println("Energy: " + petRock.getEnergy());
				System.out.println("Mood: " + petRock.getMood());
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

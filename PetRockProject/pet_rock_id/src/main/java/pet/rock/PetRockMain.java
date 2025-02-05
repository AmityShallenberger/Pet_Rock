package pet.rock;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PetRockMain 
{
	public static boolean feedOnCooldown = false;
	public static boolean playOnCooldown = false;
	public static void main (String [] args) 
	{
		//Get file the make settings for gson
		File f = new File("SavedData.json");
		GsonBuilder gsonB = new GsonBuilder();
		
		Scanner input = new Scanner(System.in);
		
		boolean shouldLoop = true;
		boolean gameOver = false;
		int turnNumber = 0;
		int gameOverCounter = 0;
		
		int polishDiminishReturnCurrent = 0;
		
		PetRock petRock = new PetRock("", "", 1, 1, 10);
                
		if (f.exists()) 
		{
			Gson gson = new Gson();

			try 
			{
				Scanner readFile = new Scanner(f);
				String currJson = "";

				while (readFile.hasNext()) 
				{
					currJson = currJson + readFile.nextLine();
				}

				petRock = gson.fromJson(currJson, PetRock.class);
				readFile.close();
			} 
			
			catch (Exception e) 
			{
				System.err.println(e);
			}
		}
		else 
		{
			Output.noSaveData();
			String newRockName = input.nextLine();
			petRock.setName(newRockName);
		}
		
		while ((shouldLoop == true) && (gameOver == false)) 
		{
			gameOver = (gameEndCheck(petRock, gameOverCounter));
			if ((gameOver) == true)
			{
				gameEnd(f, turnNumber);
			}

			else 
			{
		
				Output.display(feedOnCooldown, playOnCooldown);
				int userInput = getUserInput();
				
				switch (userInput) 
				{
					case 1: // Feed
						if (petRock.getEnergy() < 1) 
						{
							Output.noEnergy(0);
						} 
						else 
						{
							petRock.feedRock();
							feedOnCooldown = true;
						}
						playOnCooldown = false;
						polishDiminishReturnCurrent = 0;
						break;
					case 2: // Play
						if (petRock.getEnergy() < 2) 
						{
							Output.noEnergy(1);
						} 
						else 
						{
							petRock.playRock();
							playOnCooldown = true;
						}
						feedOnCooldown = false;
						polishDiminishReturnCurrent = 0;
						break;
					case 3: // Polish
						petRock.polishRock(polishDiminishReturnCurrent);
						polishDiminishReturnCurrent += 1;
						feedOnCooldown = false;
						playOnCooldown = false;
						break;
					case 4: // CheckStats
						System.out.println(petRock);
						petRock.setEnergy(petRock.getEnergy() + 1);
						break;
					case 5: // Quit
						shouldLoop = false;
						Output.gameExit();
						break;
					default: break;
				}	

				petRock.setHunger(petRock.getHunger() + 1);
				petRock.setBoredom(petRock.getBoredom() + 1);

				// Random events.
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
								System.out.println("\nYour rock found a shiny pebble! It’s happier now!");
								petRock.setHunger(petRock.getHunger() - 1);
								petRock.setBoredom(petRock.getBoredom() - 2);
								break;
							case 1: 
								System.out.println("\nYour rock got some extra sleep! Energy restored!");
								petRock.setEnergy(10);
								break;
							case 2: 
								System.out.println("\nYour rock found a snack! Satiated some Hunger!");
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
								System.out.println("\nYour rock is scared by a sudden noise! Boredom increased!");
								petRock.setBoredom(petRock.getBoredom() + 2);
								break;
							case 1: 
								System.out.println("\nYour rock is grumpy today. Hunger increased!");
								petRock.setHunger(petRock.getHunger() + 2);
								break;
							case 2: 
								System.out.println("\nYour rock smelled something delicious. Hunger increased!");
								petRock.setHunger(petRock.getHunger() + 2);
								break;
							case 3: 
								System.out.println("\nYour rock is feeling out of it today. Energy decreased!");
								petRock.setEnergy(petRock.getEnergy() - 2);
								break;
							default: break;
						}
					}
				}

				petRock.updateStats();
				petRock.updateMood();
							
				turnNumber += 1;
				if (petRock.getEnergy() == 0)
				{
					gameOverCounter++;
				}
				else 
				{
					gameOverCounter = 0;
				}

				//After every action turn the current stats into json then write to the current json file
				try 
				{
					if (!f.exists()) {
						f = new File("SavedData.json");
					} 

					String jsonData = gsonB.setPrettyPrinting().create().toJson(petRock);

					FileWriter fw = new FileWriter(f.getPath());

					fw.write(jsonData);	
					fw.close();

				} 
				
				catch (Exception e) 
				{
						System.err.println(e);
				}
				
				
			}
			
			
		}
	}
	
	
	public static boolean gameEndCheck(PetRock rockPet, int counter) 
	{
		return ( (rockPet.getHunger() == 10) || (rockPet.getBoredom() == 10) || ( (rockPet.getEnergy() == 0) && (counter >= 3) ) );
	}
			

	// Gets input from user. 
	// Converts it to INT. 
	// Ensures valid input (1 <= x <= 5).
	public static int getUserInput() 
	{
		Scanner input = new Scanner(System.in);
		String userInput = ""; 
		int userInputAsInt = 0; 
		boolean validUserInput = false; 
							
		while(!validUserInput)
		{
			userInput = input.nextLine();
			boolean validIntegerInput = true;

			try
			{
				userInputAsInt = Integer.parseInt(userInput);
			}
			catch(NumberFormatException e)
			{
				validIntegerInput = false;
			}

			// User has entered a non-integer value OR
			// an integer value that is not between 1 and 5.
			if(!validIntegerInput || userInputAsInt > 5 || userInputAsInt < 1) 
			{
				Output.invalidInput();
			}
			
			else if(feedOnCooldown && userInputAsInt == 1)
			{
				Output.cooldown(0);
			}

			else if(playOnCooldown && userInputAsInt == 2)
			{
				Output.cooldown(1);
			}

			else 
			{
				validUserInput = true;
			}
		}

		return userInputAsInt;
	}

	public static void feed() {

	}

	public static void play() {

	}

	public static void polish() {

	}
	
	public static void gameEnd(File savedData, int numTurns) 
	{
		System.out.println("Your rock has rolled away in protest! Game over. You lasted " + numTurns + " turns!");
	
		savedData.delete();	
	}
	
}

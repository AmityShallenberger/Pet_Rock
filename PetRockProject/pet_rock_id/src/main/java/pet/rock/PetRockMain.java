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
			System.out.print("No save data found for rock.\n" + 
                                "A pet rock must be created.\n" + 
                                "Please enter a name for your rock: ");
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
		
				display(feedOnCooldown, playOnCooldown);
				int userInput = getUserInput();

				switch (userInput) 
				{
					case 1: // Feed
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
						break;
					case 2: // Play
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
						System.out.println("\nExiting Application...");
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
			

        
	// Displays user-input menu.
	// Takes in feed and play cooldowns to determine available options.
	public static void display(boolean cooldownFEED, boolean cooldownPLAY)
	{
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println( ((cooldownFEED == true) ? "Feeding rock is on cooldown." : 
														"Press '1' to feed the rock") );
		System.out.println( ((cooldownPLAY == true) ? "Playing with rock is on cooldown." : 
														"Press '2' to play with the rock") );
		System.out.println("Press '3' to polish the rock");
		System.out.println("Press '4' to display the rock's status");
		System.out.println("Press '5' to exit the application");
		System.out.println("--------------------------------------------------------------------------------------------------------");
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
				System.out.println("\nInvalid input: Please enter a valid command.");
			}
			
			else if(feedOnCooldown && userInputAsInt == 1)
			{
				System.out.println("\nYou cannot feed the rock right now.");
				System.out.println("Enter a different selection.");
			}

			else if(playOnCooldown && userInputAsInt == 2)
			{
				System.out.println("\nYou cannot play with the rock right now.");
				System.out.println("Enter a different selection.");
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

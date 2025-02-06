package pet.rock;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.*;

public class PetRockMain 
{

    private static boolean shouldLoop = true;
    private static boolean gameOver = false;
    public static boolean feedOnCooldown = false;
    public static boolean playOnCooldown = false;
    public static int polishDiminishReturnCurrent = 0;
    public static File f = new File("SavedData.json");

    public static void main (String [] args) 
    {
        GsonBuilder gsonB = new GsonBuilder();

        Scanner input = new Scanner(System.in);

        int turnNumber = 0;
        int gameOverCounter = 0;

        PetRock petRock = new PetRock("", "", 1, 1, 10);
        rockCreation(petRock, input);

        while ((shouldLoop == true) && (gameOver == false)) 
        {
            gameOver = (gameEndCheck(petRock, gameOverCounter));
            
            if ((gameOver) == true)
			{
                gameEnd(turnNumber);
			}
            else 
            {
                Output.display(feedOnCooldown, playOnCooldown);
                int userInput = getUserInput(input);

				doAction(petRock, userInput);
				
                petRock.setHunger(petRock.getHunger() + 1);
                petRock.setBoredom(petRock.getBoredom() + 1);

                randomEventGenerator(petRock);
				
                petRock.updateStats();
                petRock.updateMood();

                turnNumber += 1;
                
				gameOverCounter = incrementGameOverCounter(petRock, gameOverCounter);
                
				
                //After every action turn the current stats into json then write to the current json file
                try 
                {
                    if (!f.exists())
                        f = new File("SavedData.json");
                    
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
	input.close();	
    }
	
	public static int incrementGameOverCounter(PetRock petRock, int currentCounter) 
	{
		int returnCounter = 0;
		if (petRock.getEnergy() == 0)
		{
			returnCounter = currentCounter + 1;
		}
		return returnCounter;
	}
				
	public static void doAction(PetRock petRock, int input) 
	{
		switch (input) 
		{
			case 1: // Feed
				feed(petRock);
				break;
			case 2: // Play
				play(petRock);
				break;
			case 3: // Polish
				polish(petRock);
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
	}
  
    // Used on line ___ to create a random event.				
    public static void randomEventGenerator(PetRock petRock)
    {
        int propertyOfEvent = (int)(Math.random() * 5);
        int typeOfEvent = (int)(Math.random() * 10);

        if ((propertyOfEvent < 3) && (shouldLoop == true) && (gameOver == false))
        {
            // Positive
            if (propertyOfEvent == 0) 
            {
				switch (typeOfEvent) 
				{
					case 0: 
						//System.out.println("\nYour rock found a shiny pebble! It’s happier now!");
						petRock.setHunger(petRock.getHunger() - 1);
						petRock.setBoredom(petRock.getBoredom() - 2);
						break;
					case 1: 
						//System.out.println("\nYour rock got some extra sleep! Energy restored!");
						petRock.setEnergy(10);
						break;
					case 2: 
						//System.out.println("\nYour rock found a snack! Satiated some Hunger!");
						petRock.setHunger(petRock.getHunger() - 3);
						break;
					case 3: 
						//System.out.println("Your rock got flirted with your rock is feeling energized");
						petRock.setEnergy(10);
						break;
					case 4: 
						//System.out.println("Your rock went for a relaxing walk emergy increased!");
						petRock.setEnergy(petRock.getEnergy() + 1);
						break;
					case 5: 
						//System.out.println("Your rock had a really nice day");
						petRock.setBoredom(petRock.getBoredom() - 1);
						petRock.setEnergy(petRock.getEnergy() + 1);
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
						//System.out.println("\nYour rock is scared by a sudden noise! Boredom increased!");
						petRock.setBoredom(petRock.getBoredom() + 2);
						break;
					case 1: 
						//System.out.println("\nYour rock is grumpy today. Hunger increased!");
						petRock.setHunger(petRock.getHunger() + 2);
						break;
					case 2: 
						//System.out.println("\nYour rock smelled something delicious. Hunger increased!");
						petRock.setHunger(petRock.getHunger() + 2);
						break;
					case 3: 
						//System.out.println("\nYour rock is feeling out of it today. Energy decreased!");
						petRock.setEnergy(petRock.getEnergy() - 2);
						break;
					case 4: 
						//System.out.println("Your rock lost a friend.  Hunger increased and energy decreased");
						petRock.setEnergy(petRock.getEnergy() - 1);
						petRock.setHunger(petRock.getHunger() + 1);
						break;
					default: break;
				}
            }
            Output.RanEventMessage(propertyOfEvent,typeOfEvent);
        }
    }

    // Used on line ____to end the game... nonconsensually.
    public static boolean gameEndCheck(PetRock rockPet, int counter) 
    {
        return ( (rockPet.getHunger() == 10) || (rockPet.getBoredom() == 10) || ( (rockPet.getEnergy() == 0) && (counter >= 3) ) );
    }

    // Used on line ____ to gather an input from the user. 
    // Ensures valid input (1 <= x <= 5).
    public static int getUserInput(Scanner input) 
    {
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
    
    // Used on line ____ to feed the rock.
    public static void feed(PetRock petRock) 
    {
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
    }

    // Used on line ____ to play with the rock.
    public static void play(PetRock petRock) 
    {
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
    }

    // Used on line ____ to polish the rock.
    public static void polish(PetRock petRock) 
    {
        petRock.polishRock(polishDiminishReturnCurrent);
        polishDiminishReturnCurrent += 1;
        feedOnCooldown = false;
        playOnCooldown = false;
    }

    public static void rockCreation(PetRock petRock, Scanner input) {
        if (f.exists())
		{
            petRock.getSavedData(f);
		}
        else 
        {
            Output.noSaveData();
            String newRockName = input.nextLine();
            petRock.setName(newRockName);
        }
    }
	  // Used on line ____ to end the game.... consensually.
    public static void gameEnd(int numTurns) 
    {
        Output.gameEnd(numTurns);
        f.delete();	
    }
	
}
package pet.rock;

import java.util.Scanner;


public class PetRockMain 
{
	public static void main (String [] args) 
	{		
		boolean shouldLoop = true;
		boolean gameOver = false;
		
		boolean feedOnCooldown = false;
		boolean playOnCooldown = false;
		int polishDiminishReturnCurrent = 0;
		
                // name, mood, hunger, boredom, energy.
		PetRock petRock = new PetRock("", "", 1, 1, 100);
		// Need to add something to the petrock to get data from the other thing.
		
		while (shouldLoop == true) 
		{
		
                    if ( (petRock.getHunger() == 10) || (petRock.getBoredom() == 10) || (petRock.getEnergy() == 0) ) 
                    {
                        System.out.println("Your rock has rolled away in protest! Game over.");
			shouldLoop = false;
			gameOver = true;
                    }
		
                    display(feedOnCooldown, playOnCooldown);
			
                    // Input validation.
                        int userInput = getUserInput(feedOnCooldown, playOnCooldown);

                    // Gameplay Logic.
                        // 1.
			if (userInput == 1) 
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
                        
                        // 2.
			else if (userInput == 2)
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
                        
                        // 3.
			else if (userInput == 3)
			{
				petRock.polishRock();
				polishDiminishReturnCurrent += 1;
				feedOnCooldown = false;
				playOnCooldown = false;
			}
                        
                        // 4.
			else if (userInput == 4)
			{
				System.out.println(petRock);
				petRock.setEnergy(petRock.getEnergy() + 1);
			}
                        
                        // 5.
			// Change this to separate quitting from game over! Make sure to save rock state!
			else
			{
				shouldLoop = false;
				System.out.println("Exiting Application...");
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
        
        // Displays user-input menu.
        // Takes in feed and play cooldowns to determine available options.
        public static void display(boolean cooldownFEED, boolean cooldownPLAY)
        {
            System.out.println("-------------------------------------------------" + 
                    "-------------------------------------------------------");
            System.out.println( ((cooldownFEED == true) ? "Feeding rock is on cooldown." : 
                    "Press '1' to feed the rock") );
            System.out.println( ((cooldownPLAY == true) ? "Playing with rock is on cooldown." : 
                    "Press '2' to play with the rock") );

            System.out.println("Press '3' to polish the rock");
            System.out.println("Press '4' to display the rock's status");
            System.out.println("Press '5' to exit the application");
            System.out.println("---------------------------------------------------" + 
                    "-----------------------------------------------------");
        }
        
        // Gets input from user. 
        // Converts it to INT. 
        // Ensures valid input (1 <= x <= 5).
        public static int getUserInput(boolean cooldownFEED, boolean cooldownPLAY) 
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
                if(!validIntegerInput || userInputAsInt > 5 
                || userInputAsInt < 1)
                    System.out.println("Invalid input: Please " + 
                    "enter a valid command.");

                
                else if(cooldownFEED && userInputAsInt == 1)
                {
                    System.out.println("You cannot feed the rock right now.");
                    System.out.println("Enter a different selection.");
                }

                else if(cooldownPLAY && userInputAsInt == 2)
                {
                    System.out.println("You cannot play with the rock right now.");
                    System.out.println("Enter a different selection.");
                }

                else
                    validUserInput = true;
            }
            
            return userInputAsInt;
        }
}

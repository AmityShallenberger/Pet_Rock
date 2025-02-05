package pet.rock;

/*
 *      Yarrick Dillard made this file.
 */

// Output.
public class Output 
{    
    // TEMPORARY variable to turn output on/off for testing.
    static boolean lightSwitch = true;
        
    // METHODS below...
    
    // Used in the method "checkSaveData()" in PetRock.java
    // JSON file does not exist.
    public static void noSaveData()
    {
        if(lightSwitch) {
            System.out.print("No save data found for rock.\n" +
                "A pet rock must be created.\n" + 
                "Please enter a name for your rock: ");
        }
    }
    
    // Used in the method "gameEndCheck()" in PetRockMain.java
    // Hunger, energy, or boredom exceed their thresholds.
    public static void gameEnd(int turnNo)
    {
        if(lightSwitch) {
            System.out.println("Your rock has rolled away in protest!" + 
                    " Game over. You lasted " + turnNo + " turns!");
        }
    }
    
    // Used in the switch statement for gameplay loop in PetRockMain.java
    // Energy is below a given amount.
    // "0" is used for FEEDING, "1" is used for PLAYING.
    public static void noEnergy(int type)
    {
        if(lightSwitch) {
            switch(type)
            {
                case 0:
                    System.out.println("Pet rock does not have enough energy to be fed");
                    break;
                case 1:
                    System.out.println("Pet rock does not have enough energy to play");
                    break;
                default:
                    System.out.println("INCORRECT input in noEnergy(int) in Output.java");
                    break;
            }
        }
    }
    
    // Used in the switch statement for gameplay loop in PetRockMain.java
    // User enters "5".
    public static void gameExit()
    {
        if(lightSwitch) {
            System.out.println("\nExiting Application...");
        }
    }
    
    // Used in gameplay loop in PetRockMain.java
    // Displays user-input menu.
    // Takes in feed and play cooldowns to determine available options.
    public static void display(boolean cooldownFEED, boolean cooldownPLAY)
    {
        if(lightSwitch) {
            System.out.println("\n------------------------------------------------------------------------------------------------------");
            System.out.println(((cooldownFEED == true) ? "Feeding rock is on cooldown." :
                    "Press '1' to feed the rock"));
            System.out.println(((cooldownPLAY == true) ? "Playing with rock is on cooldown." : 
                    "Press '2' to play with the rock"));
            System.out.println("Press '3' to polish the rock");
            System.out.println("Press '4' to display the rock's status");
            System.out.println("Press '5' to exit the application");
            System.out.println("--------------------------------------------------------------------------------------------------------");
        }
    }
    
    // Used in method "getUserInput()" in PetRockMain.java
    // User entered a non-integer value or an integer that
    // is not between 1 and 5.
    public static void invalidInput()
    {
        if(lightSwitch) {
            System.out.println("\nInvalid input: Please enter a valid command.");
        }
    }
    
    // Used in method "getUserInput()" in PetRockMain.java
    // Indicates either feeding or playing is on cooldown.
    // "0" is used for FEEDING, "1" is used for PLAYING.
    public static void cooldown(int feedPlay)
    {
        if(lightSwitch) {
            switch(feedPlay)
            {
                case 0:
                    System.out.println("\nYou cannot feed the rock right now.");
                    System.out.println("Enter a different selection.");
                    break;
                case 1:
                    System.out.println("\nYou cannot play with the rock right now.");
                    System.out.println("Enter a different selection.");
                    break;
                default:
                    System.out.println("INCORRECT input  in cooldown(int) from Output.java.");
                    break;
           
			
			}
        }
	}
    public static void RanEventMessage(int propertyOfEvent, int typeOfEvent){
        	// Positive
		if (propertyOfEvent == 0) 
		{
			switch (typeOfEvent) 
			{
				case 0: 
					System.out.println("\nYour rock found a shiny pebble! It’s happier now!");
						
					break;
				case 1: 
					System.out.println("\nYour rock got some extra sleep! Energy restored!");
						
					break;
				case 2: 
				    System.out.println("\nYour rock found a snack! Satiated some Hunger!");
							
					break;
				case 3: 
					System.out.println("Your rock got flirted with your rock is feeling energized");
						
					break;
				case 4: 
					System.out.println("Your rock went for a relaxing walk emergy increased!");
						
					break;
				case 5: 
					System.out.println("Your rock had a really nice day");
							
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
						
					break;
				case 1: 
					System.out.println("\nYour rock is grumpy today. Hunger increased!");
					
					break;
				case 2: 
					System.out.println("\nYour rock smelled something delicious. Hunger increased!");
						
					break;
				case 3: 
					System.out.println("\nYour rock is feeling out of it today. Energy decreased!");
						
					break;
				case 4: 
					System.out.println("Your rock lost a friend.  Hunger increased and energy decreased");
						
					break;
				
				default: break;
			}
		}

    }
}

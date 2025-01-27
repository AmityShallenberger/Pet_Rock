package pet.rock;


public class PetRock
{

	private String name;
	private String mood;
	private int hunger;
	private int boredom;
	private int energy;
	
	
	public PetRock(String setName, String setMood, int setHunger, int setBoredom, int setEnergy)
	{
		name = setName;
		mood = setMood;
		hunger = setHunger;
		boredom = setBoredom;
		energy = setEnergy;
	}
	
        // Get-Methods.
            public String getName()
            {
                return name;
            }
        
            public String getMood()
            {
                return mood;
            }
            
            public int getHunger()
            {
                return hunger;
            }
            
            public int getBoredom()
            {
                return boredom;
            }
            
            public int getEnergy()
            {
                return energy;
            }
            
        // Set-Methods.
            public void setName(String newName)
            {
                name = newName;
            }
            
            public void setMood(String newMood)
            {
                mood = newMood;
            }
            
            public void setHunger(int newHunger)
            {
                hunger = newHunger;
            }
            
            public void setBoredom(int newBoredom)
            {
                boredom = newBoredom;
            }
            
            public void setEnergy(int newEnergy)
            {
                energy = newEnergy;
            }
        
        // Gameplay Methods.
            public void feedRock()
            {
                    hunger -= 2;
                    boredom += 1;
                    energy -= 1;
            }

            public void playRock()
            {
                    boredom -= 3;
                    hunger += 1;
                    energy -= 2;
            }

            public void polishRock() //(int diminishingReturn)
            {
                    hunger -= 1; // / (1 + diminishingReturn);
                    boredom -= 1;
                    energy += 1;
                    mood = "Happy";
                    // Diminshing returns somehow
            }

            public void updateStats()
            {
                    if (hunger < 0) 
                    {
                            hunger = 0;
                    }
                    if (hunger > 10) 
                    {
                            hunger = 10;
                    }

                    if (boredom < 0) 
                    {
                            boredom = 0;
                    }
                    if (boredom > 10) 
                    {
                            boredom = 10;
                    }

            }

            public void updateMood()
            {
                    if (energy <= 2)
                    {
                            mood = "Tired";
                    }
                    else if ((hunger < 4) && (boredom < 4) && (energy > 3))
                    {
                            mood = "Happy";
                    }
                    else if ( ( ((hunger < 7) && (hunger > 4)) || ((boredom < 7) && (boredom > 4)) ) && (energy > 3) )
                    {
                            mood = "Bored";
                    }
                    else if ( ((hunger > 7) || (boredom > 7)) || (energy <= 3) )
                    {
                            mood = "Sad";
                    }

            }

        // Miscellaneous Methods.
            @Override
            public String toString()
            {
                String output = "";
                
                // Setting up output.
                    output += "\tName: " + name;
                    output += "\n\tHunger: " + hunger;
                    output += "\n\tBoredom: " + boredom;
                    output += "\n\tEnergy: " + energy;
                    output += "\n\tMood: " + mood;
                
                return output;
            }
}
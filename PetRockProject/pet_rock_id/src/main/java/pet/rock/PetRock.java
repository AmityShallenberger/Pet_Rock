package pet.rock;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import com.google.gson.*;

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
		
		
	public void polishRock(int diminishingReturn)
	{
		// EDIT SO SOME OF THESE ONLY FIRE WHEN THE STATS ARE A CERTAIN AMOUNT E.G. WONT BE SAD IF POLISHED ON DIMINISHING RETURN IF CERTAIN STATS ARE HIGH ENOUGH.
		// done!
		switch (diminishingReturn) 
		{
			case 0:
				hunger -= 1;
				boredom -= 1;
				energy += 1;
				mood = "Happy"; 
				break;
			case 1:
				hunger -= 1;
				energy += 1;
				updateMood();
				break;
			case 2:
				hunger -= 1;
				updateMood();
				break;
			case 3: 
				updateMood();
				break;
			default: break;
		}	
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
		
		if (energy < 0) 
		{
				energy = 0;
		}
		if (energy > 10) 
		{
				energy = 10;
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

        public void getSavedData(File f) 
        {
            f = new File("SavedData.json");

            if (f.exists()) 
            {
                try 
                {
                    Scanner readFile = new Scanner(f);
                    String sData = "";
                    Gson gson = new Gson();

                    while (readFile.hasNext()) {
                        sData = sData + readFile.nextLine();
                    }

                    PetRock p = gson.fromJson(sData, PetRock.class);
				
                    readFile.close();

                    this.name = p.getName();
                    this.mood = p.getMood();
                    this.hunger = p.getHunger();
                    this.boredom = p.getBoredom();
                    this.energy = p.getEnergy();
                } 
			
                catch (Exception e) 
                {
                        System.err.println(e);
                }
            }
		}	

        public void makeSavedData(File f, PetRock p) {
            GsonBuilder gsonB = new GsonBuilder();

            try 
            {
                if (!f.exists())
                    f = new File("SavedData.json");
                
                String jsonData = gsonB.setPrettyPrinting().create().toJson(p);
                FileWriter fw = new FileWriter(f.getPath());

                fw.write(jsonData);	
                fw.close();
            } 
            catch (Exception e) 
            {
                System.err.println(e);
            }
        }

    // Miscellaneous Methods.
        @Override
        public String toString()
        {
            String output = "";

            // Setting up output.
                output += "\nRock Stats.";
                output += "\n\tName: " + name;
                output += "\n\tHunger: " + hunger;
                output += "\n\tBoredom: " + boredom;
                output += "\n\tEnergy: " + energy;
                output += "\n\tMood: " + mood;

            return output;
        }
}
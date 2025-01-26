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
	
	public void feedRock()
	{
		hunger -= 2;
		boredom += 1;
		energy -= 1;
	}
	
	public void playRock)()
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
	
	
}
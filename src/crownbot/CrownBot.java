package crownbot;

import sikuli.Sikuli;
import crownbot.BruteForce;

import royaleUI.Cards;
import royaleUI.MainMenu;
import royaleUI.Map;

public class CrownBot {
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	    MainMenu mm = new MainMenu();
	    Map m = new Map();
	    Cards c = new Cards();
	    BruteForce bf = new BruteForce();
	    
	    Preferences.getInstance().initiate();
	    Thread.sleep(3);
	    
		while(true)
		{
			
	
			// Check that we are on main battle menu
			while(!Sikuli.Wait(mm.battle, 15, .7, false, null))
			{
				System.out.println("You must be on the main menu of clash royale to run this bot!");
			}
			
			System.out.println(Preferences.getInstance().strategy);
			// If battle is on menu, click it
			Sikuli.Click(mm.battle, 0, 0, .7, null);
			
			GameState.getInstance().resetGameState();

			// Wait for King tower to appear, signaling start of game
			if(!Sikuli.Wait(m.blue_king, 5, .7, false, null))
			{
				System.out.println("Waiting for game to start....");
				Thread.sleep(5);
			}
			
			// Another loop for AI logic
			while(true)
			{
				// Check for map changes
				CheckMap();
				
				// Play a card (if enough elixir)
				bf.RunBruteForce();
				
				// Loop back to battle
				Sikuli.Click(mm.ok, 0, 0, .7, null);
				Sikuli.Click(mm.battle, 0, 0, .7, null);
				
			}
			
			
			
		}
			
	}
	
	public static void CheckMap()
	{
		Map m = new Map();
		
		// Case by case scenario to make changes to map
		
		// Check right side of the map
	    System.out.println(GameState.getInstance().redRightTower);
		if (GameState.getInstance().redRightTower == true)
		{
			if (Sikuli.Wait(m.dead_tower, 0, .6, false, null))
			{
				// Assume right tower is dead
				GameState.getInstance().redRightTower = false;
			}
		}
		
	}

}

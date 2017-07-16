package crownbot;

import java.util.ArrayList;

import royaleUI.Cards;
import royaleUI.MainMenu;
import royaleUI.Map;
import sikuli.Sikuli;

public class BruteForce {
	GameState gs = GameState.getInstance();
	Preferences pf = Preferences.getInstance();
	
	Cards c = new Cards();
	Map m = new Map();
	MainMenu mm = new MainMenu();
	
	// Prioritize cards based on deck order
	public void RunBruteForce()
	{
		ArrayList<Integer> al = new ArrayList<Integer>(); 
		int count = 0;
		// Check deck to know what card to play
		while (true)
		{
			// Check if game ended
			if(Sikuli.Click(mm.battle, 0, 0, .6, null) || Sikuli.Click(mm.ok, 0, 0, .6, null))
			{
				break;
			}
			int i = (int) (Math.random() * (7));
			
			while(true)
			{
				if (al.contains(i))
				{
					i = (int) (Math.random() *(7));
				}
				break;
			}
			
			
			if (Sikuli.Click(pf.deck[i], 0, 0, .8, null))
			{
				PlayCard(pf.deck[i]);
				count++;
				if (count == 4)
				{
					break;
				}
			}
			al.add(i);
		}
		
	}
	
	// Plays Card depending on game state
	private void PlayCard(String card)
	{
		GameState gs = GameState.getInstance();
		// Check if right tower is alive
		if (gs.redRightTower == true)
		{
			Sikuli.Click(m.red_crown, 0, 0, .6, null);
		}
		else
		{
			Sikuli.Click(m.red_crown, -400, 0, .6, null);
		}
			
	}
}

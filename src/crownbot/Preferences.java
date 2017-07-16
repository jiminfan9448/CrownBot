package crownbot;

import java.util.Arrays;

import royaleUI.Cards;

public class Preferences {

	private Cards c = new Cards();
	public String[] deck;
	public String strategy;
	
	private static Preferences pf = new Preferences();
	
	Preferences()
	{


		
	}
	
	public static Preferences getInstance()
	{
		return pf;
	}
	
	public void initiate()
	{
		String[] temp = {c.prince, c.knight, c.archer, c.bomber, c.peka, c.giant, c.valk, c.musky};
		this.deck = Arrays.copyOf(temp, temp.length);
		this.strategy = "bruteforce";
	}
	
}

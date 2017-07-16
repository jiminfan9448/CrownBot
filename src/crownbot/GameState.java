package crownbot;

public class GameState {
	
	public boolean redLeftTower = true;
	public boolean redRightTower = true;
	public boolean redKingTower = true;
	
	public boolean blueLeftTower = true;
	public boolean blueRightTower = true;
	public boolean blueKingTower = true;
	
	private static GameState gamestate = new GameState();
	
	GameState()
	{
		
	}
	
	public static GameState getInstance()
	{
		return gamestate;
	}
	
	public void resetGameState()
	{
		this.redLeftTower = true;
		this.redRightTower = true;
		this.redKingTower = true;
		
		this.blueLeftTower = true;
		this.blueRightTower = true;
		this.blueKingTower = true;
	}
}

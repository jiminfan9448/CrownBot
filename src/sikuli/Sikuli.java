package sikuli;

import org.sikuli.script.*;

public class Sikuli {

	public static boolean Click(String png, int offsetX, int offsetY, double conf, Region region)
	{
		// Pattern object based on conf
		Pattern p = new Pattern(png);
		// Screen object
		Screen s = new Screen();
		// Match object
		Match m;
		
		// Search within a region for the match if it exists
		if (region != null)
		{
			m = region.exists(p);
		}
		else
		{
			m = s.exists(p);
		}
		

		// If pattern matches, return true
		if (m == null)
		{
			System.out.println("Did not find " + png + " to click on");
			return false;
		}
		else if (m.getScore() < conf)
		{
			System.out.println("Did not find " + png + " to click on");
			return false;
		}
		else
		{
			// Calculates offset
			int v1 = m.getTarget().getX() - m.getTopLeft().getX();
			int v2 = m.getTarget().getY() - m.getTopRight().getY();
			
			// Clicks with an @offsetX and NEGATIVE offsetY, because we are
			// using relative position to x = 0, y = 0. The function normally
			// is returned as the center of the image
			try {
				s.click(p.similar((float) conf).targetOffset(offsetX - v1, offsetY - v2));
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Success Message
			System.out.println("Clicked on " + png + " , SCORE: " + Double.toString(m.getScore()));
		}
		return true;
	}
	
	public static boolean Wait(String png, int tSec, double conf, Boolean error,  Region region)
	{
		// Pattern object based on conf
		Pattern p = new Pattern(png);
		// Screen object
		Screen s = new Screen();
		// Match object
		Match m;
		
		//Default variable for error
		if (error.equals(null))
		{
			error = true;
		}
		
		long t1 = System.currentTimeMillis();
		long t2 = t1 + tSec * 1000;
		
		// Loop until @tSec is exceeded
		while (System.currentTimeMillis() < t2)
		{
			// Search within a specific region
			if (region != null)
			{
				m = region.exists(p);
			}
			else
			{
				m = s.exists(p);
			}
			// Keep waiting until image is found
			if (m == null)
			{
				continue;
			}
			else if (m.getScore() < conf)
			{
				continue;
			}
			else
			{
				System.out.println("Found " + png + " after " + Long.toString(System.currentTimeMillis() - t1) + " seconds!");
	            System.out.println("WAIT SCORE: " + Double.toString(m.getScore()));
	            return true;
			}
			
		}
		
	    // If @error is True and we reach this point, we will print ERROR message and
	    // terminate script
		if (error == true)
		{
			System.out.println("ERROR: " + png + " did not appear in " + Integer.toString(tSec) + "seconds");
		}
		
		return false;
	}
	
	public static boolean WaitVanish(String png, int tSec,  Region region)
	{
		Pattern p = new Pattern(png);
		// Screen object
		Screen s = new Screen();
		// Match object
		Match m;
		
		long t1 = System.currentTimeMillis();
		long t2 = t1 + tSec * 1000;
		
		// Loop until @tSec is exceeded
		while (System.currentTimeMillis() < t2)
		{
			// Search within a specific region
			if (region != null)
			{
				m = region.exists(p);
			}
			else
			{
				m = s.exists(p);
			}
			
			if (m == null)
			{
	            System.out.println("After " + Long.toString(System.currentTimeMillis() - t1) + " seconds, " + png + " is gone.");
	            return true;
			}
		}
		return false;
	}
	
}

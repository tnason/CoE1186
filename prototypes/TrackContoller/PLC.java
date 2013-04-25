/*
* Author(s): Cameron Dashti
* Updated: 25 – 4 – 2013
* Purpose: …
*/

package TLTTC;

public class PLC
{
	public PLC()
	{


	}

	public boolean verifyPLC()
	{
		return true;
	}

	public void checkAuthority()
	{


	}

	public void checkSwitch()
	{


	}

	public void checkTrack()
	{


	}

	public void checkCrossing(Block occupied)
	{
		if(occupied.isCrossing())
		{
			occupied.setCrossing(true);
		}
	}

	public void doMethod()
	{
		System.out.println("Works");

	}

	public void doMethod(int x)
	{
		
		System.out.println("Works " + x);
	}
}
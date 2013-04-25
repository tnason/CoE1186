/*
* Author(s): Cameron Dashti
* Updated: 25 – 4 – 2013
* Purpose: Simulate PLC program that runs on the track controller
*/

package TLTTC;
import java.util.*;

public class PLC
{
	public PLC()
	{


	}

	public boolean verifyPLC()
	{
		return true;
	}

	public double checkAuthority(Hashtable<Integer, Block> newState,
							   Block newBlock)
	{
		try
		{
			Block nextBlock = newBlock.getStopNode().getNextBlock(newBlock);

			if(!nextBlock.isOccupied())
			{
				return nextBlock.getLength();
			}
		}
		catch(Exception e){ }
		

		return 0.0;
	}

	public void checkSwitch()
	{


	}

	public boolean checkTrack(Hashtable<Integer, Block> oldState,
						      Hashtable<Integer, Block> newState,
						      Block newBlock)
	{

		return !oldState.get(newBlock.getID()).isOccupiedNoMaintenance()
			   && newState.get(newBlock.getID()).isOccupiedNoMaintenance() ;

	}

	public void checkCrossing(Block occupied)
	{
		if(occupied.isCrossing())
		{
			occupied.setCrossing(true);
		}
	}
}
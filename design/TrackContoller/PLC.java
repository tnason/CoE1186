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

	// Track controller will ask if this class is a PLC. returns true
	public boolean verifyPLC()
	{
		return true;
	}

	// to check fixed block authority, look at the next block and
	// see if is unoccupied. Returns 0 if the next block is occupied
	// or sends the distance of the next block.
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
			// DID not recieve rout from CTC

		/*

		*/

	}

	// Check for consistency of track while train moves over it.
	public boolean checkTrack(Hashtable<Integer, Block> oldState,
						      Hashtable<Integer, Block> newState,
						      Block newBlock)
	{

		return !oldState.get(newBlock.getID()).isOccupiedNoMaintenance()
			   && newState.get(newBlock.getID()).isOccupiedNoMaintenance() ;

	}

	// Checks crossing when the 
	public void checkCrossing(Block occupied)
	{
		if(occupied.isCrossing() && occupied.isOccupied() )
		{
			occupied.setCrossing(true); // enable crossing
		}
		else
		{
			occupied.setCrossing(false); // else crossing is disabled
 		}
	}
}
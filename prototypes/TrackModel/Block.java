package TLTTC;
public abstract class Block {
	/*unique identification number*/
	protected int blockID;
	
	boolean occupied;
	
	/*keep track of nodes*/
	protected Node startNode;
	protected Node stopNode;
	
	/*	allowable directions
	 * 		start -> stop = 1
	 * 		stop -> start = 2
	 * 		any -> any    = 3
	 */
	protected int allowedDirections;
	
	public void setOccupation(boolean state)
	{
		occupied = state;
	}
	
	public boolean isOccupied()
	{
		return false;
	}
	
	public double getGrade()
	{
		return 0;
	}
	
	public double getLength()
	{
		return 0;
	}
	
	public double getPowerLimit()
	{
		return 0;
	}
	
	public double getSpeedLimit()
	{
		return 0;
	}
	
	public boolean isUnderground()
	{
		return false;
	}
	
	public Node getStartNode()
	{
		return startNode;
	}
	
	public Node getStopNode()
	{
		return stopNode;
	}
	
	public int getAllowedDirection()
	{
		return allowedDirections;
	}
	
	public Block getNextBlock(int direction)
	{
		/*direction uses same number convention as allowableDirection*/
		return null;
	}
	
	public Block getNextBlock(Node lastNode)
	{
		/*
			it is easier to implement if we keep track of last node passed
		
			Tom: To do this, the train needs a way to query the block it is occupying to find which node it is entering/exiting on
			This could be done relatively easily if someone passes me the yard node when dispatching a new train
			Then, Block could resolve my direction from the node that I entered it on (+ switches, etc)
			I could then set a new block's entry node to the current block's exit node (which it returns to me after I pass it the node I entered on)
		*/
		return null;
	}
	
	public double[] getAbsolutePosition(double distance)
	{
		return new double[3]; //[x,y,z]
	}
	
}

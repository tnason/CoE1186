//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

public class Train implements Comparable<Train>
{
	public final int trainNumber;

	private boolean locationValid;
	private boolean stoppingDistanceValid;
	private double stoppingDistance;
	private boolean blockValid;
	private Block block;
	private Node previousNode;
	private Node nextNode;
	private double location;
	private long time;
	private int passengerCount;

	public Train(int trainNumber, long time)
	{
		this.trainNumber = trainNumber;
		location = 0;
		stoppingDistance = 0;
		block = null;
		previousNode = null;
		nextNode = null;
		locationValid = false;
		stoppingDistanceValid = false;
		blockValid = false;
		this.time = time;
	}

	public Train(int trainNumber, long time, double location, double stoppingDistance, Block block, Node previousNode, Node nextNode)
	{
		this.trainNumber = trainNumber;
		this.stoppingDistance = stoppingDistance;
		this.location = location;
		this.block = block;
		this.previousNode = previousNode;
		this.nextNode = nextNode;
		locationValid = true;
		stoppingDistanceValid = true;
		blockValid = true;
		this.time = time;
	}

	/*Setters for location and stopping distance, and setters for flags to ensure the data is accurate*/

	public boolean isLocationValid()
	{
		return locationValid;
	}

	public void setLocationValid(boolean valid)
	{
		locationValid = valid;
	}

	public boolean isStoppingDistanceValid()
	{
		return stoppingDistanceValid;
	}

	public void setStoppingDistanceValid(boolean valid)
	{
		stoppingDistanceValid = valid;
	}

	public boolean isBlockValid()
	{
		return blockValid;
	}

	public void setBlockValid(boolean valid)
	{
		blockValid = valid;
	}

	public double getStoppingDistance()
	{
		return stoppingDistance;
	}

	public void setStoppingDistance(double stoppingDistance, long time)
	{
		this.stoppingDistance = stoppingDistance;
		stoppingDistanceValid = true;
		this.time = time;
	}

	public double getLocation()
	{
		return location;
	}

	public void setLocation(double location, long time)
	{
		this.location = location;
		locationValid = true;
		this.time = time;
	}
	
	public Block getBlock()
	{
		return block;
	}

	public Node getPreviousNode()
	{
		return previousNode;
	}

	public Node getNextNode()
	{
		return nextNode;
	}

	public void setBlock(Block block, Node previousNode, Node nextNode, long time)
	{
		this.block = block;
		this.previousNode = previousNode;
		this.nextNode = nextNode;
		this.time = time;
	}

	/*Comparator to ensure that the trains in a list are sorted in order as they are traversing the track*/
	/*NOT YET IMPLEMENTED*/

	public int compareTo(Train train)
	{
		if(location < train.getLocation())
		{
			return -1;
		}
		else if(location > train.getLocation())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}

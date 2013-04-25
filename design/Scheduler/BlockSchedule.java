//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

import java.sql.*;

public class BlockSchedule
{
  private long entryTime;
	private long traverseTime;
	private Node previousNode;
	private Node nextNode;
	private Block block;
	private double speed;
	
	public BlockSchedule(long entryTime, long traverseTime, double speed, Block block, Node previousNode, Node nextNode) throws Exception
	{
		/*if(!block.getStartNode().equals(previousNode) || !block.getStartNode().equals(nextNode) || !block.getStopNode().equals(previousNode) || !block.getStopNode().equals(nextNode))
		{
			throw new Exception("Block does not contain nodes!!!");
		}
*/
		this.entryTime = entryTime;
		this.traverseTime = traverseTime;
		this.speed = speed;
		this.previousNode = previousNode;
		this.nextNode = nextNode;
		this.block = block;
	}

	public void setEntryTime(long time)
	{
		this.entryTime = time;
	}

	public void setTraverseTime(long time)
	{
		this.traverseTime = time;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public void setPreviousNode(Node node)
	{
		this.previousNode = node;
	}

	public void setNextNode(Node node)
	{
		this.nextNode = node;
	}

	public void setBlock(Block block)
	{
		this.block = block;
	}

	public long getEntryTime()
	{
		return entryTime;
	}

	public long getTraverseTime()
	{
		return traverseTime;
	}

	public long getExitTime()
	{
		return entryTime + traverseTime;
	}

	public double getSpeed()
	{
		return speed;
	}

	public Node getPreviousNode()
	{
		return previousNode;
	}

	public Node getNextNode()
	{
		return nextNode;
	}

	public Block getBlock()
	{
		return block;
	}

	public String toString()
	{
		String s = "Block: " + block;
		s = s.concat("\nPrevious Node: " + previousNode);
		s = s.concat("\nNext Node: " + nextNode);
		s = s.concat("\nEntry Time: " + new Time(entryTime));
		s = s.concat("\nExit Time: " + new Time(traverseTime + entryTime));
		s = s.concat("\nSpeed: " + speed);

		return s;
	}
}
	

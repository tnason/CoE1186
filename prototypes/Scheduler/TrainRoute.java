//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

import java.util.*;
import java.sql.*;

public class TrainRoute
{
  private int trainNumber;
	private long startTime;
	private long yardTime;
	private long duration;
	private ArrayList<BlockSchedule> route;

	public TrainRoute(int trainNumber, long startTime, long yardTime)
	{
		this.trainNumber = trainNumber;
		this.startTime = startTime;
		this.yardTime = yardTime;
		this.duration = 0;
		this.route = new ArrayList<BlockSchedule>();
	}

	public String toString()
	{
		String s = "Train Number: " + trainNumber;
		s = s.concat("\nStart Time: " + new Time(startTime));
		s = s.concat("\nYard time: " + new Time (yardTime));
		s = s.concat("\nRoute Duration: " + duration);

		for(int i = 0; i< route.size(); i++)
		{
			s = s.concat("\n" + route.get(i));
		}

		return s;
	}

	/*Update the duration of Route*/

	private void updateDuration()
	{
		if(route.size() == 0)
		{
			duration = 0;
		}
		else
		{
			BlockSchedule bs = route.get(route.size() - 1);

			duration = bs.getExitTime() - startTime;
		}
	}

	public int size()
	{
		return route.size();
	}

	public long getRouteStartTime()
	{
		return startTime;
	}

	public long getYardTime()
	{
		return yardTime;
	}

	/*Get the time the train is expected to arrive at the yard. Returns -1 if train is not routed to yard.*/

	public long getYardArrivalTime()
	{
		BlockSchedule bs = route.get(route.size()-1);

		if(bs.getPreviousNode().getNodeType() == constData.NodeType.Yard && bs.getPreviousNode().getNodeType() == constData.NodeType.Yard)
		{
			return (bs.getEntryTime() + bs.getTraverseTime());
		}
		else
		{
			return -1;
		}
	}

	public long getRouteDuration()
	{
		return duration;
	}

	public Iterator<BlockSchedule> getIterator()
	{
		return route.iterator();
	}

	public BlockSchedule getIndex(int index)
	{
		return route.get(index);
	}

	public int getTrainNumber()
	{
		return trainNumber;
	}

	public boolean add(BlockSchedule bs) throws Exception
	{
		if(route.size() > 0 && bs.getEntryTime() < route.get(route.size() - 1).getExitTime())
		{
			throw new Exception("BlockSchedule added out of order!!!");
		}

		route.add(bs);
		updateDuration();

		return true;
	}

	public boolean set(int index, BlockSchedule bs)
	{
		route.set(index, bs);

		return true;
	}
	public BlockSchedule get(int index)
	{
		return route.get(index);
	}

	public BlockSchedule getFirst()
	{
		return route.get(0);
	}

	public BlockSchedule getLast()
	{
		return route.get(route.size() - 1);
	}

	public boolean remove()
	{
		int size = route.size();

		if(size == 0)
		{
			return false;
		}
		
		route.remove(size - 1);

		updateDuration();

		return true;
	}

	public boolean removeFrom(int index)
	{
		int size = route.size();

		if(size <= index || index < 0)
		{
			return false;
		}

		for(int i = size - 1; i >= index; i--)
		{
			route.remove(i);
		}

		updateDuration();

		return true;
	}

	/*Checks if trains collide when going towards each other*/

	public boolean isForwardCollision(Block block, Node previousNode, long entryTime, long exitTime)
	{
		for(int i = route.size() - 1; i >= 0; i--)
		{
			BlockSchedule bs = route.get(i);
			long bsEntryTime = bs.getEntryTime();
			long bsExitTime = bs.getExitTime();

			/*Returns true if trains are on the same block and facing opposite directions, returns true*/

			if(block.equals(bs.getBlock()) && previousNode.equals(bs.getNextNode()) && ((bsEntryTime <= entryTime && entryTime < bsExitTime) || (bsEntryTime <= exitTime && exitTime < bsExitTime) || (entryTime <= bsEntryTime && bsExitTime <= exitTime)))
			{
				return true;
			}
		}

		return false;
	}

	/*Checks if a train will collide into the train ahead of it when both are going in the same direction*/

	public boolean isRearCollision(Block block, Node previousNode, long entryTime, long exitTime)
	{
		for(int i = route.size() - 1; i >= 0; i--)
		{
			BlockSchedule bs = route.get(i);
			long bsEntryTime = bs.getEntryTime();
			long bsExitTime = bs.getExitTime();
			
			/*Returns true if trains are on the same block and facing the same direction*/

			if(block.equals(bs.getBlock()) && previousNode.equals(bs.getPreviousNode()) && ((bsEntryTime < entryTime && entryTime < bsExitTime) || (bsEntryTime < exitTime && exitTime < bsExitTime) || (entryTime <= bsEntryTime && bsExitTime <= exitTime)))
			{
				return true;
			}
		}

		return false;
	}
}

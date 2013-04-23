package TLTTC;

import java.util.*;

public class RouteSchedule
{
 	public static long ROUTE_LENGTH = 60 * 60 * 1000;//milliseconds
	public static double PERCENT_SPEED = .75;

	private Hashtable<Integer, TrainRoute> route;

	public static void main(String[] args) throws Exception
	{
		int numBlocks = 7;
		int numNodes = numBlocks + 1;
		Block[] blocks = new Block[numBlocks];
		Node[] nodes = new Node[numNodes];
		ArrayList<Train> train = new ArrayList<Train>();
		OperatorSchedule schedule = new OperatorSchedule();
		RouteSchedule rs = new RouteSchedule();

		nodes[0] = new YardNode(0, 0, 0);
		
		for(int i = 1; i < nodes.length - 1;i++)
		{
			nodes[i]= new ConnectorNode(i * i, i * i, i * i);
		}

		nodes[nodes.length - 1] = new YardNode((nodes.length - 1) * (nodes.length - 1), (nodes.length - 1) * (nodes.length - 1), (nodes.length - 1) * (nodes.length - 1));

		for(int i = 0; i < blocks.length; i++)
		{
			blocks[i] = new LinearBlock(nodes[i], nodes[i+1], i, 0);
			nodes[i].setOutput(blocks[i]);
			nodes[(i+1) % blocks.length].setInput(blocks[i]);
		}

		train.add(new Train(0, 0));
		schedule.add("first","last",0,System.currentTimeMillis(),OperatorStatus.SHIFTFIRSTHALF);

		train.get(0).setBlock(blocks[0], nodes[0], nodes[1], 0);
		

		ROUTE_LENGTH = 10 * 1000;

		rs.routeTrains(System.currentTimeMillis(), train, schedule);		

		System.out.println(rs.toString());
	}

	public RouteSchedule()
	{
		route = new Hashtable<Integer, TrainRoute>();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for(TrainRoute tr : route.values())
		{
			sb.append("\n" + tr);			
		}

		return sb.toString();
	}

	public boolean add(TrainRoute tr)
	{
		if(!route.containsKey(tr.getTrainNumber()))
		{
			route.put(tr.getTrainNumber(), tr);

			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean remove(TrainRoute tr)
	{
		return (route.remove(tr) != null);
	}

	public int getSize()
	{
		return route.size();
	}

	public boolean remove(int trainNumber)
	{
		TrainRoute tr = route.get(trainNumber);

		if(tr == null)
		{
			return false;
		}

		return remove(tr);
	}

	public Iterator<TrainRoute> getIterator()
	{
		return route.values().iterator();
	}

	public TrainRoute getRoute(int trainNumber)
	{
		return route.get(trainNumber);
	}

	public void routeTrains(long start, ArrayList<Train> trains, OperatorSchedule schedule) throws Exception
	{
		if(trains.size() != schedule.size())
		{
			throw new Exception("Schedule and trains sizes are not equal!!!");
		}

		int size = trains.size();

		if(size == 0)
		{
			throw new Exception("Schedule and trains are empty!!!");
		}

		route.clear();

		for(int i = 0; i < size; i++)
		{
			Train train = trains.get(i);
			Operator operator = schedule.getOperator(train.trainNumber);
			TrainRoute tr;

			if(operator == null)
			{
				throw new Exception("Cannot find train in schedule!!!");
			}
			else if(operator.status == OperatorStatus.SHIFTFIRSTHALF)
			{
				tr = new TrainRoute(train.trainNumber, start, operator.breakStart);
			}
			else if(operator.status == OperatorStatus.SHIFTSECONDHALF)
			{
				tr = new TrainRoute(train.trainNumber, start, operator.shiftEnd);
			}
			else
			{
				throw new Exception("Cannot route trains that are in the yard!!!");
			}

			long t = (long)Math.ceil(1000 * train.getBlock().getLength() / (0 + .5 * (RouteSchedule.PERCENT_SPEED * train.getBlock().getSpeedLimit() - 0)));
			tr.add(new BlockSchedule(start, t, RouteSchedule.PERCENT_SPEED * train.getBlock().getSpeedLimit(), train.getBlock(), train.getPreviousNode(), train.getNextNode()));
			Node previousNode = train.getNextNode();
			Block block = previousNode.getNextBlock(train.getBlock());
			Node nextNode = block.getNextNode(previousNode);
			tr.add(new BlockSchedule(start + t, 0, RouteSchedule.PERCENT_SPEED * block.getSpeedLimit(), block, previousNode, nextNode));
			if(!add(tr))
			{
				throw new Exception("Schedule has duplicate operators!!!");
			}				
		}

		routeTrains();
	}

	private void routeTrains() throws Exception
	{
		Stack<int[]> startStack;
		Stack<boolean[]> directionStack;
		TrainRoute[] trainRoute;
		int[] start;
		boolean[] direction;
		boolean doingWork;

		startStack = new Stack<int[]>();
		directionStack = new Stack<boolean[]>();
		trainRoute = route.values().toArray(new TrainRoute[route.size()]);
		start = new int[trainRoute.length];
		direction = new boolean[trainRoute.length];

		for(int i = 0; i < trainRoute.length; i++)
		{
			start[i] = 1;
			direction[i] = true;
		}

		do
		{
			doingWork = false;

			for(int i = 0; i < trainRoute.length; i++)
			{
				System.out.println(i + "\n");
				Block block;
				Node previousNode;
				Node nextNode;
				BlockSchedule bs;
				long entryTime;
				long traverseTime;
				double v;
				double vo;
				double d;
				double t;
				int size;

				if(trainRoute[i].getRouteDuration() >= RouteSchedule.ROUTE_LENGTH)
				{
					continue;
				}

				
				bs = trainRoute[i].get(start[i]);
				block = bs.getBlock();
				previousNode = bs.getPreviousNode();
				nextNode = bs.getNextNode();

				if(previousNode.getNodeType() == constData.NodeType.Yard && nextNode.getNodeType() == constData.NodeType.Yard)
				{
					continue;
				}

				doingWork = true;
				entryTime = bs.getEntryTime();
				traverseTime = bs.getTraverseTime();
				size = trainRoute[i].size();

				if(size == 1)
				{
					vo = 0;
				}
				else
				{
					vo = trainRoute[i].get(size - 2).getSpeed();
				}

				d = block.getLength();
				v = RouteSchedule.PERCENT_SPEED * block.getSpeedLimit();
				t = d / (vo + .5 * (v - vo)); // v = vo + a * t, d = vo * t + .5 * a * t ^ 2, replace a and solve for t
				traverseTime = traverseTime + (long)Math.ceil(1000 * t);
				bs.setTraverseTime(traverseTime);
				
				if(!isForwardCollision(i, trainRoute, block, previousNode, entryTime, traverseTime))
				{
					BlockSchedule temp;

					if(nextNode.getNodeType() == constData.NodeType.Switch)
					{
						startStack.push(start);
						directionStack.push(direction);
						start = Arrays.copyOf(start, start.length);
						direction = Arrays.copyOf(direction, direction.length);
						direction[i] = true;
						previousNode = nextNode;
						block = previousNode.getNextBlock(block);
						nextNode = block.getNextNode(previousNode);
						temp = new BlockSchedule(entryTime + traverseTime, 0, RouteSchedule.PERCENT_SPEED * block.getSpeedLimit(), block, previousNode, nextNode);
					}
					else if(nextNode.getNodeType() == constData.NodeType.Yard)// && trainRoute[i].getYardTime() >= trainRoute[i].getLast().getExitTime())
					{
						previousNode = nextNode;
						block = null;
						temp = new BlockSchedule(entryTime + traverseTime, 0, 0, block, previousNode, nextNode);
					}
					else
					{
						previousNode = nextNode;
						block = previousNode.getNextBlock(block);
						nextNode = block.getNextNode(previousNode);
						temp = new BlockSchedule(entryTime + traverseTime, 0, RouteSchedule.PERCENT_SPEED * block.getSpeedLimit(), block, previousNode, nextNode);
					}

					if(block != null && !isRearCollision(i, trainRoute, block, previousNode, entryTime, traverseTime))
					{
						if(start[i] < size - 1)
						{
							trainRoute[i].set(start[i], temp);
						}
						else
						{
							trainRoute[i].add(temp);
						}

						start[i]++;
					}
				}
				else
				{
					start = startStack.pop();
					direction = directionStack.pop();

					if(direction[i])
					{
						BlockSchedule temp;

						direction[i] = false;
						bs = trainRoute[i].get(start[i]);
						previousNode = bs.getNextNode();
						//block = previousNode.getDivergentBlock(bs.getBlock());
						nextNode = block.getNextNode(previousNode);
						temp = new BlockSchedule(entryTime + traverseTime, 0, RouteSchedule.PERCENT_SPEED * block.getSpeedLimit(), block, previousNode, nextNode);
	
						if(start[i] < size - 1)
						{
							trainRoute[i].set(start[i], temp);
						}
						else
						{
							trainRoute[i].add(temp);
						}

						start[i]++;
					}
				}				
			}
		}
		while(doingWork);
	}

	private boolean isRearCollision(int index, TrainRoute[] tr, Block block, Node previousNode, long entryTime, long traverseTime)
	{
		for(int i = 0; i < tr.length; i++)
		{
			if(i != index && tr[i].isRearCollision(block, previousNode, entryTime, traverseTime))
			{
				return true;
			}
		}

		return false;
	}

	private boolean isForwardCollision(int index, TrainRoute[] tr, Block block, Node previousNode, long entryTime, long traverseTime)
	{
		for(int i = 0; i < tr.length; i++)
		{
			if(i != index && tr[i].isForwardCollision(block, previousNode, entryTime, traverseTime))
			{
				return true;
			}
		}

		return false;
	}
}

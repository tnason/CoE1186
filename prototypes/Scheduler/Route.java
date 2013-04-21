package TLTTC;

import java.util.*;

@SuppressWarnings("unchecked")  
public class Route
{
  public static long ROUTE_LENGTH = 60 * 60 * 1000;//milliseconds
	public static double PERCENT_SPEED = .75;
	
	private Hashtable<Integer, LinkedList<TrainRoute>> routes;

	public static void main(String[] args) throws Exception
	{
		int numBlocks = 7;
		int numNodes = numBlocks;
		Block[] blocks = new Block[numBlocks];
		Node[] nodes = new Node[numNodes];

		for(int i = 0; i < nodes.length;i++)
		{
			nodes[i]= new ConnectorNode(i, i, i);
		}

		for(int i = 0; i < blocks.length; i++)
		{
			blocks[i] = new LinearBlock(nodes[i], nodes[(i+1) % blocks.length], i, 0);
			nodes[i].setOutput(blocks[i]);
			nodes[(i+1) % blocks.length].setInput(blocks[i]);
		}

		ROUTE_LENGTH = 10 * 1000;

		ArrayList<TrainRoute>[] route = calculateRoute(new int[]{0}, new long[]{0}, new long[]{Long.MAX_VALUE}, new Block[]{blocks[0]}, new Node[]{nodes[0]}, new Node[]{nodes[1]});

		for(int i = 0; i < route.length; i++)
		{
			for(int j = 0; j < route[i].size(); j++)
			{
				System.out.println(route[i].get(j));
			}
		}
	}

	private static boolean timeOverlap(ArrayList<TrainRoute>[] routes, long entryTime, long traverseTime, Block block, Node previousNode, Node nextNode)
	{
		for(int i = 0; i < routes.length - 1; i++)
		{
			int size = routes[i].size();

			for(int j = 0 ; j < size; j++)
			{
				TrainRoute tr;
				long blockEntryTime;
				long blockExitTime;

				tr = routes[i].get(j);
				blockEntryTime = tr.getEntryTime();
				blockExitTime = blockEntryTime + tr.getTraverseTime();

				if(block == tr.getBlock() && (blockEntryTime <= entryTime && entryTime <= blockExitTime) || (blockEntryTime <= (entryTime + traverseTime) && (entryTime + traverseTime) <= blockExitTime) || (entryTime < blockEntryTime &&  blockExitTime < (entryTime + traverseTime)))
				{
					return true;
				}
			}
		}

		return false;
	}
	public static ArrayList<TrainRoute>[] calculateRoute(int[] trainNumbers, long[] startTimes, long[] yardTimes, Block[] blocks, Node[] previousNodes, Node[] nextNodes) throws Exception
	{
		if(trainNumbers.length != startTimes.length || startTimes.length != blocks.length || blocks.length != previousNodes.length || previousNodes.length != nextNodes.length)
		{
			throw new Exception("Array sizes are not equal!!!");
		}

		ArrayList<TrainRoute>[] routes = new ArrayList[trainNumbers.length];//I do not know if this warning can be fixed

		for(int i = 0 ; i < trainNumbers.length; i++)
		{
			routes[i] = new ArrayList<TrainRoute>();
			double d = blocks[i].getLength();
			double v = PERCENT_SPEED * blocks[i].getSpeedLimit();
			double t = d / (0 + .5 * (v - 0)); // v = vo + a * t, d = vo * t + .5 * a * t ^ 2, replace a and solve for t

			routes[i].add(new TrainRoute(startTimes[i], 0, v, blocks[i], previousNodes[i], nextNodes[i]));
		}

		return(calculateRoute(0, routes, startTimes, yardTimes));
	}

	private static ArrayList<TrainRoute>[] calculateRoute(int start, ArrayList<TrainRoute>[] routes, long[] startTimes, long[] yardTimes) throws Exception
	{
		boolean doingWork;
		long time;
		double v;
		double vo;
		double d;
		double t;
		int[] startIndex;
		
		if(start == routes.length)
		{
			start =0;
		}
		else if (start > routes.length)
		{
			throw new Exception("Start: " + start + " must be less than or equal to Routes: " + routes.length);
		}

		startIndex = new int[routes.length];

		for(int i =0; i < startIndex.length; i++)
		{
			startIndex[i] = routes[i].size() - 1;
		}


		do
		{
			doingWork = false;

			for(int i = start; i < routes.length; i++)
			{
				System.out.println("\n" + i);
				int size = routes[i].size();
				TrainRoute trainRoute = routes[i].get(size-1);
				long startTime = trainRoute.getEntryTime();
				long traverseTime = trainRoute.getTraverseTime();

				if((startTime + traverseTime) - startTimes[i] >= Route.ROUTE_LENGTH)
				{
					continue;
				}

				Node previousNode = trainRoute.getPreviousNode();
				Block block = trainRoute.getBlock();
				Node nextNode = trainRoute.getNextNode();

				if(nextNode.getNodeType() == constData.NodeType.Yard && (startTime + traverseTime) >= yardTimes[i])
				{
					continue;
				}

				doingWork = true;

				if(size == 1)
				{
					vo = 0;
				}
				else
				{
					vo = routes[i].get(size-2).getSpeed();
				}

				d = block.getLength();
				v = PERCENT_SPEED * block.getSpeedLimit();
				t = d / (vo + .5 * (v - vo)); // v = vo + a * t, d = vo * t + .5 * a * t ^ 2, replace a and solve for t
				traverseTime = traverseTime + (long)Math.ceil(1000 * t);

				trainRoute.setTraverseTime(traverseTime);

				if(!timeOverlap(routes, startTime, traverseTime, block, previousNode, nextNode))
				{
					TrainRoute temp;

					previousNode = nextNode;
					block = previousNode.getNextBlock(block);
					nextNode = block.getNextNode(previousNode);
					
					temp = new TrainRoute(startTime + traverseTime + 1, 0, v, block, previousNode, nextNode);
					routes[i].add(temp);
					temp.setSpeed(v);
					//vo = v;

					if(previousNode.getNodeType() == constData.NodeType.Switch)
					{
						if(calculateRoute(i + 1, routes, startTimes, yardTimes) != null)
						{
							return routes;
						}

						temp.setTraverseTime(0);
						//temp.setBlock(previousNode.getDivergentBlock());
						temp.setNextNode(block.getNextNode(previousNode));

						if(calculateRoute(i + 1, routes, startTimes, yardTimes) != null)
						{
							return routes;
						}
					}
					else
					{
						block = nextNode.getNextBlock(block);
						previousNode = nextNode;
						nextNode = block.getNextNode(previousNode);
					}
				}
				else
				{
					for(int j = 0; j < startIndex.length; j++)
					{
						for(int k = routes[j].size() - 1; k > startIndex[j]; k--)
						{
							routes[j].remove(k);
						}
					}

					return null;
				}
			}
			
		}
		while(doingWork);

		return routes;		
	}	
}

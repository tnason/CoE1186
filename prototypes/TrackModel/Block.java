package TLTTC;
import java.util.*;

public abstract class Block {
	
	public Block(Node start, Node stop, int c){
		startNode = start;
		stopNode = stop;
		controller.add(c);
	}
	
	public String toString(){
		return "Abstract Block Class";
	}
	
	// In constructor of block, add controller number(s) to this list
	protected ArrayList<Integer> controller = new ArrayList<Integer>();
	
	/*unique identification number*/
	protected int blockID;
	
	boolean occupied;
	boolean maintenance;
	
	/*keep track of nodes*/
	protected Node startNode;
	protected Node stopNode;


	
	/*	allowable directions
	 * 		start -> stop = 1
	 * 		stop -> start = 2
	 * 		any -> any    = 3
	 */
	protected int allowedDirections;
	

	public void setOccupation(boolean state){
		occupied = state;
	}
	public void setMaintenance(boolean state){
		maintenance = state;
	}
	
	public void setID(int id)
	{
		blockID = id;
	}
	
	public int getID()
	{
		return blockID;
	}

	public boolean isOccupied(){
		return occupied;
	}
	
	public double getGrade(){
		//stopz - startz / linear distance
		double dx = stopNode.getX() - startNode.getX();
		double dy = stopNode.getY() - startNode.getY();
		double dz = stopNode.getZ() - startNode.getZ();
		
		double dist = Math.sqrt(dx*dx * dy*dy);
		
		return dz/dist;
	}
	
	public double getLength(){
		//this only works for linear blocks..
		double dx = stopNode.getX() - startNode.getX();
		double dy = stopNode.getY() - startNode.getY();
		double dz = stopNode.getZ() - startNode.getZ();		
		
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}
	
	public double getPowerLimit(){
		return 0;
	}
	
	public double getSpeedLimit(){
		return 0;
	}
	
	public boolean isUnderground(){
		return false;
	}
	
	public Node getStartNode(){
		return startNode;
	}
	
	public Node getStopNode(){
		return stopNode;
	}
	
	public int getAllowedDirection(){
		return allowedDirections;
	}
	
	//		***Deprecated - let me know if you need this***
	//public Block getNextBlock(int direction){
	//	/*direction uses same number convention as allowableDirection*/
	//	return null;
	//}
	
	public Block getNextBlock(Node lastNode){
		/*it is easier to implement if we keep track of last node passed*/
		Node nextNode = getNextNode(lastNode);
		try {
			return nextNode.getNextBlock(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public double[] getAbsolutePosition(double distance){
		return new double[3]; //[x,y,z]
	}
  
  public Node getYardNode(){
    //return a yard node if it belongs to block else return null
	  if(startNode.getNodeType() == constData.NodeType.Yard)
		  return startNode;
	  if(stopNode.getNodeType() == constData.NodeType.Yard)
		  return stopNode;
	  return null;
  }
  
  public Node getNextNode(Node lastNode){
  	//make sure a valid node is passed in - return null if not    
    if(lastNode == stopNode)
    	return startNode;
    if(lastNode == startNode)
    	return stopNode;
    return null;
  }

  public ArrayList<Integer> getController(){
    // Should return a list of the controller(s) that a block is under
    return controller;
  }

  public void addController(int c)
  {
  	controller.add(c);
  }
  	
}

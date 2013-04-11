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
	
	
	
	/*unique identification number*/
	protected int blockID;
	
	boolean occupied;
	boolean maintenance;
	
	/*keep track of nodes*/
	protected Node startNode;
	protected Node stopNode;

	// In constructor of block, add controller number(s) to this list
	protected ArrayList<Integer> controller = new ArrayList<Integer>();
	
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
	
	
	public int getID()
	{
		return blockID;
	}

	public void setId(int id)
	{
		blockID = id;
	}

	public boolean isOccupied(){
		return occupied;
	}
	
	public double getGrade(){
		return 0;
	}
	
	public double getLength(){
		return 0;
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
	
	public Block getNextBlock(int direction){
		/*direction uses same number convention as allowableDirection*/
		return null;
	}
	
	public Block getNextBlock(Node lastNode){
		/*it is easier to implement if we keep track of last node passed*/
		return null;
	}
	
	public double[] getAbsolutePosition(double distance){
		return new double[3]; //[x,y,z]
	}
  
  public Node getYardNode(){
    //return a yard node if it belongs to block else return null
        return startNode; //FOR THE LOVE OF GOD CHANGE THIS!!!
  }
  
  public Node getNextNode(Node nextNode){
    //
    return nextNode;
  }
  public void addController(int i)
  {
  	controller.add(i);
  }
  public ArrayList<Integer> getController(){
    // Should return a list of the controller(s) that a block is under
    return controller;
  }
  	
}

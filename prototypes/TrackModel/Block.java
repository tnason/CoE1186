
public abstract class Block {

	/*unique identification number*/
	protected int blockID;
	
	boolean occupied;
  /*unique identification number*/
	protected int blockID;
	
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

		//TODO - bounds check state
		occupied = state;
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
    
  }
  
  public Node getNextNode(Node ){
    //
  }
  	
}

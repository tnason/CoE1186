package TLTTC;

public abstract class Node implements constData
 {
	public Node(){
		nodeType = NodeType.Node;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Node x=");
		sb.append(xPos);
		sb.append(" y=");
		sb.append(yPos);
		sb.append(" z=");
		sb.append(zPos);
		
		
		return sb.toString();
	}
	
	/*Unique identifier*/
	protected int nodeID;
	
	/*Cartesian coordinates in meters*/
	protected double xPos;
	protected double yPos;
	protected double zPos;
	
	/*block that enters this node*/
	protected Block input;
	/*block that exits this node*/
	protected Block output;
	
	/*keep track of the type of node*/
	protected NodeType nodeType;
	public NodeType getNodeType(){
		return nodeType;
	}
	
	
	/*getters for coordinates*/
	public double getX(){
		return xPos;
	}
	
	public double getY(){
		return zPos;
	}
	
	public double getZ(){
		return zPos;
	}
	
	/*get the next block when traversing*/
	public Block getNextBlock(Block currentBlock) throws Exception{
		if(currentBlock == input)
		{
			return output;
		}
		else if(currentBlock == output)
		{
			return input;
		}
		else{
			throw new Exception("You are not crossing the node correctly...");
		}
	}
}



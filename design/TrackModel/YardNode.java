package TLTTC;

public class YardNode extends Node
{
	public YardNode(double x, double y, double z){
		nodeType = NodeType.Yard;
		xPos = x;
		yPos = y;
		zPos = z;
	}
	public String toString(){
		return "Yard" + super.toString();	
	}
	public Block getNextBlock(Block currentBlock) throws Exception{
		if(input != null)
		{
			return input;
		}
		else if(output != null)
		{
			return output;
		}
		else{
			throw new Exception("The yard node isnt connected to anything");
		}
	}
}

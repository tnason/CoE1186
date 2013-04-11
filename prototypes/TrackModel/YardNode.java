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
}
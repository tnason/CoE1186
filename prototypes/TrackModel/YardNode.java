package TLTTC;

public class YardNode extends Node implements constData
{
	public YardNode(double x, double y, double z){
		nodeType = NodeType.Yard;
		xPos = x;
		yPos = y;
		zPos = z;
	}
}
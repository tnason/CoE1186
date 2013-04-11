package TLTTC;

public class ConnectorNode extends Node {
	/*concrete implementation of Node*/
	public ConnectorNode(double x, double y, double z){
		nodeType = NodeType.Connector;
		xPos = x;
		yPos = y;
		zPos = z;
	}
	
	public String toString(){
		return "Connector" + super.toString();
	}
}

package TLTTC;
public class StationNode extends Node implements constData
{
	protected String name;
	
	public StationNode(double x, double y, double z){
		nodeType = NodeType.Station;
		xPos = x;
		yPos = y;
		zPos = z;
	}
	
	public String getStationName(){
		return name;
	}
}

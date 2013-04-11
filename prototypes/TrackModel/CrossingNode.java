package TLTTC;
public class CrossingNode extends Node{
	public CrossingNode(double x, double y, double z){
		nodeType = NodeType.Crossing;
		xPos = x;
		yPos = y;
		zPos = z;
	}	
	
	protected boolean opened;
	
	public boolean isOpen(){
		return opened;
	}
	
	public void setOpen(){
		opened = true;
	}
	
	public void setClosed(){
		opened = false;
	}
}

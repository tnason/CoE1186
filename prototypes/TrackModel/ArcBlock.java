package TLTTC;
public class ArcBlock extends Block {
	public ArcBlock(Node start, Node stop) throws Exception{
		super(start, stop);
		throw new Exception("ArcBlock Not Implemented!!!!!!!");
	}
	
	public String toString(){
		return "ArcBlock.toString not really implemented yet";
	}
	
	protected double radius;
	protected double centerX;
	protected double centerY;
	protected double centerZ;
	
	public double[] getAbsolutePosition(double distance){
		return new double[3]; //[x,y,z]
	}
}

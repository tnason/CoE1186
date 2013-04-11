package TLTTC;
public class ArcBlock extends Block {
	public ArcBlock(Node start, Node stop){
		super(start, stop);
	}
	
	
	protected double radius;
	protected double centerX;
	protected double centerY;
	protected double centerZ;
	
	public double[] getAbsolutePosition(double distance){
		return new double[3]; //[x,y,z]
	}
}

package TLTTC;
public class ArcBlock extends Block {
	protected double radius;
	protected double centerX;
	protected double centerY;
	protected double centerZ;

    public ArcBlock(Node start, Node stop, int id, int c) throws Exception
	{
		super(start, stop, c);
		super.setID(id);
		throw new Exception("ArcBlock Not Implemented!!!!!!!");
	}
	
	public String toString()
    {
		return "ArcBlock.toString not really implemented yet";
	}

	public double[] getAbsolutePosition(double distance){
		return new double[3]; //[x,y,z]
	}
}

package TLTTC;

public class LinearBlock extends Block{
	/*just inherits for now*/
	public LinearBlock(Node start, Node stop){
		super(start, stop);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Linear Block: start = ");
		sb.append(startNode.toString());
		sb.append(", stop = ");
		sb.append(stopNode.toString());
		sb.append(", occupied=");
		sb.append(occupied);
		sb.append(", maintenance=");
		sb.append(maintenance);
		
		
		sb.append(".");
		
		
		
		return sb.toString();
	}
	
	
	
}

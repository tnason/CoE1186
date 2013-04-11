package TLTTC;
public class SwitchNode extends Node{
	public SwitchNode(double x, double y, double z){
		nodeType = NodeType.Switch;
	}
	
	protected Block divergingOutput;
	protected int   direction;
	
	public void setSwitchState(int direction){
		//TODO - input bounds check
		this.direction = direction;
	}
	
	public Block getNextBlock(Block currentBlock) throws Exception{
		//TODO - check to make sure NULL isn't returned
		if(currentBlock == output || currentBlock == divergingOutput)
		{
			return input;
		}
		else if(currentBlock == input)
		{
			if(direction == 0)
			{
				return output;
			}
			else if(direction == 1)
			{
				return divergingOutput;
			}
		}
		else
		{
			throw new Exception("You are not crossing the node correctly...");
		}
		
		/*should never hit this point but eclipse complains otherwise*/
		return null;
	}
}

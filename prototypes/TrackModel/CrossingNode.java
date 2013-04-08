
public class CrossingNode extends Node{
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

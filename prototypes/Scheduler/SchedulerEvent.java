package TLTTC;

@SuppressWarnings("serial")  // SchedulerEvent has no definition of serialVersionUID

public class SchedulerEvent extends java.util.EventObject
{
	public SchedulerEvent(Object source)
	{
		super(source);
	}
}
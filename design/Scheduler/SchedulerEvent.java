//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

@SuppressWarnings("serial")  // SchedulerEvent has no definition of serialVersionUID

public class SchedulerEvent extends java.util.EventObject
{
	public SchedulerEvent(Object source)
	{
		super(source);
	}
}
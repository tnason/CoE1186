//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

public interface SchedulerListener
{
	public void timetableChanged(SchedulerEvent e);
	public void operatorScheduleChanged(SchedulerEvent e);
}
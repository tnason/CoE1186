package TLTTC;

public interface SchedulerListener
{
	public void timetableChanged(SchedulerEvent e);
	public void operatorScheduleChanged(SchedulerEvent e);
}
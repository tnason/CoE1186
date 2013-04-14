package TLTTC;
import java.text.SimpleDateFormat;
import javaa.util.Calendar;

public class SystemClock
{

	private Calendar epoch;
	private long startTime;
	private Calendar now;
	private SimpleDateFormat sdf;

	//start time in "simulation world" set here
	//for now, let's use May 1, 2013, 7 AM
	private final int START_YEAR = 2013;
	private final int START_MONTH = 4;
	private final int START_DAY = 1;
	private final int START_HOUR = 7;
	private final int START_MIN = 0;


	private final int SIMULATION_SPEEDUP = 5; //rate of simulation speedup

	public SystemClock()
	{
		epoch = Calendar.getInstance();
		now = Calendar.getInstance();

		//internal realtime epoch is now!
		startTime = System.getTimeInMillis();

		//use epoch for the artifical epoch
		epoch.set(	START_YEAR,
					START_MONTH,
					START_DAY,
					START_HOUR,
					START_MIN);
	}

	//For train motion, will return actual milliseconds elasped since
	//last call to motionStep()
	public long timeSince(long beginTime)
	{
		return now.getTimeInMillis() - beginTime;
	}

	//This method is for calling by the CTC/Scheduler
	//This will return a Date object indicating the current simulation time
	public Date getSimulationTime()
	{
		Calendar temp;
		long timeDiff;
		long artificalEpoch = epoch.getTimeInMillis();

		timeDiff = System.getTimeInMillis() - startTime;

		temp.setTimeInMillis(artificialEpoch + (timeDiff * SIMULATION_SPEEDUP));
		return temp.getTime();
	}

	public void printSimulationTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(getSimulationTime()));

}

package TLTTC;
import java.text.SimpleDateFormat;
import java.util.*; //Date,Calendar

public class SystemClock
{
	private Calendar epoch;
	private long startTime;

	//start time in "simulation world" set here
	//for now, let's use May 1, 2013, 7 AM
	private final int START_YEAR = 2013;
	private final int START_MONTH = 4;
	private final int START_DAY = 1;
	private final int START_HOUR = 7;
	private final int START_MIN = 0;


	public final int SIMULATION_SPEEDUP = 1; //rate of simulation speedup

	public SystemClock()
	{
		epoch = Calendar.getInstance();

		//internal realtime epoch is now!
		startTime = System.currentTimeMillis();

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
		return System.currentTimeMillis() - beginTime;
	}

	//This method is for calling by the CTC/Scheduler
	//This will return a Date object indicating the current simulation time
	public Date getSimulationTime()
	{
		Calendar temp = Calendar.getInstance();
		long timeDiff;
		long artificalEpoch = epoch.getTimeInMillis();

		timeDiff = System.currentTimeMillis() - startTime;

		temp.setTimeInMillis(artificalEpoch + (timeDiff * SIMULATION_SPEEDUP));
		return temp.getTime();
	}

	public void printSimulationTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(getSimulationTime()));
	}

	public void printSystemTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(sdf.format(new java.util.Date()));
	}
	
	//Really stupid driver program that shows features of clock
	public static void main(String args[])
	{
		SystemClock s = new SystemClock();
		long test = System.currentTimeMillis();
		int j = 0;

		s.printSimulationTime();
		s.printSystemTime();		

		System.out.println(s.timeSince(test));
		s.printSimulationTime();
		s.printSystemTime();		

		System.out.println(s.timeSince(test));
		s.printSimulationTime();
		s.printSystemTime();		

		System.out.println(s.timeSince(test));
	
		for(int m = 5000000; m > 1; m /= 2) 
		{	
			s.printSimulationTime();
			s.printSystemTime();		

			for(int i = 0; i < 500000; i++)
			{
				j++;
				double h = Math.pow(j,j);
			}
		}

		s.printSimulationTime();
		s.printSystemTime();		
		
		System.out.println(j);
		System.out.println(s.timeSince(test));
		s.printSimulationTime();
		s.printSystemTime();		

		System.out.println(s.timeSince(test));
		s.printSimulationTime();
		s.printSystemTime();		

		System.out.println(s.timeSince(test));
	}
}

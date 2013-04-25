//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

import java.sql.*;

public class TimesObject implements Comparable<TimesObject>
{
	public final String stationName;
	public final int trainNumber;
	public final long time;

	public TrainStatus status;

	public TimesObject(String stationName, int trainNumber, long time, TrainStatus status)
	{
		this.stationName = stationName;
		this.trainNumber = trainNumber;
		this.time = time;
		this.status = status;
	}

	public String toString()
	{
		return new String("Station Name: " + stationName +
					"\nTrain Number: " + trainNumber +
					"\nArrivalTime: " + new Time(time).toString() +
					"\nTrain Status: " + status);
	}

	public int compareTo(TimesObject to)
	{
		if(time < to.time)
		{
			return -1;
		}
		else if(time > to.time)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
		
}
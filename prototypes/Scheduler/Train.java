package TLTTC;

public class Train implements Comparable<Train>
{
	public final int trainNumber;

	private boolean locationValid;
	private boolean stoppingDistanceValid;
	private double stoppingDistance;
	private double location;
	private long time;

	public Train(int trainNumber, long time)
	{
		this.trainNumber = trainNumber;
		location = 0;
		stoppingDistance = 0;
		locationValid = false;
		stoppingDistanceValid = false;
		this.time = time;
	}

	public Train(int trainNumber, double location, double stoppingDistance, long time)
	{
		this.trainNumber = trainNumber;
		this.stoppingDistance = stoppingDistance;
		this.location = location;
		locationValid = true;
		stoppingDistanceValid = true;
		this.time = time;
	}

	public boolean isLocationValid()
	{
		return locationValid;
	}

	public void setLocationValid(boolean valid)
	{
		locationValid = valid;
	}

	public boolean isStoppingDistanceValid()
	{
		return stoppingDistanceValid;
	}

	public void setStoppingDistanceValid(boolean valid)
	{
		stoppingDistanceValid = valid;
	}

	public double getStoppingDistance()
	{
		return stoppingDistance;
	}

	public void setStoppingDistance(double stoppingDistance, long time)
	{
		this.stoppingDistance = stoppingDistance;
		stoppingDistanceValid = true;
		this.time = time;
	}

	public double getLocation()
	{
		return location;
	}

	public void setLocation(double location, long time)
	{
		this.location = location;
		locationValid = true;
		this.time = time;
	}

	public int compareTo(Train train)
	{
		if(location < train.getLocation())
		{
			return -1;
		}
		else if(location > train.getLocation())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}
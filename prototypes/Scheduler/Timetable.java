package TLTTC;

import java.util.*;

public class Timetable
{
	private ArrayList<TimesObject> timetable;


	public boolean add(TimesObject time)
	{
		boolean value = timetable.add(time);
		Collections.sort(timetable);
		return value;
	}

	public boolean add(String stationName, int trainNumber, long time, TrainStatus status)
	{
		return add(new TimesObject(stationName, trainNumber, time, status));
		
	}

	public Iterator<TimesObject> getIterator()
	{
		return timetable.iterator();
	}

	public TimesObject getTimesObject(int index)
	{
		return timetable.get(index);
	}

	public boolean remove(TimesObject time)
	{
		boolean value = timetable.remove(time);
		Collections.sort(timetable);
		return value;
	}

	public int remove(int trainNumber)
	{
		int count;
		TimesObject temp;

		count = 0;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(trainNumber == temp.trainNumber)
			{
				timetable.remove(i);
				count++;
				i--;
			}
		}

		Collections.sort(timetable);

		return count;
	}

	public int remove(String stationName)
	{
		int count;
		TimesObject temp;

		count = 0;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(stationName.compareTo(temp.stationName) == 0)
			{
				timetable.remove(i);
				count++;
				i--;
			}
		}

		Collections.sort(timetable);

		return count;
	}

	public boolean remove(int trainNumber, long time)
	{
		TimesObject temp;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(trainNumber == temp.trainNumber && time == temp.time)
			{
				timetable.remove(i);
	
				return true;
			}
		}

		Collections.sort(timetable);

		return false;
	}

	public boolean remove(String stationName, long time)
	{
		TimesObject temp;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(stationName.compareTo(temp.stationName) == 0 && time == temp.time)
			{
				timetable.remove(i);
	
				return true;
			}
		}

		Collections.sort(timetable);

		return false;
	}

	public int remove(String stationName, int trainNumber)
	{
		int count;
		TimesObject temp;

		count = 0;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(stationName.compareTo(temp.stationName) == 0 && trainNumber == temp.trainNumber)
			{
				timetable.remove(i);
				count++;
				i--;
			}
		}

		Collections.sort(timetable);

		return count;
	}

	public Timetable search(int trainNumber)
	{
		Timetable trainTimetable;
		TimesObject temp;

		trainTimetable = new Timetable();

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(trainNumber == temp.trainNumber)
			{
				trainTimetable.add(temp);
			}
		}

		if(trainTimetable.size() == 0)
		{
			return null;
		}
		else
		{
			return trainTimetable;
		}
	}

	public Timetable search(String stationName)
	{
		Timetable stationTimetable;
		TimesObject temp;

		stationTimetable = new Timetable();

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(stationName.compareTo(temp.stationName) == 0)
			{
				stationTimetable.add(temp);
			}
		}

		if(stationTimetable.size() == 0)
		{
			return null;
		}
		else
		{
			return stationTimetable;
		}
	}

	public TimesObject search(int trainNumber, long time)
	{
		TimesObject temp;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(trainNumber == temp.trainNumber && time == temp.time)
			{
				return temp;
			}
		}

		return null;
	}

	public TimesObject search(String stationName, long time)
	{
		TimesObject temp;

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(stationName.compareTo(temp.stationName) == 0 && time == temp.time)
			{
				return temp;
			}
		}

		return null;
	}

	public Timetable search(String stationName, int trainNumber)
	{
		Timetable stationTimetable;
		TimesObject temp;

		stationTimetable = new Timetable();

		for(int i = 0;i < timetable.size();i++)
		{
			temp = timetable.get(i);

			if(stationName.compareTo(temp.stationName) == 0 && trainNumber == temp.trainNumber)
			{
				stationTimetable.add(temp);
			}
		}

		if(stationTimetable.size() == 0)
		{
			return null;
		}
		else
		{
			return stationTimetable;
		}
	}

	public int size()
	{
		return timetable.size();
	}

	public Timetable()
	{
		timetable = new ArrayList<TimesObject>();
	}

	public String toString()
	{
		String s;

		s = new String();

		for(int i = 0;i < timetable.size();i++)
		{
			s = s.concat("\n\n" + timetable.get(i).toString());
		}

		return s;
	}

	public int update()
	{
		return 1;
	}
}
//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

import java.util.*;

public class OperatorSchedule
{
	private Hashtable<Integer, Operator> schedule;

	public boolean add(Operator operator)
	{
		schedule.put(operator.trainNumber, operator);

		return true;
	}

	public boolean add(String firstName, String lastName, int trainNumber, long shiftStart, OperatorStatus status)
	{
		schedule.put(trainNumber, new Operator(firstName, lastName, trainNumber, shiftStart, status));

		return true;
	}

	public Iterator<Operator> getIterator()
	{
		return schedule.values().iterator();
	}

	public Operator getOperator(int trainNumber)
	{
		return schedule.get(trainNumber);
	}

	public static void main(String[] args)
	{
		OperatorSchedule testSchedule;

		testSchedule = new OperatorSchedule();

		for(int i = 0;i < 9;i++)
		{
			testSchedule.add(Character.toString((char)(i + 33)),
						Character.toString((char)(126 - i)),
						i,
						System.currentTimeMillis(),
						OperatorStatus.values()[i % OperatorStatus.values().length]);
		}

		testSchedule.add(new Operator("Operator", "Constructor", 10, System.currentTimeMillis(), OperatorStatus.values()[10 % OperatorStatus.values().length]));
		System.out.println(testSchedule.toString());
	}

	public OperatorSchedule()
	{
		schedule = new Hashtable<Integer, Operator>();
	}

	public boolean remove(Operator operator)
	{
		if(schedule.remove(operator.trainNumber) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean remove(int trainNumber)
	{
		if(schedule.remove(trainNumber) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Operator search(int trainNumber)
	{
		return schedule.get(trainNumber);
	}

	public int size()
	{
		return schedule.size();
	}

	public String toString()
	{
		String s;
		Iterator<Operator> iterator;

		iterator = schedule.values().iterator();
		
		s = new String();

		while(iterator.hasNext())
		{
			s = s.concat("\n\n" + iterator.next().toString());
		}

		return(s);
	}

	public boolean update()
	{
		return true;
	}
}

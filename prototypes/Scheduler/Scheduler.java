package TLTTC;

import java.util.*;
import java.util.concurrent.*;

public class Scheduler extends Worker implements constData
{
	public static int NEXT_TRAIN_NUMBER = 0;

	private List listeners = new ArrayList();
	private LinkedBlockingQueue<Message> messages;
	private Module name;
	private OperatorSchedule schedule;
	private Timetable timetable;
	private MyLinkedList<Train> trains;

	public synchronized void addSchedulerListener(SchedulerListener listener)
	{
		listeners.add(listener);
	}

	private synchronized void operatorScheduleChanged()
	{
		Iterator i;
		SchedulerEvent e;

		e = new SchedulerEvent(this);
		i = listeners.iterator();

		while(i.hasNext())
		{
			((SchedulerListener) i.next()).operatorScheduleChanged(e);
		}
	}

	private synchronized void timetableChanged()
	{
		Iterator i;
		SchedulerEvent e;

		e = new SchedulerEvent(this);
		i = listeners.iterator();

		while(i.hasNext())
		{
			((SchedulerListener) i.next()).timetableChanged(e);
		}
	}

	public OperatorSchedule getOperatorSchedule()
	{
		return schedule;
	}

	public Timetable getTimetable()
	{
		return timetable;
	}

	public static void main(String[] args)
	{
	}

	public synchronized void removeSchedulerListener(SchedulerListener listener)
	{
		listeners.remove(listener);
	}

	public void run()
	{

		while(true)
		{
			if(messages.peek() != null)
			{
				Message message = messages.poll();

				if(name == message.getDest())
				{
					System.out.println("\nRECEIVED MESSAGE: source->" + message.getSource() + " : dest->" + message.getDest() + "\n");

					switch((int)message.getData().get("id"))
					{
						case 93:
							receivedGPSLocation(message);
							break;
						case 94:
							receivedTrainDispatch(message);
							break;
						case 95:
							receivedTrainArrival(message);
							break;	
						case 99:
							receivedYardArrival(message);
							break;	
					}
				}
				else
				{
					System.out.println("PASSING MESSAGE: step->" + name + " source->" + message.getSource() + " dest->" + message.getDest());
					message.updateSender(name);
					Environment.passMessage(message);
				}
			}

			Iterator<TimesObject> i;

			i = timetable.getIterator();

			while(i.hasNext())
			{
				TimesObject time = i.next();

				if(time.status != TrainStatus.ARRIVED && time.time < System.currentTimeMillis())
				{
					time.status = TrainStatus.LATE;
				}
			}
		}

	}

	private void receivedGPSLocation(Message message)
	{
	}

	private void receivedTrainDispatch(Message message)
	{
		int trainID;

		trainID = (int)message.getData().get("train_ID");
		trains.add(new Train(trainID, System.currentTimeMillis()));

		if(schedule.search(trainID) == null)
		{
			schedule.add("Train", "Operator", trainID, System.currentTimeMillis(), OperatorStatus.SHIFTNOTSTARTED);
			operatorScheduleChanged();
			updateTimetable();
		}
	}

	private void receivedTrainArrival(Message message)
	{
	}

	private void receivedYardArrival(Message message)
	{
	}

	private void requestTrainGPS()
	{
		Iterator<Operator> i;
		Message message;

		i = schedule.getIterator();

		while(i.hasNext())
		{
			message = new Message(Module.scheduler, Module.scheduler, Module.satellite);
			message.addData("trainNumber", i.next().trainNumber);
			send(message);
		}
	}

	public Scheduler()
	{
		new SchedulerViewModel(this);
		timetable = new Timetable();
		schedule = new OperatorSchedule();
		trains = new MyLinkedList<Train>();
		messages = new LinkedBlockingQueue<Message>();
		this.name = Module.scheduler;
/*
		timetable.add("Pittsburgh", 35, System.currentTimeMillis(), TrainStatus.ONTIME);
		timetable.add("Philadelphia", 17, System.currentTimeMillis(), TrainStatus.EARLY);
		timetable.add("Harrisburg", 2, System.currentTimeMillis(), TrainStatus.LATE);
*/
	}

	public void send(Message message)
	{
	    	System.out.println("SENDING MESSAGE: start->" + message.getSource() + " : dest->" + message.getDest() + "\n");
		Environment.passMessage(message);
	}

	public void setMsg(Message message)
	{
		messages.add(message);
	}

	public String toString()
	{
		String s;

		s = "Timetable";

		s = s.concat(timetable.toString());
		s = s.concat("Operator Schedule");
		s = s.concat(schedule.toString());

		return s;		
	}

	public boolean updateOperatorSchedule()
	{
		int numTrains;

		numTrains = calculateOptimalTrains();

		if(trains.size() < numTrains)
		{
			schedule.add("Train", "Operator", Scheduler.NEXT_TRAIN_NUMBER++, System.currentTimeMillis(), OperatorStatus.SHIFTNOTSTARTED);
			operatorScheduleChanged();

			return true;
		}

		return false;
	}

	public boolean updateTimetable()
	{
		timetableChanged();

		return true;
	}

	private int calculateOptimalTrains()
	{
		return 1;
	}

	private void calculateRoutes(int time)
	{
		while(time < 36000000)
		{
			
			time = time +1000;
		}
	}
}
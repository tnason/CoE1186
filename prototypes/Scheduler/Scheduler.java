package TLTTC;

import java.util.*;
import java.util.concurrent.*;

public class Scheduler extends Worker implements constData
{
	public static int NEXT_TRAIN_NUMBER = 0;

<<<<<<< HEAD
	private List<SchedulerListener> listeners;
=======
	private List<SchedulerListener> listeners = new ArrayList<SchedulerListener>();
>>>>>>> 3e34b24b82a3f0e958c0a0b0e367b4a715e532c4
	private LinkedBlockingQueue<Message> messages;
	private Module name;
	private OperatorSchedule schedule;
	private Timetable timetable;
	private MyLinkedList<Train> trains;

	public static void main(String[] args)
	{

	}

	/*
		Constructor
	*/

	public Scheduler()
	{
		timetable = new Timetable();
		schedule = new OperatorSchedule();
		trains = new MyLinkedList<Train>();
		listeners = new ArrayList<SchedulerListener>();
		messages = new LinkedBlockingQueue<Message>();
		this.name = Module.scheduler;

		new SchedulerViewModel(this);
	}

	/*
		Methods
	*/

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
			while(schedule.size() < numTrains)
			{
				schedule.add("Train", "Operator", Scheduler.NEXT_TRAIN_NUMBER++, System.currentTimeMillis(), OperatorStatus.SHIFTNOTSTARTED);
			}

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

	private Train findTrain(int trainNumber)
	{
		Train t;
		Train value;
		Train train;

		value = null;

		if(trains.size() == 0)
		{
			return value;
		}

		t = trains.selected();

		do
		{
			train = trains.next();

			if(train.trainNumber == trainNumber)
			{
				value = train;
			}
		}
		while(!t.equals(trains.selected()));

		return value;
	}

	public OperatorSchedule getOperatorSchedule()
	{
		return schedule;
	}

	public Timetable getTimetable()
	{
		return timetable;
	}

	/*
		Thread loop
	*/

	public void run()
	{

		while(true)
		{
			if(messages.peek() != null)
			{
				Message message = messages.poll();

				if(name == message.getDest())
				{
					System.out.println("RECEIVED MESSAGE ~ (source : " + message.getSource() + "), (dest : " + message.getDest() + ")\n");

					switch(message.getType())
					{
						case CTC_Sch_Generate_Schedule:
							receivedTimetableUpdateRequest(message);
							break;
						/*case:
							receivedGPSLocation(message);
							break;*/
						/*case 95:
							receivedTrainArrival(message);
							break;	*/
						case TnMd_Sch_Notify_Yard:
							if((boolean)message.getData().get("entry"))
							{
								receivedTrainReturn(message);
							}
							else
							{
								receivedTrainDispatch(message);
							}
							break;	
					}
				}
				else
				{	System.out.println("in: " + message.getSender());
					System.out.println("PASSING MSG ~ (source : " + message.getSource() + "), (step : " + name + "), (dest : "+message.getDest()+")");
                    message.updateSender(Module.scheduler);
					Environment.passMessage(message);System.out.println("out: " + message.getSender());
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

	/*
		Message Handlers
	*/

	public void setMsg(Message message)
	{
		messages.add(message);
	}

	private void receivedGPSLocation(Message message)
	{
	}

	private void receivedTrainDispatch(Message message)
	{
		int trainID;
		Operator operator;

		trainID = (int)message.getData().get("trainID");
		trains.add(new Train(trainID, System.currentTimeMillis()));
		sendTrainUpdate();

		operator = schedule.search(trainID);

		if(operator == null)
		{
			schedule.add("Train", "Operator", trainID, System.currentTimeMillis(), OperatorStatus.SHIFTFIRSTHALF);
			operatorScheduleChanged();
			//updateTimetable();
		}
		else
		{
			if(operator.status == OperatorStatus.SHIFTNOTSTARTED)
			{
				operator.status = OperatorStatus.SHIFTFIRSTHALF;
			}
			else if(operator.status == OperatorStatus.ONBREAK)
			{
				operator.status = OperatorStatus.SHIFTSECONDHALF;
			}
		}
	}

	private void receivedTrainArrival(Message message)
	{
	}

	private void receivedTimetableUpdateRequest(Message message)
	{
		updateTimetable();
		sendTimetable();
	}

	private void receivedTrainReturn(Message message)
	{
		int trainID;
		Operator operator;

		trainID = (int)message.getData().get("trainID");
		operator = schedule.search(trainID);

		if(operator.status == OperatorStatus.SHIFTFIRSTHALF)
		{
			operator.status = OperatorStatus.ONBREAK;
		}
		else if(operator.status == OperatorStatus.SHIFTSECONDHALF)
		{
			operator.status = OperatorStatus.SHIFTENDED;
		}

		trains.remove(findTrain(trainID));
		sendTrainUpdate();
	}

	/*
		Message Senders
	*/

	public void send(Message message)
	{
   		System.out.println("SENDING MSG ~ (start : "+message.getSource() + "), (dest : "+message.getDest()+"), (type : " + message.getType()+ ")");
        message.updateSender(name);
        Environment.passMessage(message);
	}

	private void requestTrainGPS()
	{
		Iterator<Operator> i;
		Message message;
/*
		i = schedule.getIterator();

		while(i.hasNext())
		{
			message = new Message(Module.scheduler, Module.scheduler, Module.satellite, msg.placeHolder);
			message.addData("trainNumber", i.next().trainNumber);
			send(message);
		}
*/
	}

	private void sendTrainUpdate()
	{
		Message message;

		message = new Message(name, name, Module.MBO, msg.Sch_MBO_Notify_Train_Added_Removed);
		message.addData("id", 96);
		message.addData("trainList", trains.copy());
		send(message);
	}

	private void sendTimetable()
	{
		Message message;

		message = new Message(name, name, Module.CTC, msg.Sch_CTC_Send_Schedule);
		message.addData("schedule", timetable);
		send(message);
	}

	/*
		Scheduler UI Communication
	*/

	public synchronized void addSchedulerListener(SchedulerListener listener)
	{
		listeners.add(listener);
	}

	public synchronized void removeSchedulerListener(SchedulerListener listener)
	{
		listeners.remove(listener);
	}

	private synchronized void operatorScheduleChanged()
	{
		Iterator<SchedulerListener> i;
		SchedulerEvent e;

		e = new SchedulerEvent(this);
		i = listeners.iterator();

		while(i.hasNext())
		{
			i.next().operatorScheduleChanged(e);
		}
	}

	private synchronized void timetableChanged()
	{
<<<<<<< HEAD
		Message m = new Message(Module.MBO, Module.MBO, Module.scheduler, msg.TnMd_Sch_Notify_Yard);
		m.addData("trainID", 1);
		m.addData("entry", true);
		send(m);
/*
=======
>>>>>>> 3e34b24b82a3f0e958c0a0b0e367b4a715e532c4
		Iterator<SchedulerListener> i;
		SchedulerEvent e;

		e = new SchedulerEvent(this);
		i = listeners.iterator();

		while(i.hasNext())
		{
<<<<<<< HEAD
			i.next().timetableChanged(e);
=======
			 i.next().timetableChanged(e);
>>>>>>> 3e34b24b82a3f0e958c0a0b0e367b4a715e532c4
		}
*/
	}
}
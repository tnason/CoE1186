package TLTTC;
import java.util.*;
import java.util.concurrent.*;

//This class will hold all of the trains
//It will also receive messages and route them to the trains
//It will also also abstract out train construction 
public class TrainContainer implements Runnable, constData 
{
	private Module name;
	private LinkedBlockingQueue<Message> msgs;
  
	private HashTable<int, TrainModel> trains;
  
	private Timer motionTimer;

	private int timerTrigger = 10; //real-time value (in ms) for triggering motionStep() calls
	private double trainTimestep = .05; //timestep (in s) for train motion integration (simulation time!)
	//For now, simulationSpeedup = (trainTimestep * 1000) / timerTrigger

	//for message sending/receiving
	int trainID;
	TrainModel tm;
	

	public TrainContainer()
	{ 
  		this.name = Module.trainModel;
		msgs  = new LinkedBlockingQueue<Message>();
		trains = new HashTable<int, TrainModel>();
		motionTimer = new Timer();
  		motionTimer.scheduleAtFixedRate(new motionTask(), 0, timerTrigger); //update all the train motion every 10 ms
	}
  
	//Driver for timed motion!
	private class motionTask extends TimerTask 
	{
 		public void run()
		{
  			Enumeration<int> enumKey = trains.keys();
  			while(enumKey.hasMoreElements())
  			{
  				trains.get(enumKey.nextElement()).motionStep(); //move the trains!
	  		}
  		}
	}

	public void newTrain(int TrainID, Block start)
	{
		trains.add(new TrainModel(TrainID, start, trainTimestep));
		//send a message to TrainControllerModule to make a new, linked TrainController

	}

	public void run()
	{
		//Thread t = new Thread();

		while(true)
		{
		 	if(msgs.peek() != null)
			{
				Message mine = msgs.poll();

				if(name == mine.getDest())
				{
					
					System.out.println("\nRECEIVED MSG: source->"+mine.getSource() + " : dest->"+mine.getDest()+"\n");

					if(mine.getData() != null)
					{
						trainID = (int)(m.getData.get("train_ID"));
						tm = trains.get(trainID);
						System.out.println(mine.getData().get("check"));
						
						//handling incoming messages
						switch (m.getType())
						{
							//case MESSAGE_NAME:
							//stuff
						}
						
					}
					else
					{

						Message verify = new Message(name, name, mine.getSource());
						verify.addData("check", (Object) ("VERIFY LOOP!"));

						send(verify);
					}
				}
				else
				{
					System.out.println("PASSING MSG: step->"+name + " source->"+mine.getSource()+ " dest->"+mine.getDest());
					mine.updateSender(name);
					Environment.passMessage(mine);
				}
			}
		}
	}

	public void setMsg(Message m)
	{
		msgs.add(m);
	}

	public void send()
	{
		Message outgoing = new Message(name, name, Module.trainController);
		System.out.println("SENDING MSG: start->"+outgoing.getSource() + " : dest->"+outgoing.getDest()+"\n");
		Environment.passMessage(outgoing);
	}

	public void send(Message m)
	{
   		System.out.println("SENDING MSG: start->"+m.getSource() + " : dest->"+m.getDest()+"\n");
		Environment.passMessage(m);
	}
  
  //methods to send messages go here
}

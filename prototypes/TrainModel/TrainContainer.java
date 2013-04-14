package TLTTC;
import java.util.*;
import java.util.concurrent.*;

//This class will hold all of the trains
//It will also receive messages and route them to the trains
//It will also also abstract out train construction 
public class TrainContainer extends Worker implements Runnable, constData 
{
	private Module name;
	private LinkedBlockingQueue<Message> msgs;
  
	private Hashtable<Integer, TrainModel> trains;
  
	private Timer motionTimer;

	private SystemClock clock;

	//This value is just a suggestion for integration's sake!
	private final double TIME_STEP = .1; //timestep (in s) for train motion integration (simulation time)

	private long timerTrigger; //real-time value (in ms) for triggering motionStep() calls

	private int motionStepCount = 0;

	private TrainControllerModule trc;

	//For now, simulationSpeedup = (trainTimestep * 1000) / timerTrigger

	//for message sending/receiving
	int trainID;
	TrainModel tm;
	Block bl;
	Node n;
	Message outgoingMessage;
	

	public TrainContainer()
	{ 
  		this.name = Module.trainModel;
		msgs  = new LinkedBlockingQueue<Message>();
		trains = new Hashtable<Integer, TrainModel>();
		motionTimer = new Timer();
	}
  
	//Driver for timed motion!
	private class motionTask extends TimerTask 
	{
 		public void run()
		{
  			Enumeration<Integer> enumKey = trains.keys();
  			while(enumKey.hasMoreElements())
  			{
				trainID = enumKey.nextElement();
				tm = trains.get(trainID);
				if(tm.whiteFlag)
				{
					//kill it!
					trains.remove(trainID);
				}
				else
				{
					tm.motionStep(clock); //move the trains!
				}
	  		}
			motionStepCount++;
  		}
	}

	public TrainModel newTrain(int TrainID, Block start, double step)
	{
		TrainModel n = new TrainModel(TrainID, start, step);
		trains.put(TrainID, n);
		return n;
	}

	public void init(TrainControllerModule other, SystemClock sys)
	{
		trc = other;
		clock = sys;
		timerTrigger = (TIME_STEP * 1000.0)/(double)clock.SIMULATION_SPEEDUP;
  		motionTimer.scheduleAtFixedRate(new motionTask(), 0, timerTrigger); //update all the train motion every X ms
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
					
					System.out.println("RECEIVED MESSAGE ~ (source : " + mine.getSource() + "), (dest : " + mine.getDest() + ")\n");

					if(mine.getData() != null)
					{
						//System.out.println(mine.getData().get("check")); ????
						
						//handling incoming messages
						switch (mine.getType())
						{
							case CTC_TnMd_Request_Train_Creation:
								n = (YardNode)mine.getData().get("yardNode");
								bl = (Block)mine.getData().get("yardBlock");
								if(bl.isOccupied())
								{
									//fail silently
								} 
								else
								{
									System.out.println("	!!!!!!!!!!!!!!!!!NEW TRAIN!!!!!!!!");

									tm = newTrain((int)mine.getData().get("trainID"), bl, TIME_STEP);
									
									tm.setYardNode(n);
									//send associated messages!!!
									
									// Send will pass message to environment. Notify to console of msg send.
									//send TnMd_CTC_Confirm_Train_Creation
									outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_CTC_Confirm_Train_Creation, new String[] {"trainID"}, new Object[] {mine.getData().get("trainID")});
									send(outgoingMessage); 

									//send TnMd_TnCt_Request_Train_Controller_Creation
									//deprecated

									//send TnMd_Sch_Notify_Yard
									outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.scheduler, msg.TnMd_Sch_Notify_Yard, new String[] {"entry","trainID","blockID"}, new Object[] {(Object)false, mine.getData().get("trainID"), (Object)(bl.getID())});
									send(outgoingMessage);
								}
								break;
							case TnCt_TnMd_Send_Power:
								//deprecated
								break;
							case TnCt_TnMd_Request_Train_Velocity:
								//deprecated
								break;
							default:
								//stuff
						}
						
					}
					else
					{
						//other stuff!
					}
				}
				else
				{
					System.out.println("PASSING MSG ~ (source : " + mine.getSource() + "), (step : " + name + "), (dest : " + mine.getDest()+"), (type : " + mine.getType()+")");
          			mine.updateSender(name);
					send(mine);
				}
			}
		}
	}

	//methods called by TrainControllerModule!
	TrainModel getTrain(int trainID)
	{
		return trains.get(trainID);
	}


	public void setMsg(Message m)
	{
		msgs.add(m);
	}

	public void send()
	{
		//Message outgoing = new Message(name, name, Module.trainController);
		//System.out.println("SENDING MSG: start->"+outgoing.getSource() + " : dest->"+outgoing.getDest()+"\n");
		//send(outgoing);
	}

	public void send(Message m)
	{
   		System.out.println("SENDING MSG ~ (start : "+m.getSource() + "), (dest : "+m.getDest()+"), (type : " + m.getType()+ ")");
        m.updateSender(name);
        Environment.passMessage(m);
	}

  
  //methods to send messages go here
}

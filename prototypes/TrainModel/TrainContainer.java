import java.util.*;
import java.util.concurrent.*;

//This class will hold all of the trains
//It will also receive messages and route them to the trains
//It will also also abstract out train construction 
public class TrainContainer implements Runnable, constData 
{
	private Module name;
  private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>();
  
  private ArrayList<TrainModel> trains = new ArrayList<TrainModel>();
  
	private Timer motionTimer;

  public TrainContainer()
  { 
  	this.name = Module.trainModel;
  	motionTimer = new Timer();
  	motionTimer.scheduleAtFixedRate(new motionTask(), 0, 10); //update all the train motion every 10 ms
  }
  
  //Driver for timed motion!
  private class motionTask extends TimerTask 
  {
  	public void run()
  	{
  		Iterator<TrainModel> it = trains.iterator();
  		while(it.hasNext())
  		{
  			it.next().motionStep(); //move the trains!
  		}
  	}
  }

	public void newTrain(int TrainID, Block start)
	{
		trains.add(new TrainModel(TrainID, start));
		//send a message to TrainControllerModule to make a new, linked TrainController

	}

  public void run()
	{
		Thread t = new Thread();

		while(true)
		{
		 	if(msgs.peek() != null)
			{
				Message mine = msgs.poll();

				if(name == mine.getDest())
				{
					System.out.println("\nRECEIVED MSG: source->"+mine.getSource() + " : dest->"+mine.getDest()+"\n");

					if(mine.getData() != null && mine.getData().get("check") != null )
					{
						System.out.println(mine.getData().get("check"));
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
}

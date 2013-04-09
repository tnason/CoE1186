package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class Worker implements Runnable, constData
{
    private Module name;
    private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>();

    public Worker(Module name){ this.name = name;}

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

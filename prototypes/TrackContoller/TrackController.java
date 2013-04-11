package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class TrackController extends Worker implements constData, Runnable
{
	Environment env;
	private Module name = Module.trackController;
    private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>();

    public TrackController()
    {

    }

	public void run()
	{
		while(true)
		{
			if(msgs.peek() != null)
     		{
        		Message m = msgs.poll();
        
        		if(name == m.getDest())
			    {
			    	System.out.println("RECEIVED MESSAGE ~ (source : " + m.getSource() + "), (dest : " + m.getDest() + ")\n");
			    }
			    else
       			{
         			System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : " + m.getDest()+")");
                    m.updateSender(name);
          			Environment.passMessage(m);
          		}
          	}
        }
	}

	public void init()
	{


	}

	public void setMsg(Message m)
    {
		msgs.add(m);
    }

    public void send()
    {
    	Message m = new Message(name,name,name,msg.verify);
    	//Environment.passMessage(m);
    	send(m);
    }

    public void send(Message m)
    {
        System.out.println("SENDING MSG ~ (start : "+m.getSource() + "), (dest : "+m.getDest()+"), (type : " + m.getType()+ ")");
        Environment.passMessage(m);
    }

	public static void main(String [] args)
	{
        
	}

}

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

		}
	}

	public void setMsg(Message m)
    {
		msgs.add(m);
    }

    public void send()
    {
    	Message m = new Message(name,name,name,msg.verify);
    	Environment.passMessage(m);
    }

	public static void main(String [] args)
	{
        
	}

}

package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class TrackController implements constData, Runnable
{

	private Module name = Module.trackController;
    private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>();

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

    }

	public static void main(String [] args)
	{
        
	}

}
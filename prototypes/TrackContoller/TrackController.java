package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class TrackController extends Worker implements constData, Runnable
{
	private Module name = Module.trackController;
  private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>();

  private Hashtable<Integer, ArrayList<Block>> blockUnderController = new Hashtable<Integer, ArrayList<Block>>();

  TrackControllerView gui;

  public TrackController()
  {
      gui = new TrackControllerView();
      gui.startGUI();
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
            if(m.getType() == constData.msg.MBO_TnCt_Send_Moving_Block_Authority)
            {
              
              
            }

         			System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : " + m.getDest()+"), (type : " + m.getType()+")");
              m.updateSender(name);
          		Environment.passMessage(m);
        	}
        }
      }
	}

  public void init(Worker tModel)
	{
    Hashtable<Integer, Block> allBlocks = new Hashtable<Integer, Block>(((TrackModel)tModel).getBlocks());

    for(Block b : allBlocks.values())
    {
      ArrayList<Integer> control = b.getController();

      for(int c : control)
      {
        if(!blockUnderController.containsKey(c))
        {
            blockUnderController.put(c, new ArrayList<Block>());
        }
        
        blockUnderController.get(c).add(b);
      }
    }

    for(int i : blockUnderController.keySet())
    {
        for(Block blk : blockUnderController.get(i))
        {
          System.out.println("Controller " + i + " block " + blk.getID() );
       }
      System.out.println();
    }
	}

	public void setMsg(Message m)
  {
		msgs.add(m);
  }

  public void send()
  {
  	Message m = new Message(name,name,name,msg.verify);
  	send(m);
  }

  public void send(Message m)
  {
      System.out.println("SENDING MSG ~ (start : "+m.getSource() + "), (dest : "+m.getDest()+"), (type : " + m.getType()+ ")");
      m.updateSender(name);
      Environment.passMessage(m);
  }
}

package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class TrackController extends Worker implements constData, Runnable
{
	private Module name = Module.trackController;
  private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>();

  private Hashtable<Integer, Hashtable<Integer, Block>> blockUnderController =
                                       new Hashtable<Integer, Hashtable<Integer, Block>>();

  private Object myPLC = null;
  private TrackControllerView gui;


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
          //if(m.getType() != msg.MBO_TnCt_Send_Moving_Block_Authority)
            //System.out.println("THROUGH: " +m.getType()+" "+ m.getData().toString());

          if(name == m.getDest())
			    {
			    	//System.out.println("RECEIVED MESSAGE ~ (source : " + m.getSource() + "), (dest : " + m.getDest() + ")\n");
          }
			    else
          {

            if(m.getType() == msg.MBO_TnCt_Send_Moving_Block_Authority)
            {
              // Check if safe MBA and add fixed block.
            }
            else if(m.getType() == msg.TnMd_CTC_Send_Block_Occupied)
            {
              gui.refresh();
            }
            
            send(m);
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
            blockUnderController.put(c, new Hashtable<Integer, Block>());
        }
        
        blockUnderController.get(c).put(b.getID(), b);
      }
    }

    for(int i : blockUnderController.keySet())
    {
        for(Block blk : blockUnderController.get(i).values())
        {
          System.out.println("Controller " + i + " block " + blk.getID() + " " + blk.getStopNode().getNodeType());
       }
      System.out.println();
    }

    gui = new TrackControllerView(blockUnderController);
    gui.setVisible(true);

    //while(gui.getPLC() == null);

    //myPLC = gui.getPLC();
  }
	public void setMsg(Message m)
  {
		msgs.add(m);
  }

  public void send(Message m)
  {
      m.updateSender(name);
      Environment.passMessage(m);
  }
}

/*
* Author(s): Cameron Dashti
* Updated: 25 – 4 – 2013
* Purpose: This class is the track controller for the entire transit system.
*          It holds all of the TCs and deals with crossings, switches and fixed block.
*/


package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class TrackController extends Worker implements constData, Runnable
{
	private Module name = Module.trackController;  // Modules name
  private LinkedBlockingQueue<Message> msgs = new LinkedBlockingQueue<Message>(); // my message inbox
  
  private Hashtable<Integer, Block> allBlocks;   // blocks in system
  private Hashtable<Integer, Block> oldBlocks;
  // Blocks divided into contorllers
  private Hashtable<Integer, Hashtable<Integer, Block>> blockUnderController =
                                       new Hashtable<Integer, Hashtable<Integer, Block>>(); 

  private Object myPLC        = null;  // references to PLC class and object
  private Class<?> myPlcClass = null;

  private TrackControllerView gui;     // reference to GUI
  
  public TrackController()
  { 
    // Deafult constructor. Doesnt need to do any work. All done in init
  }

	public void run()     
	{
		while(true)     // loop forever.
		{
			if(msgs.peek() != null)        // if there is a new message in hte inbox.
     		{
        	Message m = msgs.poll();   // get next available message
          if(m.getType() != msg.MBO_TnCt_Send_Moving_Block_Authority && m.getType() != msg.MBO_TnMd_Request_Velocity)
            System.out.println("THROUGH: " +m.getType()+" "+ m.getData().toString());

          if(name == m.getDest())
			    {
			         // If message is destined for track controller.
          }
			    else
          {
            if(m.getType() == msg.MBO_TnCt_Send_Moving_Block_Authority)
            {
              // Check if safe MBA and add fixed block.
              m.addData("authorityFB", (Object) 1400);
            }
            else if(m.getType() == msg.TnMd_CTC_Send_Block_Occupied)
            {
              m.addData("isStation", (Object) (allBlocks.get(m.getData().get("blockId")).isStation()));
              try
              {
                // get block being traversed and let PLC handle crossings
                Block currentBlock = allBlocks.get(m.getData().get("blockId"));
                myPlcClass.getMethod("handleCrossing", Block.class).invoke(myPLC, currentBlock);

                myPlcClass.getMethod("checkTrack", Hashtable.class, Hashtable.class,  Block.class).invoke(oldBlocks, allBlocks, currentBlock);
                
               double fixedAuth = (double) myPlcClass.getMethod("checkAuthority",Hashtable.class,  Block.class).invoke(allBlocks, currentBlock);

               m.addData("authorityFB", (Object) fixedAuth);
              } 
              catch (Exception e)
              {

              }

              gui.refresh();  // update the gui after state changes
            }
            else if(m.getType() == msg.TnMd_TcCt_Update_Block_Occupancy)
            {
                 gui.refresh();  // update the gui after state changes
            }
            
            send(m);    // pass message back to the environment
        	}
        }
      }
	}

  public void init(Worker tModel)
	{
    // get all of the blocks from the track model
    allBlocks = new Hashtable<Integer, Block>(((TrackModel)tModel).getBlocks());
    oldBlocks = allBlocks;

    // loop over all of the blocks
    for(Block b : allBlocks.values())
    {
      // get the list of controllers the block belongs to
      ArrayList<Integer> control = b.getController();

      for(int c : control) // loop over all the controllers
      {
        if(!blockUnderController.containsKey(c)) 
        {
            blockUnderController.put(c, new Hashtable<Integer, Block>());
        }
        
        blockUnderController.get(c).put(b.getID(), b); // add the block to the controller
      }
    }

    gui = new TrackControllerView(blockUnderController); // create new gui 
    gui.setVisible(true);                                // set gui visible

    while(!gui.PLCLoaded())     // While the track controller doesn't have a PLC, wait
    {
        System.out.print("");   
    }

    myPLC = gui.getPLC();            // once PLC is loaded, get the class and instance of the PLC
    myPlcClass = gui.getPlcClass(); 
  }

	public void setMsg(Message m)      
  {
		msgs.add(m);       // Allows the environment to send module messages
  }

  public void send(Message m)
  {
      m.updateSender(name);           // Update the sender as my module
      Environment.passMessage(m);     // Send message back to environment
  }
}

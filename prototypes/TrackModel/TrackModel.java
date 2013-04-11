package TLTTC;

import java.util.HashMap;


public class TrackModel extends Worker
{
    private HashMap<int, Block> blocks;
    private HashMap<int, Node> nodes;
    private Module name;
    
    public TrackModel()
    {
      		this.name = Module.trackModel;
		      msgs  = new LinkedBlockingQueue<Message>();
    
    
    
    
    
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
  					
  					if(mine.getData() != null)
  					{
  						//handling incoming messages
  						switch (mine.getType())
  						{
  							//case MESSAGE_NAME:
  							//stuff                                                      ---parse from enum?
  					    case msg.foooooook;
            
            
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
  					System.out.println("TrackModel PASSING MSG: step->"+name + " source->"+mine.getSource()+ " dest->"+mine.getDest());
  					mine.updateSender(name);
  					Environment.passMessage(mine);
  				}
  			}  
  	 	}
  	}    
    public void initTrack()
    {
    
    
    }
    

    //handlers-----------------------------------

    //TnCt_TcMd_Request_Track_Speed_Limit
    public void getBlockSpeedLimit(int blockID)
    {
      //gets a block id 
    }


    //pass throughs------------------------------

    //CTC_TnMd_Request_Train_Creation
    public void relayTrainCreationMsg()
    {
    
    }

}
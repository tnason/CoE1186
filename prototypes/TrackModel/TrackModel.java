package TLTTC;

import java.util.HashMap;


public class TrackModel extends Worker implements Runnable, constData
{
    private HashMap<Integer, Block> blocks;
    private HashMap<Integer, Node> nodes;
    private java.util.concurrent.LinkedBlockingQueue<Message> msgs;
    private Module name = Module.trackModel;
    
    public TrackModel () {
        msgs = new java.util.concurrent.LinkedBlockingQueue<Message>();
    }

    public void run() {
        while (true) 
        {
            if(msgs.peek() != null)
            {
                Message m = msgs.poll();
    
                if(name == m.getDest())
                {

                }
                else
                {
                    System.out.println("PASSING MSG: step->"+name + " source->"+m.getSource()+ " dest->"+m.getDest());
                    m.updateSender(name);
                    Environment.passMessage(m);
                }
            }
        }
    }
    
   
    
    public void initTrack()
    {
    
    
    }
    
    public void setMsg(Message m) 
    {
        msgs.add(m);
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
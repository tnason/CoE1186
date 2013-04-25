package TLTTC;
import java.util.*;
import java.util.concurrent.*;

public class TrainControllerModule extends Worker implements Runnable, constData
{
  private Module name = Module.trainController; // Name of Module
  private Hashtable<Integer, TrainController> controllers; // Hashtable of TrainControllers
  private LinkedBlockingQueue<Message> msgs; // Message queue
  
  private int trainID; // Local train ID
  private TrainController tc; // Local train controller
  private TrainControllerGUI gui; // GUI
  
  private TrainContainer trainContainer; // Reference to TrainModel container
  
  
  public TrainControllerModule()
  {
    controllers = new Hashtable<Integer, TrainController>(); // Instantiates Hashtable
    msgs = new LinkedBlockingQueue<Message>(); // Instantiates message queue

    gui = new TrainControllerGUI(this); // Starts up GUI thread
    new Thread(gui).start();
  }
  
  
  public void run()
  {
    while(true) // Runs continuously
    {
      if(msgs.peek() != null) // If message is in the queue
      {
        Message m = msgs.poll(); // Gets message
        if(name == m.getDest()) // If message is intended for train controller
        {
          //System.out.println("\nRECEIVED MSG: source->"+m.getSource() + " : dest->"+m.getDest()+"\n");
          if(m.getData() != null && m.getData().containsKey("trainID")) // If message has data and has a train ID
          {
            trainID = (int)(m.getData().get("trainID")); // Gets train ID
            if (controllers.containsKey(trainID)) // If Hashtable contains this train controller
            {
              tc = controllers.get(trainID); // Creates local TrainController
            }
            
            switch (m.getType())
            {
              case Sat_TnCnt_Request_Traversed_Block_Stats: //???????????
                
                break;
              case Sat_TnCt_ReceiptConfirm_Traversed_Block_Stats: //??????????
                
                break;
              case MBO_TnCt_Send_Moving_Block_Authority: // Moving block authority from CTC 
                tc.setMovingBlockAuth((double)(m.getData().get("authority")));
                break;
              case CTC_TnCt_Send_Manual_MovingBlock: // Manual moving block authority from CTC
                tc.setCtcMovingBlockAuth((double)(m.getData().get("authority")));
                break;
              case TcCt_TnCt_Send_Fixed_Block_Authority: // Fixed block authority from track controller
                tc.setFixedBlockAuth((double)(m.getData().get("authority")));
                break;
              case CTC_TnCt_Send_Manual_FixedBlock: // Manual fixed block from CTC
                tc.setCtcFixedBlockAuth((double)(m.getData().get("authority")));
                break;
              case CTC_TnCt_Send_Manual_Speed: // Manual velocity from CTC
                tc.setCtcOperatorVelocity((double)(m.getData().get("velocity")));
                break;
            }
          }
          else // Otherwise, no train ID was sent
          {
            System.out.println("ERROR! No train ID in message sent to train controller!");
          }
        }
        else // Otherwise, pass message along to next module
        {
          //System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : " + m.getDest()+"), (type : " + m.getType()+")");
          m.updateSender(name);
          Environment.passMessage(m);
        }
      }
    }
  }
  
  
  public void setMsg(Message m) // Add message to queue
  {
    msgs.add(m);
  }
  
  
  public void send(Message m) // Send message
  {
    //System.out.println("SENDING MSG: start->"+m.getSource() + " : dest->"+m.getDest()+" " + m.getType() + "\n");
    Environment.passMessage(m);
  }
  
  
  public void init(TrainContainer t) // Associates TrainContainer with this
  {
    trainContainer = t;
  }
  
  public Enumeration<Integer> getTrainList() // Returns list of train IDs for GUI's use
  {
    return controllers.keys();
  }
  
  
  // Methods for Train Model's use
  public TrainController createTrainController(int t) // Creates new train controller
  {
    TrainController newTrainController = new TrainController(t, trainContainer.getTrain(t), trainContainer, gui);
    controllers.put(t, newTrainController);
    gui.createDropdownModel();
    return newTrainController;
  }
  
  
  public TrainController getTrainController(int t) // Gets a train controller object
  { 
    return controllers.get(t);
  }
  
  
  public void destroyTrainController(int t) // Removes train controller when train is destroyed
  {
    controllers.remove(t);
    gui.createDropdownModel();
  }
}

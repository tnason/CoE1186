package TLTTC;
import java.util.*;

public class TrainControllerModule extends Worker implements Runnable, constData{
  private Hashtable<Integer, TrainController> controllers;
  private Module name;
  private java.util.concurrent.LinkedBlockingQueue<Message> msgs;
  
  private int trainID;
  private TrainController tc;
  
  public TrainControllerModule()
  {
    controllers = new Hashtable<Integer, TrainController>();
    name = Module.trainController;
    msgs = new java.util.concurrent.LinkedBlockingQueue<Message>();
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
          System.out.println("\nRECEIVED MSG: source->"+m.getSource() + " : dest->"+m.getDest()+"\n");
          if(m.getData() != null && m.getData().containsKey("trainID"))
          {
            trainID = (Integer)(m.getData().get("trainID"));
            if (controllers.containsKey(trainID))
            {
              tc = controllers.get(trainID); // Local TrainController
            }
            switch (m.getType())
            {
              case MBO_TnCt_Send_Moving_Block_Authority: // Moving block authority from CTC
                tc.movingBlockAuth = (Double)(m.getData().get("authority"));
                sendPower();
                break;
              case TcCt_TnCt_Send_Fixed_Block_Authority: // Fixed block authority from track controller
                tc.fixedBlockAuth = (Double)(m.getData().get("authority"));
                sendPower();
                break;
              case CTC_TnCt_Send_Manual_FixedBlock: // Manual fixed block from CTC
                tc.ctcFixedBlockAuth = (Double)(m.getData().get("authority"));
                sendPower();
                break;
              case CTC_TnCt_Send_Manual_Speed: // Manual velocity from CTC
                tc.ctcOperatorVelocity = (Double)(m.getData().get("velocity"));
                sendPower();
                break;
              case TcMd_TnCt_Send_Track_Speed_Limit: // Track speed limit from track model
                tc.trackLimit = (Double)(m.getData().get("speedLimit"));
                sendPower();
                break;
              case TnMd_TnCt_Send_Train_Velocity: // Current train velocity from train model
                tc.velocity = (Double)(m.getData().get("velocity"));
                sendPower();
                break;
              case CTC_TnCt_Send_Manual_MovingBlock: // Manual moving block authority from CTC
                tc.ctcMovingBlockAuth = (Double)(m.getData().get("authority"));
                sendPower();
                break;
              case TnMd_TnCt_Request_Train_Controller_Creation: // Train controller creation
                TrainController newTrainController = new TrainController(trainID, this);
                controllers.put(trainID, newTrainController);
                break;
              case TnMd_TnCt_Request_Train_Controller_Destruction:
                tc.closeGUI();
                controllers.remove(trainID);
                break;
              default:
                
                break;
            }
          }
          else
          {
            System.out.println("ERROR! No train ID in message sent to train controller!");
          }
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
  
  public void setMsg(Message m)
  {
    msgs.add(m);
  }
  
  
  public void send(Message m)
  {
    System.out.println("SENDING MSG: start->"+m.getSource() + " : dest->"+m.getDest()+"\n");
    Environment.passMessage(m);
  }
  
  private void requestVelocity(){
    String[] keys = {"trainID"};
    Object[] data = {trainID};
    send(new Message(name, name, Module.trainModel, msg.TnCt_TnMd_Request_Train_Velocity, keys, data));
  }
  
  public void sendPower(){
    requestVelocity();
    double powerCommand = tc.setPower();
    String[] keys = {"trainID", "power"};
    Object[] data = {trainID, powerCommand};
    send(new Message(name, name, Module.trainModel, msg.TnCt_TnMd_Send_Power, keys, data));
  }
}

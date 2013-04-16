package TLTTC;
import java.util.*;

public class TrainControllerModule extends Worker implements Runnable, constData{
  private Hashtable<Integer, TrainController> controllers;
  private Module name;
  private java.util.concurrent.LinkedBlockingQueue<Message> msgs;
  
  private int trainID;
  private TrainController tc;
  private TrainContainer trainContainer;
  private TrainModel tm;
  
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
            trainID = (int)(m.getData().get("trainID"));
            if (controllers.containsKey(trainID))
            {
              tc = controllers.get(trainID); // Local TrainController
  		  tm = trainContainer.getTrain(trainID); // Local TrainModel
            }
            switch (m.getType())
            {
				case Sat_TnCnt_Request_Traversed_Block_Stats: //???????????
					
				break;
				case Sat_TnCt_ReceiptConfirm_Traversed_Block_Stats: //??????????
				
				break;
              case MBO_TnCt_Send_Moving_Block_Authority: // Moving block authority from CTC
                tc.movingBlockAuth = (double)(m.getData().get("authority"));
                sendPower();
                break;
              case TcCt_TnCt_Send_Fixed_Block_Authority: // Fixed block authority from track controller
                tc.fixedBlockAuth = (double)(m.getData().get("authority"));
                sendPower();
                break;
				case TcMd_TnCt_Confirm_Occupancy_Return_Block_Stats: //????????????
				
				break;
				case TcMd_TnCt_Confirm_Depopulation: //???????????
				
				break;	
              case CTC_TnCt_Send_Manual_FixedBlock: // Manual fixed block from CTC
                tc.ctcFixedBlockAuth = (double)(m.getData().get("authority"));
                sendPower();
                break;
              case CTC_TnCt_Send_Manual_Speed: // Manual velocity from CTC
                tc.ctcOperatorVelocity = (double)(m.getData().get("velocity"));
                sendPower();
                break;
				case TcMd_TnCt_Send_Track_Gnd_State: // Underground state from track model
				tc.underground = (boolean)(m.getData().get("state");
				tc.setLights();
				break;
				case TcMd_TnCt_Send_Station_Name: // Next station name from track model
					tc.nextStation = (String)(m.getData().get("stationName");
					tc.stationAnnounced = false;
					tc.announceStation();
				break;
              case TcMd_TnCt_Send_Track_Speed_Limit: // Track speed limit from track model
                tc.trackLimit = (double)(m.getData().get("speedLimit"));
                sendPower();
                break;
              case CTC_TnCt_Send_Manual_MovingBlock: // Manual moving block authority from CTC
                tc.ctcMovingBlockAuth = (double)(m.getData().get("authority"));
                sendPower();
                break;
              default:
                
                break;
				// get station status from track model
            }
          }
          else
          {
            System.out.println("ERROR! No train ID in message sent to train controller!");
          }
        }
        else
        {
          System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : " + m.getDest()+"), (type : " + m.getType()+")");
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
    System.out.println("SENDING MSG: start->"+m.getSource() + " : dest->"+m.getDest()+" " + m.getType() + "\n");
    Environment.passMessage(m);
  }
  
  
  public void sendPower(){
    tc.velocity = tm.getVelocity();
    tm.setPower(tc.setPower());
  }
  
  public void init(TrainContainer t){
    trainContainer = t;
  }
  
  // Methods for TrainContainer
  public TrainController getTrainController(int trainID){
	return controllers.get(trainID);
  }
  
  public TrainController createTrainController(int trainID){
	TrainController newTrainController = new TrainController(trainID, this, trainContainer.getTrain(trainID));
    controllers.put(trainID, newTrainController);
	return newTrainController;
  }
  
  public void destroyTrainController(int trainID){
	// Todo: remove from dropdown list of trains in GUI here
	controllers.remove(trainID);
  }
  }

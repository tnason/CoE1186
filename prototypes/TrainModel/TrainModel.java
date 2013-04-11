//The actual train
package TLTTC;
import java.util.*;

public class TrainModel implements constData 
{
	//tracking block occupancy
	//[0] = 'front' block (in direction of motion)
	private ArrayList<Block> occupiedBlocks = new ArrayList<Block> ();
	private ArrayList<Double> blockEntryPos = new ArrayList<Double> ();
	private Node currentNode;

	private Message outgoingMessage;

	public boolean whiteFlag = false; //if true, TrainContainer will kill this train
	
	private boolean fromYard;
	
	private int trainID;

	//train state
	private double power = 0; //in Watts
	private double position = 0; //in m
	private double velocity = 0; //in m/s
	private double acceleration = 0;//in m/s^2
	
	private double currentBlockGrade; //for motion!
	
	private double time = 0; //in s
	
	private double mass = 51437; //loaded train mass in kg
		
	private final double trLength = 32.2; //in m
	private final double trWidth = 2.65;
	private final double trHeight = 3.42;
	
	private boolean trainBrakeOn = false;
	private boolean trainEmergencyBrakeOn = false;
	
	private final double timeStep; //in s

	private final double maxPower = 120000.0; //in W (120kW)
	private final double maxSpeed = 70000/3600.0; //in m/s (70km/hr)
	
	private final double trainFriction = .001; //coefficient of friction of rolling steel wheels
	
	private final double trainBrake = 1.2; //in m/s^2
	private final double trainEmergencyBrake = 2.73; //in m/s^2
	
	private final double gravity = 9.81; //in m/s^2

	private int accelRegime = 0;
	private int forceRegime = 0;
	
	
	public TrainModel(int trainID, Block start, double timeStep) 
	{
		this.trainID = trainID;
		this.timeStep = timeStep;
		
		occupiedBlocks.add(start);
		blockEntryPos.add(position); 
		
		//place the train on the initial block (outside of the yard)
		currentBlockGrade = occupiedBlocks.get(0).getGrade();
		occupiedBlocks.get(0).setOccupation(true);
		fromYard = true;

	}

	public double getVelocity() 
	{
		return velocity;
	}

	public void setYardNode (Node yard)
	{
		currentNode = yard;
	}
	
	
	public void setPower(double pow) 
	{
		power = pow;
		if(pow < 0.0) 
		{
			setBrake(true);
		} 
		else 
		{
			setBrake(false);
		}
	}
	
	private boolean setBrake(boolean brake) 
	{
		return trainBrakeOn = brake;
	}
	
	public boolean setEmergencyBrake(boolean brake) 
	{
		return trainEmergencyBrakeOn = brake;
	}
	
	//for motion debugging
	public void printState() 
	{
		System.out.format("t: %.3f, p: %.1e, x: %.6f, v: %.6f, a: %.3f %d %d%n", time, power, position, velocity, acceleration, forceRegime, accelRegime);
	}
	
	public void motionStep() 
	{
		double rollingFrictionForce;
		double hillResistanceForce;
		double engineForce;
		
		double endPosition; //x(t+dt)
		double endVelocity; //v(t+dt) 
		double endAccel = 0;//a(t+dt)
		
		
		double trackAngle;
		trackAngle = Math.toDegrees(Math.atan(currentBlockGrade/100.0));
		
		if(power > maxPower) 
		{
			power = maxPower;
		}
		
		//apply brakes over train power
		if(trainBrakeOn) 
		{
			acceleration = -trainBrake; 
			endAccel = -trainBrake;
		}
		if(trainEmergencyBrakeOn) 
		{
			acceleration = -trainEmergencyBrake;
			endAccel = -trainEmergencyBrake;
		}
		
		//Modified velocity verlet algorithm
		//Step 1: Determine v(t+dt)
		//	v(t+dt) = v(t) + a(t)*dt
		//Step 2: Derive a(t+dt) using v(t+dt) to calc forces
		// 	a(t+dt) = F_engine / m (with F_friction + F_hill)
		//Step 3: Determine x(t+dt)
		//	x(t+dt) = x(t) + .5(v(t)+v(t+dt))*dt + .25(a(t)+a(t+dt))*dt^2
		
		endVelocity = velocity + acceleration*timeStep;
		
		//apply brakes
		if(trainBrakeOn || trainEmergencyBrakeOn) 
		{
			if(endVelocity < 0) 
			{
				endVelocity = 0.0;
				endAccel = 0.0;
				
				accelRegime = 0;
				//stopped
				endPosition = position;
			}
			//calculates current position from last step velocity and acceleration
			//x(t+dt) = x(t) + .5(v(t)+v(t+dt))*dt + .25(a(t)+a(t+dt))*dt^2
			endPosition = position + .5*(velocity+endVelocity)*timeStep + .25*(acceleration+endAccel)*timeStep*timeStep;
		} 
		else 
		{
			//Calculate resistance due to wheel friction
			//F_engine = power / v;	
			//F_frict = (k_wheels * g * m(kg) * cos(trackAngle)) 
			//	Friction due to normal force of train on track
			//	ALWAYS RESISTS MOTION
			//F_hill = (mg*sin(trackAngle))
			//	Additional resistance (uphill) or additional force provided (downhill)
			rollingFrictionForce = trainFriction * (Math.cos(Math.toRadians(trackAngle))*gravity * mass);
			hillResistanceForce = (mass * gravity * Math.sin(Math.toRadians(trackAngle)));
			if(endVelocity > .005) 
			{
				forceRegime = 1;
				engineForce = power / endVelocity;
			} else {
				forceRegime = 0;
				engineForce = power / .005;
			}	
	
			//Use forces to determine acceleration/direction
			if(engineForce > (rollingFrictionForce + hillResistanceForce)) 
			{
				//move forward
				endAccel = (engineForce - (rollingFrictionForce + hillResistanceForce))/mass;
				accelRegime = 2;
			} 
			else if(engineForce > hillResistanceForce) 
			{
				//rolling friction can cause deceleration
				//but train will not reverse
				endAccel = (engineForce - (rollingFrictionForce + hillResistanceForce))/mass;
				accelRegime = 3;	
				if(endVelocity < 0.0001) {
					endVelocity = 0.0;
					endAccel = 0.0;
				}
			} else if((Math.abs(engineForce - hillResistanceForce)) > rollingFrictionForce) {
				//slide backwards (backwards motion strong enough to overcome friction)
				endAccel = -(hillResistanceForce - (engineForce + rollingFrictionForce))/mass;
				accelRegime = 4;
			} 
			else 
			{
				//stay still (backwards motion can't overcome friction)
				endAccel = 0.0;
				accelRegime = 5;
			}
		
			if(endVelocity > maxSpeed) 
			{ //cap speed at speed limit
				endVelocity = maxSpeed;
				endAccel = 0; //and stop acceleration (for position calc in next step)
			}
			
			//calculates current position from last step velocity and acceleration
			//x(t+dt) = x(t) + .5(v(t)+v(t+dt))*dt + .25(a(t)+a(t+dt))*dt^2
			endPosition = position + .5*(velocity+endVelocity)*timeStep + .25*(acceleration+endAccel)*timeStep*timeStep;
	
		}	

		position = endPosition;
		velocity = endVelocity;
		acceleration = endAccel;
		
		time += timeStep;
	
		updateOccupancy();	
	}
	
	private void updateOccupancy()
	{
		Node nextNode;

		//Update occupancy/traverse blocks
		//must 'bootstrap' to get consistent stats after leaving yard
		if(fromYard)
		{
			if((position - blockEntryPos.get(0)) > (occupiedBlocks.get(0).getLength() - trLength/2.0)) //if the front of the train is crossing into a new block
			{

				System.out.println("	!!!!!!!!!!!!!!!NEW BLOCK!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				System.out.println("currentNode: " + currentNode);
				nextNode = occupiedBlocks.get(0).getNextNode(currentNode);
				System.out.println("nextNode: " + nextNode);
				occupiedBlocks.add(0, occupiedBlocks.get(0).getNextBlock(currentNode));
				blockEntryPos.add(0, position);
				
				occupiedBlocks.get(0).setOccupation(true);

				currentNode = nextNode;
				//shouldn't need yard entry stuff here...
				
				//new block!
				//send TnMd_TcMd_Request_Track_Speed_Limit
				outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.trackModel, msg.TnMd_TcMd_Request_Track_Speed_Limit, new String[] {"trainID", "blockID"}, new Object[] {trainID, occupiedBlocks.get(0).getID()});
				Environment.passMessage(outgoingMessage); 

				//send TnMd_CTC_Send_Block_Occupied
				outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_CTC_Send_Block_Occupied, new String[] {"blockID"}, new Object[] {occupiedBlocks.get(0).getID()});
				Environment.passMessage(outgoingMessage);
	
				//send TnMd_TcCt_Update_Block_Occupancy
				outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_TcCt_Update_Block_Occupancy, new String[] {"blockID", "occupancy", "block"}, new Object[] {occupiedBlocks.get(0).getID(), true, occupiedBlocks.get(0)});
				Environment.passMessage(outgoingMessage);
			}
			
			if(occupiedBlocks.size() == 2)
			{
				if((position - blockEntryPos.get(1)) > (occupiedBlocks.get(1).getLength() - trLength/2.0)) //if the back of the train has left the old block
				{
					//send TnMd_TcCt_Update_Block_Occupancy
					outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_TcCt_Update_Block_Occupancy, new String[] {"blockID", "occupancy", "block"}, new Object[] {occupiedBlocks.get(1).getID(), false, occupiedBlocks.get(1)});
					Environment.passMessage(outgoingMessage);
					
					fromYard = false;
					occupiedBlocks.get(1).setOccupation(false);
					occupiedBlocks.remove(1);
					blockEntryPos.remove(1);
				}
			}
		}
		//normal case
		else
		{
			if((position - blockEntryPos.get(0)) > (occupiedBlocks.get(0).getLength()))
			{
				System.out.println("	!!!!!!!!!!!!!!!NEW BLOCK!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				nextNode = occupiedBlocks.get(0).getNextNode(currentNode);

				if(nextNode.getNodeType() == NodeType.Yard) //yard entry
				{
					//destruct 

					//send TnMd_TnCt_Request_Train_Controller_Destruction
					outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.trainController, msg.TnMd_TnCt_Request_Train_Controller_Destruction, new String[] {"trainID"}, new Object[] {trainID});
					Environment.passMessage(outgoingMessage); 
					
					//send TnMd_CTC_Request_Train_Destruction
					outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_CTC_Request_Train_Destruction, new String[] {"trainID"}, new Object[] {trainID});
					Environment.passMessage(outgoingMessage); 
					
					//send TnMd_Sch_Notify_Yard
					outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.scheduler, msg.TnMd_Sch_Notify_Yard, new String[] {"entry","trainID", "blockID"}, new Object[] {true, trainID, occupiedBlocks.get(0).getID()});
					Environment.passMessage(outgoingMessage);

					//no, really... destruct!
					//well... get yourself unlisted first.
					//just give up then. They'll do the rest...
					whiteFlag = true;
				}

				//if not yard, keep goin!
				occupiedBlocks.add(0, occupiedBlocks.get(0).getNextBlock(currentNode));
				blockEntryPos.add(0, position);
				
				occupiedBlocks.get(0).setOccupation(true);
				currentNode = nextNode;

				//new block!
				//send TnMd_TcMd_Request_Track_Speed_Limit
				outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.trackModel, msg.TnMd_TcMd_Request_Track_Speed_Limit, new String[] {"trainID", "blockID"}, new Object[] {trainID, occupiedBlocks.get(0).getID()});
				Environment.passMessage(outgoingMessage); 

				//send TnMd_CTC_Send_Block_Occupied
				outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_CTC_Send_Block_Occupied, new String[] {"blockID"}, new Object[] {occupiedBlocks.get(0).getID()});
				Environment.passMessage(outgoingMessage);

				//send TnMd_TcCt_Update_Block_Occupancy
				outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_TcCt_Update_Block_Occupancy, new String[] {"blockID", "occupancy", "block"}, new Object[] {occupiedBlocks.get(0).getID(), true, occupiedBlocks.get(0)});
				Environment.passMessage(outgoingMessage);

			}
			
			if(occupiedBlocks.size() == 2)
			{
				if((position - blockEntryPos.get(1)) > (occupiedBlocks.get(1).getLength() + trLength)) //if the back of the train has left the old block
				{
					//send TnMd_TcCt_Update_Block_Occupancy
					outgoingMessage = new Message(Module.trainModel, Module.trainModel, Module.CTC, msg.TnMd_TcCt_Update_Block_Occupancy, new String[] {"blockID", "occupancy", "block"}, new Object[] {occupiedBlocks.get(1).getID(), false, occupiedBlocks.get(0)});
					Environment.passMessage(outgoingMessage);

					occupiedBlocks.get(1).setOccupation(false);
					occupiedBlocks.remove(1);
					blockEntryPos.remove(1);
				}
			}
		}
	}
	
}

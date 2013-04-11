package TLTTC;

import java.util.concurrent.*;

public class MovingBlockOverlay extends Worker implements constData
{
	private LinkedBlockingQueue<Message> messages;
	private Module name;
	private MyLinkedList<Train> trains;

	public static void main(String[] args)
	{
	}

	public MovingBlockOverlay()
	{
		messages = new LinkedBlockingQueue<Message>();
		this.name = Module.MBO;	
	}

	public void run()
	{

		while(true)
		{
			if(messages.peek() != null)
			{
				Message message = messages.poll();

				if(name == message.getDest())
				{
					System.out.println("\nRECEIVED MESSAGE: source->" + message.getSource() + " : dest->" + message.getDest() + "\n");

					switch((int)message.getData().get("id"))
					{
						case 86:
							receivedGPSLocation(message);
							break;
						case 88:
							receivedStoppingDistance(message);
							break;
						case 91:
							//Not implemented
							break;
						case 96:
							receivedTrainUpdate(message);
							break;	
					}
				}
				else
				{
					System.out.println("PASSING MESSAGE: step->" + name + " source->" + message.getSource() + " dest->" + message.getDest());
					message.updateSender(name);
					Environment.passMessage(message);
				}
			}

			if(trains != null && trains.size() > 0)
			{
				Train forwardTrain;
				Train train;
	
				forwardTrain = trains.previous(); //returns selected train, then goes backwards
				train = trains.selected();

				if(forwardTrain.isLocationValid() && train.isLocationValid() && train.isStoppingDistanceValid())
				{
					sendAuthority(train.trainNumber, calculateMovingBlock(train.getLocation(), train.getStoppingDistance(), forwardTrain.getLocation(), 0));
				}

				forwardTrain.setLocationValid(false);
				requestLocation(forwardTrain.trainNumber);
				train.setStoppingDistanceValid(false);
				requestStoppingDistance(train.trainNumber);
			}			
		}

	}

	private void receivedGPSLocation(Message message)
	{
		
	}

	private void receivedStoppingDistance(Message message)
	{
		Train train;

		train = findTrain((int)(message.getData().get("train_ID")));

		if(train != null)
		{
			train.setStoppingDistance((double)(message.getData().get("stoppingDist")), System.currentTimeMillis());
			train.setStoppingDistanceValid(true);
		}
	}

	private void receivedTrainUpdate(Message message)
	{
		trains = new MyLinkedList<Train>(message.getData().get("trainList"));
	}

	public void setMsg(Message message)
	{
		messages.add(message);
	}

	public void send(Message message)
	{
		System.out.println("SENDING MSG: start->"+message.getSource() + " : dest->"+message.getDest()+"\n");
		Environment.passMessage(message);
	}

	private Train findTrain(int trainNumber)
	{
		Train t;
		Train value;
		Train train;

		value = null;

		if(trains.size() == 0)
		{
			return value;
		}

		t = trains.selected();

		do
		{
			train = trains.next();

			if(train.trainNumber == trainNumber)
			{
				value = train;
			}
		}
		while(!t.equals(trains.selected()));

		return value;
	}

	private double calculateMovingBlock(double trainLocation, double trainSpeed, double forwardTrainLocation, double forwardTrainSpeed)
	{
/*
		double vF = 0;
		double timeF = (0 - trainSpeed) / Train.BRAKEDECELERATION;
		double distance = trainSpeed * timeF + .5 * Train.BRAKEDECELERATION * Math.pow(timeF, 2);

		return forwardTrainLocation - ((distance + trainLocation) % Track.LENGTH);		
*/
		return Double.MAX_VALUE;
	}

	private void sendAuthority(int trainNumber, double authority)
	{
		Message message;

		message = new Message(Module.MBO, Module.MBO, Module.CTC);
		message.addData("Train Number", trainNumber);
		message.addData("MBO Authority", authority);
		send(message);
	}
	private void requestLocation(int trainNumber)
	{
		Message message;

		message = new Message(Module.MBO, Module.MBO, Module.satellite);
		message.addData("Train Number", trainNumber);
		send(message);
	}

	private void requestStoppingDistance(int trainNumber)
	{
		Message message;

		message = new Message(Module.MBO, Module.MBO, Module.trainController);
		message.addData("Train Number", trainNumber);
		send(message);
	}
}
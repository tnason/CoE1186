package TLTTC;
import java.util.*;
import java.util.concurrent.*;

/*
public enum Module
	{
		satellite, scheduler, MBO, CTC, trackController,
		trackModel, trainModel, trainController
	}

*/

public class Environment implements constData
{
    private static LinkedBlockingQueue<Message> messageQ = new LinkedBlockingQueue<Message>();
    private static Scanner s = new Scanner(System.in);

    public static void main(String [] args)
    {
    	ArrayList<Module> modualOrder = new ArrayList<Module>();
		HashMap<Module, Worker> modWorker = new HashMap<Module, Worker>();

		Worker tkc = new TrackController();
		Worker tkm = new TrackModel();
		Worker trc = new TrainControllerModule();
		Worker trm = new TrainContainer();
		Worker ctc = new CTCOffice();

		modualOrder.add(Module.trackController);
		modualOrder.add(Module.trackModel);
		modualOrder.add(Module.trainModel);
		modualOrder.add(Module.trainController);
		modualOrder.add(Module.CTC);
		
		modWorker.put(Module.trackController, tkc);
		modWorker.put(Module.trackModel, tkm);
		modWorker.put(Module.trainController, trc);
		modWorker.put(Module.trainModel, trm);
		modWorker.put(Module.CTC, ctc);

		Thread tkcThread = new Thread(tkc);
		Thread tkmThread = new Thread(tkm);
		Thread trcThread = new Thread(trc);
		Thread trmThread = new Thread(trm);
		Thread ctcThread = new Thread(ctc);

		tkcThread.start();
		tkmThread.start();
		trcThread.start();
		trmThread.start();
		ctcThread.start();

		ctcThread.send();//w2.send();w3.send();w4.send();w5.send();


		while(true)
		{
			if(messageQ.peek() != null)
			{
				Message inbox = messageQ.poll();

				if(modualOrder.indexOf(inbox.getSender()) < modualOrder.indexOf(inbox.getDest()))
				{
					Module right = modualOrder.get(modualOrder.indexOf(inbox.getSender()) + 1);
					modWorker.get(right).setMsg(inbox);
				}
				else if(modualOrder.indexOf(inbox.getSender()) > modualOrder.indexOf(inbox.getDest()))
				{
					Module left = modualOrder.get(modualOrder.indexOf(inbox.getSender()) - 1);
					modWorker.get(left).setMsg(inbox);
				}
			}
		}
    }

    public static void passMessage(Message m)
    {
		messageQ.add(m);
    }
}

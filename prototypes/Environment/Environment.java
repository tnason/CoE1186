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

		Worker mbo = new MovingBlockOverlay();
		Worker sch = new Scheduler();
		Worker tkc = new TrackController();
		Worker tkm = new TrackModel();
		Worker trc = new TrainControllerModule();
		Worker trm = new TrainContainer();
		Worker ctc = new CTCOffice();
		
		modualOrder.add(Module.MBO);
		modualOrder.add(Module.scheduler);
		modualOrder.add(Module.CTC);
		modualOrder.add(Module.trackController);
		modualOrder.add(Module.trackModel);
		modualOrder.add(Module.trainModel);
		modualOrder.add(Module.trainController);
		
		modWorker.put(Module.MBO, mbo);
		modWorker.put(Module.scheduler, sch);
		modWorker.put(Module.CTC, ctc);
		modWorker.put(Module.trackController, tkc);
		modWorker.put(Module.trackModel, tkm);
		modWorker.put(Module.trainModel, trm);
		modWorker.put(Module.trainController, trc);

		Thread mboThread = new Thread(mbo);
		Thread schThread = new Thread(sch);
		Thread tkcThread = new Thread(tkc);
		Thread tkmThread = new Thread(tkm);
		Thread trcThread = new Thread(trc);
		Thread trmThread = new Thread(trm);
		Thread ctcThread = new Thread(ctc);

		mboThread.start();
		schThread.start();
		tkcThread.start();
		tkmThread.start();
		trcThread.start();
		trmThread.start();
		ctcThread.start();

		Message begin = new Message(Module.CTC, Module.CTC, Module.trainModel, msg.CTC_TnMd_Request_Train_Creation,
							new String [] {"trainID"}, new Object [] {0});

		ctc.send(begin);//w2.send();w3.send();w4.send();w5.send();


		while(true)
		{
			if(messageQ.peek() != null)
			{
				Message inbox = messageQ.poll();

				if(modualOrder.indexOf(inbox.getSender()) < modualOrder.indexOf(inbox.getDest()))
				{
					Module right = modualOrder.get(modualOrder.indexOf(inbox.getSender()) + 1);
					//System.out.println("To : " + modWorker.get(right));
					modWorker.get(right).setMsg(inbox);
				}
				else if(modualOrder.indexOf(inbox.getSender()) > modualOrder.indexOf(inbox.getDest()))
				{
					Module left = modualOrder.get(modualOrder.indexOf(inbox.getSender()) - 1);
					//System.out.println("To : " + modWorker.get(left));
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

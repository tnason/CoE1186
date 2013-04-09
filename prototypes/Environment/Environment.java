<<<<<<< HEAD
import java.util.*;
import java.util.concurrent.*;

public class Environment implements constData
{
    private static LinkedBlockingQueue<Message> messageQ = new LinkedBlockingQueue<Message>();
    private static Scanner s = new Scanner(System.in);

    public static void main(String [] args)
    {
    	ArrayList<Module> modualOrder = new ArrayList<Module>();
		HashMap<Module, Worker> modWorker = new HashMap<Module, Worker>();

		Worker w1 = new Worker(Module.CTC);
		Worker w2 = new Worker(Module.trackController);
		Worker w3 = new Worker(Module.trackModel);
		Worker w4 = new Worker(Module.trainModel);
		Worker w5 = new Worker(Module.trainController);

		modualOrder.add(Module.CTC);
		modualOrder.add(Module.trackController);
		modualOrder.add(Module.trackModel);
		modualOrder.add(Module.trainModel);
		modualOrder.add(Module.trainController);

		modWorker.put(Module.CTC, w1);
		modWorker.put(Module.trackController, w2);
		modWorker.put(Module.trackModel, w3);
		modWorker.put(Module.trainModel, w4);
		modWorker.put(Module.trainController, w5);

		Thread a = new Thread(w1);
		Thread b = new Thread(w2);
		Thread c = new Thread(w3);
		Thread d = new Thread(w4);
		Thread e = new Thread(w5);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

		w1.send();//w2.send();w3.send();w4.send();w5.send();


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
=======
import java.util.*;
import java.util.concurrent.*;

public class Environment implements constData
{
    private static LinkedBlockingQueue<Message> messageQ = new LinkedBlockingQueue<Message>();
    private static Scanner s = new Scanner(System.in);

    public static void main(String [] args)
    {
    	ArrayList<Module> modualOrder = new ArrayList<Module>();
		HashMap<Module, Worker> modWorker = new HashMap<Module, Worker>();

		Worker w1 = new Worker(Module.CTC);
		Worker w2 = new Worker(Module.trackController);
		Worker w3 = new Worker(Module.trackModel);
		Worker w4 = new Worker(Module.trainModel);
		Worker w5 = new Worker(Module.trainController);

		modualOrder.add(Module.CTC);
		modualOrder.add(Module.trackController);
		modualOrder.add(Module.trackModel);
		modualOrder.add(Module.trainModel);
		modualOrder.add(Module.trainController);

		modWorker.put(Module.CTC, w1);
		modWorker.put(Module.trackController, w2);
		modWorker.put(Module.trackModel, w3);
		modWorker.put(Module.trainModel, w4);
		modWorker.put(Module.trainController, w5);

		Thread a = new Thread(w1);
		Thread b = new Thread(w2);
		Thread c = new Thread(w3);
		Thread d = new Thread(w4);
		Thread e = new Thread(w5);

		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

		w1.send();//w2.send();w3.send();w4.send();w5.send();


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
>>>>>>> bfe6e5edf936ddbceaac8a112c5e324c0ed97d70

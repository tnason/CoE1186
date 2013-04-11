package TLTTC;

public abstract class Worker implements Runnable 
{
    
    abstract void setMsg(Message m);

    abstract void run();

    //public void send();
}

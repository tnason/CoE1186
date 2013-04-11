package TLTTC;

public abstract class Worker implements Runnable 
{
    
    public abstract void setMsg(Message m);

    public abstract void run();

    //public void send();
}

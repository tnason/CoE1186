package TLTTC;

public abstract class Worker implements Runnable, constData
{
    
    public abstract void setMsg(Message m);

    public abstract void run();

    public abstract void send(Message m);

    //public void send();
}

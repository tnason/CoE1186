package TLTTC;

public class TrainContainerTest extends TrainContainer
{

	//Just testing the enumeration!
	public static void main(String args[])
	{
		msg m = msg.TnCt_TnMd_Request_Train_Velocity;
		for(msg l: msg.values())
		{
			System.out.println("enum: " + l);
		}
	}

}

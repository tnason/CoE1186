package TLTTC;
public interface constData
{
	public enum Module
	{
		satellite, scheduler, MBO, CTC, trackController,
		trackModel, trainModel, trainController
	}
	
	public enum msg
	{
		verify, // temporary. Just used so that Worker.java compiles
		CTC_TnCt_Send_Moving_Block_Authority,
		CTC_TnCt_Send_Manual_MovingBlock,
		TcCt_TnCt_Send_Fixed_Block_Authority,
		TnCt_TnMd_Send_Train_Controller,
		TnCt_TnMd_Send_Power,
		TcMd_TnCt_Send_Track_Speed_Limit,
		TnMd_TnCt_Send_Train_Velocity,
		TnMd_TnCt_Request_Power
	}
}

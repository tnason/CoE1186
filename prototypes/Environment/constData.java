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
		MBO_TnCt_Send_Moving_Block_Authority,
		Sch_MBO_Notify_Train_Added_Removed,
		Sch_CTC_Send_Schedule,
		CTC_Sch_Generate_Schedule,
		CTC_TnMd_Request_Train_Creation,
		CTC_TnCt_Send_Manual_MovingBlock,
		CTC_TnCt_Send_Manual_FixedBlock,
		CTC_TnCt_Send_Manual_Speed,
		TcCt_TnCt_Send_Fixed_Block_Authority,
		TcMd_TnCt_Send_Track_Speed_Limit,
		TnMd_Sch_Notify_Yard,
		TnMd_CTC_Confirm_Train_Creation,
		TnMd_CTC_Request_Train_Destruction,
		TnMd_CTC_Send_Block_Occupied,
		TnMd_TcCt_Update_Block_Occupancy,
		TnMd_TnCt_Request_Train_Controller_Creation,
		TnMd_TnCt_Request_Train_Controller_Destruction,
		TnMd_TnCt_Send_Train_Velocity,
		TnCt_TnMd_Send_Power,
		TnCtTcMdRequestTrackSpeedLimit, verify,
		TnCt_TnMd_Request_Train_Velocity,
		TnCt_TcMd_Request_Track_Speed_Limit,
		placeHolder
	}

	public enum NodeType
	{
		Node,
		Connector,
		Crossing,
		Switch,
		Yard,
		Station

	}
}

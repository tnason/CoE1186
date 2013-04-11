package TLTTC;

import java.sql.*;

public class Operator
{
	public final String firstName;
	public final String lastName;
	public final int trainNumber;
	public final long shiftStart;
	public final long breakStart;
	public final long breakEnd;
	public final long shiftEnd;

	public OperatorStatus status;

	public Operator(String firstName, String lastName, int trainNumber, long shiftStart, OperatorStatus status)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.trainNumber = trainNumber;
		this.shiftStart = shiftStart;
		this.breakStart = shiftStart + 14400000;
		this.breakEnd = breakStart + 1800000;
		this.shiftEnd = breakEnd + 14400000;
		this.status = status;
	}

	public String toString()
	{
		return new String("First Name: " + firstName + 
					"\nLast Name: " + lastName + 
					"\nTrain Number: " + trainNumber + 
					"\nShift Start: " + new Time(shiftStart).toString() + 
					"\nBreak Start: " + new Time(breakStart).toString() +
					"\nBreak End: " + new Time(breakEnd).toString() +
					"\nShift End: " + new Time(shiftEnd).toString() +
					"\nStatus: " + status.toString());
	}
}
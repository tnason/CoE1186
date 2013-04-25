//Marcus Hayes
//Computer Engineeering
//Senior
//ECE 1186
//Th 6-9
//TLTTC - Scheduler/MBO

package TLTTC;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class OperatorScheduleTableModel extends DefaultTableModel
{
  boolean sortedHighToLow;
	int sortColumn;

	public OperatorScheduleTableModel()
	{
		super();

		addColumn("First Name");
		addColumn("Last Name");
		addColumn("Train Number");
		addColumn("Shift Start");
		addColumn("Break Start");
		addColumn("Break End");
		addColumn("Shift End");
		addColumn("Status");

		sortColumn = 2;
		sortedHighToLow = false;		
	}

	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	/*Updates the display of the Operator schedule in the UI*/
	public void update(OperatorSchedule schedule)
	{
		Iterator<Operator> i;
		boolean[] rowMatches;
		Operator operator;

		rowMatches = new boolean[getRowCount()];
		i = schedule.getIterator();

		while(i.hasNext())
		{
			boolean match = false;

			operator = i.next();

			/*search each row for matches*/

			for(int row = 0; row < rowMatches.length; row++)
			{
				/*if a match is found, change the status*/

				if(operator.trainNumber == (int)getValueAt(row ,2))
				{
					match = true;
					rowMatches[row] = true;
					setValueAt(operator.status.toString(), row, 7);
					break;
				}
			}

			/*if not, add it to the end*/

			if(!match)
			{
				addRow(new Object[]{operator.firstName, operator.lastName, operator.trainNumber, new Time(operator.shiftStart), new Time(operator.breakStart), new Time(operator.breakEnd), new Time(operator.shiftEnd), operator.status.toString()});
			}
		}

		/*remove all indices that do not have a match*/

		for(int row = rowMatches.length - 1; row >= 0; row--)
		{
			if(!rowMatches[row])
			{
				removeRow(row);
			}
		}
	}
}

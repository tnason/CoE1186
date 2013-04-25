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

@SuppressWarnings("serial")  
public class TimetableTableModel extends DefaultTableModel
{
  public TimetableTableModel()
	{
		super();

		addColumn("Station Name");
		addColumn("Train Number");
		addColumn("Arrival Time");
		addColumn("Status");		
	}

	public boolean isCellEditable(int row, int column)
	{
		return false;
	}

	/*Updates the display of the Operator schedule in the UI*/

	public void update(Timetable timetable)
	{
		Iterator<TimesObject> i;
		boolean[] rowMatches;
		TimesObject time;
		int columns;
		int stationColumn;
		int trainIDColumn;
		int arrivalColumn;
		int statusColumn;

		rowMatches = new boolean[getRowCount()];
		i = timetable.getIterator();
		columns = getColumnCount();
		stationColumn = findColumn("Station Name");
		trainIDColumn = findColumn("Train Number");
		arrivalColumn = findColumn("Arrival Time");
		statusColumn = findColumn("Status");

		while(i.hasNext())
		{
			boolean match = false;

			time = i.next();

			/*search each row for matches*/

			for(int row = 0; row < rowMatches.length; row++)
			{
				/*if a match is found, change the status*/

				if(time.stationName.equals((String)getValueAt(row, stationColumn)) && time.trainNumber == (int)getValueAt(row, trainIDColumn) && time.time == ((Time)getValueAt(row, arrivalColumn)).getTime())
				{
					match = true;
					rowMatches[row] = true;

					setValueAt(time.stationName, row, stationColumn);
					setValueAt(time.trainNumber, row, trainIDColumn);
					setValueAt(new Time(time.time), row, arrivalColumn);
					setValueAt(time.status.toString(), row, statusColumn);
					break;
				}
			}

			/*if not, add it to the end*/

			if(!match)
			{
				addRow(new Object[]{time.stationName, time.trainNumber, time.trainNumber, new Time(time.time), time.status.toString()});
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

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

public class SchedulerViewModel
{
	private Container myContainer;
	private OperatorScheduleTableModel mySchedule;
	private TimetableTableModel myTimetable;
	private JFrame myFrame;
	private JTable myScheduleGrid;
	private JTable myTimetableGrid;
	private JPanel myPanel;
	private JButton myScheduleButton;
	private JButton myTimetableButton;

	private Scheduler scheduler;

	public static void main(String[] args)
	{
	}

	public SchedulerViewModel(Scheduler scheduler)
	{
		initUI();

		this.scheduler = scheduler;
		scheduler.addSchedulerListener(new MySchedulerListener());
	}

	public void initUI()
	{
		myFrame = new JFrame("Marcus Hayes - Scheduler");
		myContainer = myFrame.getContentPane();

		mySchedule = new OperatorScheduleTableModel();
		myTimetable = new TimetableTableModel();

		myScheduleGrid = new JTable(mySchedule);
		myScheduleGrid.setRowSorter(new TableRowSorter<TableModel>(mySchedule));
		myScheduleGrid.setPreferredScrollableViewportSize(new Dimension(200, myScheduleGrid.getRowHeight() * 5));
		myScheduleGrid.setFillsViewportHeight(true);

		myTimetableGrid = new JTable(myTimetable);
		myTimetableGrid.setRowSorter(new TableRowSorter<TableModel>(myTimetable));
		myTimetableGrid.setPreferredScrollableViewportSize(new Dimension(200, myTimetableGrid.getRowHeight() * 5));
		myTimetableGrid.setFillsViewportHeight(true);

		myScheduleButton = new JButton("Update Operator Schedule");
		myScheduleButton.setHorizontalTextPosition(AbstractButton.CENTER);
		myScheduleButton.setVerticalTextPosition(AbstractButton.CENTER);
		myScheduleButton.addActionListener(new MyButtonListener());

		myTimetableButton = new JButton("Update Timetable");
		myTimetableButton.setHorizontalTextPosition(AbstractButton.CENTER);
		myTimetableButton.setVerticalTextPosition(AbstractButton.CENTER);
		myTimetableButton.addActionListener(new MyButtonListener());

		myPanel = new JPanel();

		myPanel.add(myScheduleButton);
		myPanel.add(myTimetableButton);

		myContainer.add(new JScrollPane(myScheduleGrid), BorderLayout.NORTH);
		myContainer.add(new JScrollPane(myTimetableGrid), BorderLayout.CENTER);
		myContainer.add(myPanel, BorderLayout.SOUTH);

		myFrame.validate();
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initScheduler()
	{
	}

	/*action listener to update the UI when the schedule or timetable changes*/

	private class MySchedulerListener implements SchedulerListener
	{
		public void timetableChanged(SchedulerEvent e)
		{
			myTimetable.update(scheduler.getTimetable());

		}

		public void operatorScheduleChanged(SchedulerEvent e)
		{
			mySchedule.update(scheduler.getOperatorSchedule());

		}
	}

	/*action listener to request updates from the scheduler*/

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == myTimetableButton)
			{
				scheduler.updateTimetable();
			}
			else if(e.getSource() == myScheduleButton)
			{
				scheduler.updateOperatorSchedule();
			}
		}
	}
}

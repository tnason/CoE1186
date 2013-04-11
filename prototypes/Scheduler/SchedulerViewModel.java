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
	private DefaultTableModel mySchedule;
	private DefaultTableModel myTimetable;
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
		myFrame = new JFrame();
		myContainer = myFrame.getContentPane();

		mySchedule = new DefaultTableModel();
		myTimetable = new DefaultTableModel();

		myScheduleGrid = new JTable(mySchedule);
		myTimetableGrid = new JTable(myTimetable);

		mySchedule.addColumn("First Name");
		mySchedule.addColumn("Last Name");
		mySchedule.addColumn("Train Number");
		mySchedule.addColumn("Shift Start");
		mySchedule.addColumn("Break Start");
		mySchedule.addColumn("Break End");
		mySchedule.addColumn("Shift End");
		mySchedule.addColumn("Status");

		myScheduleGrid.setFillsViewportHeight(true);

		myTimetable.addColumn("Station Name");
		myTimetable.addColumn("Train Number");
		myTimetable.addColumn("Arrival Time");
		myTimetable.addColumn("Status");

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

	private class MySchedulerListener implements SchedulerListener
	{
		public void timetableChanged(SchedulerEvent e)
		{
			Iterator<TimesObject> i;
			TimesObject t;

			i = scheduler.getTimetable().getIterator();

			while(i.hasNext())
			{
				t = i.next();
				myTimetable.addRow(new Object[]{t.stationName, t.trainNumber, new Time(t.time), t.status.toString()});
			}
		}

		public void operatorScheduleChanged(SchedulerEvent e)
		{
			Iterator<Operator> i;
			Operator s;

			i = scheduler.getOperatorSchedule().getIterator();

			while(i.hasNext())
			{
				s = i.next();
				mySchedule.addRow(new Object[]{s.firstName, s.lastName, s.trainNumber, new Time(s.shiftStart), new Time(s.breakStart), new Time(s.breakEnd), new Time(s.shiftEnd), s.status.toString()});
			}
		}
	}

	private class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == myTimetableButton)
			{
				System.out.println("timetable");
				scheduler.updateTimetable();
			}
			else if(e.getSource() == myScheduleButton)
			{
				System.out.println("schedule");
				scheduler.updateOperatorSchedule();
			}
		}
	}
}